package com.wfs.fileconverter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "file")
@Component
public class FileProperties {

	private String defaultAudioBitRate;
	private String defaultAudioChannels;
	private String defaultAudioCodec;
	private String defaultAudioSamplingRate;
	private String defaultOutputFormat;
	private String defaultVideoBitRate;
	private String defaultVideoCodec;
	private String defaultVideoFrameRate;
	private String defaultVideoHeight;
	private String defaultVideoWidth;
	private String inputPrefix;
	private String outputCleaningTime;
	private String outputDir;
	private String outputPrefix;
	private String tmpExtension;
	private String uploadDir;

	public String getDefaultAudioBitRate() {
		return defaultAudioBitRate;
	}

	public String getDefaultAudioChannels() {
		return defaultAudioChannels;
	}

	public String getDefaultAudioCodec() {
		return defaultAudioCodec;
	}

	public String getDefaultAudioSamplingRate() {
		return defaultAudioSamplingRate;
	}

	public String getDefaultOutputFormat() {
		return defaultOutputFormat;
	}

	public String getDefaultVideoBitRate() {
		return defaultVideoBitRate;
	}

	public String getDefaultVideoCodec() {
		return defaultVideoCodec;
	}

	public String getDefaultVideoFrameRate() {
		return defaultVideoFrameRate;
	}

	public String getDefaultVideoHeight() {
		return defaultVideoHeight;
	}

	public String getDefaultVideoWidth() {
		return defaultVideoWidth;
	}

	public String getInputPrefix() {
		return inputPrefix;
	}

	public String getOutputCleaningTime() {
		return outputCleaningTime;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public String getOutputPrefix() {
		return outputPrefix;
	}

	public String getTmpExtension() {
		return tmpExtension;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setDefaultAudioBitRate(String defaultAudioBitRate) {
		this.defaultAudioBitRate = defaultAudioBitRate;
	}

	public void setDefaultAudioChannels(String defaultAudioChannels) {
		this.defaultAudioChannels = defaultAudioChannels;
	}

	public void setDefaultAudioCodec(String defaultAudioCodec) {
		this.defaultAudioCodec = defaultAudioCodec;
	}

	public void setDefaultAudioSamplingRate(String defaultAudioSamplingRate) {
		this.defaultAudioSamplingRate = defaultAudioSamplingRate;
	}

	public void setDefaultOutputFormat(String defaultOutputFormat) {
		this.defaultOutputFormat = defaultOutputFormat;
	}

	public void setDefaultVideoBitRate(String defaultVideoBitRate) {
		this.defaultVideoBitRate = defaultVideoBitRate;
	}

	public void setDefaultVideoCodec(String defaultVideoCodec) {
		this.defaultVideoCodec = defaultVideoCodec;
	}

	public void setDefaultVideoFrameRate(String defaultVideoFrameRate) {
		this.defaultVideoFrameRate = defaultVideoFrameRate;
	}

	public void setDefaultVideoHeight(String defaultVideoHeight) {
		this.defaultVideoHeight = defaultVideoHeight;
	}

	public void setDefaultVideoWidth(String defaultVideoWidth) {
		this.defaultVideoWidth = defaultVideoWidth;
	}

	public void setInputPrefix(String inputPrefix) {
		this.inputPrefix = inputPrefix;
	}

	public void setOutputCleaningTime(String outputCleaningTime) {
		this.outputCleaningTime = outputCleaningTime;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public void setOutputPrefix(String outputPrefix) {
		this.outputPrefix = outputPrefix;
	}

	public void setTmpExtension(String tmpExtension) {
		this.tmpExtension = tmpExtension;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

}
