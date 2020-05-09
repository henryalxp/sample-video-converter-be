package com.wfs.fileconverter.service.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.Instant;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wfs.fileconverter.FileProperties;
import com.wfs.fileconverter.service.CleanerService;
import com.wfs.fileconverter.service.ConverterService;
import com.wfs.fileconverter.util.AppConstants;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoAttributes;
import ws.schild.jave.VideoAttributes.X264_PROFILE;
import ws.schild.jave.VideoSize;

@Service
public class ConverterServiceImpl implements ConverterService {

	private Logger logger = LoggerFactory.getLogger(ConverterServiceImpl.class);

	private FileProperties fileProperties;

	private CleanerService cleanerService;

	@Autowired
	public ConverterServiceImpl(
			FileProperties fileProperties,
			CleanerService cleanerService) {
		this.fileProperties = fileProperties;
		this.cleanerService = cleanerService;
	}

	@Override
	public Resource convert(MultipartFile multipartFile) {

		String uniqueName = RandomStringUtils.random(10, true, true);
		String uniqueInputName = fileProperties.getInputPrefix() + uniqueName;
		String uniqueOutputName = fileProperties.getOutputPrefix() + uniqueName;

		Instant begin = Instant.now();

		EncodingAttributes attrs = getEncodingAttributes(getAudioAttributes(), getVideoAttributes());

		Path uploadFolderPath = Paths.get(fileProperties.getUploadDir());
		Path inputPath = uploadFolderPath
				.resolve(uniqueInputName + AppConstants.DOT + fileProperties.getTmpExtension());
		Path outputFolderPath = Paths.get(fileProperties.getOutputDir());
		Path outputPath = outputFolderPath
				.resolve(uniqueOutputName + AppConstants.DOT + fileProperties.getDefaultOutputFormat());

		File tempOutputFile = null;

		try {

			Files.createDirectories(uploadFolderPath);
			Files.createDirectories(outputFolderPath);
			Files.copy(multipartFile.getInputStream(), inputPath, StandardCopyOption.REPLACE_EXISTING);

			File tempInputFile = inputPath.toFile();
			tempOutputFile = Files.createFile(outputPath).toFile();

			tempInputFile.deleteOnExit();
			tempOutputFile.deleteOnExit();

			encode(attrs, tempOutputFile, tempInputFile);

			tempInputFile.delete();

		} catch (Exception e) {
			logger.error("Error converting input file", e);
			throw new RuntimeException(e);
		}

		logger.info("File converted successfully, duration: {}", Duration.between(begin, Instant.now()));

		cleanerService.clean(outputPath);
		return this.loadFileAsResource(outputPath);

	}

	private synchronized void encode(
			EncodingAttributes attrs,
			File tempOutputFile,
			File tempInputFile)
			throws InputFormatException, EncoderException {
		new Encoder().encode(new MultimediaObject(tempInputFile), tempOutputFile, attrs);
	}

	@Override
	public Resource loadFileAsResource(Path filePath) {

		try {
			Resource resource = new UrlResource(filePath.normalize().toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("File not found: " + filePath.toString());
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("File not found: " + filePath.toString(), e);
		}

	}

	private EncodingAttributes getEncodingAttributes(AudioAttributes audio, VideoAttributes video) {
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat(fileProperties.getDefaultOutputFormat());
		attrs.setAudioAttributes(audio);
		attrs.setVideoAttributes(video);
		return attrs;
	}

	private AudioAttributes getAudioAttributes() {
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec(fileProperties.getDefaultAudioCodec());
		audio.setBitRate(Integer.valueOf(fileProperties.getDefaultAudioBitRate()));
		audio.setChannels(Integer.valueOf(fileProperties.getDefaultAudioChannels()));
		audio.setSamplingRate(Integer.valueOf(fileProperties.getDefaultAudioSamplingRate()));
		return audio;
	}

	private VideoAttributes getVideoAttributes() {
		VideoAttributes video = new VideoAttributes();
		video.setCodec(fileProperties.getDefaultVideoCodec());
		video.setX264Profile(X264_PROFILE.BASELINE);
		video.setBitRate(Integer.valueOf(fileProperties.getDefaultVideoBitRate()));
		video.setFrameRate(Integer.valueOf(fileProperties.getDefaultVideoFrameRate()));
		video.setSize(new VideoSize(
				Integer.valueOf(fileProperties.getDefaultVideoWidth()),
				Integer.valueOf(fileProperties.getDefaultVideoHeight())));
		return video;
	}

}
