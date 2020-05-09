package com.wfs.fileconverter.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.wfs.fileconverter.FileProperties;
import com.wfs.fileconverter.service.CleanerService;

@Service
public class CleanerServiceImpl implements CleanerService {

	private Logger logger = LoggerFactory.getLogger(CleanerServiceImpl.class);

	private FileProperties fileProperties;

	public CleanerServiceImpl(FileProperties fileproperties) {
		this.fileProperties = fileproperties;
	}

	@Override
	@Async
	public void clean(Path path) {

		try {
			Thread.sleep(Long.valueOf(fileProperties.getOutputCleaningTime()));
			Files.delete(path);
			logger.info("Path {} was deleted", path.toString());
		} catch (Exception e) {
			logger.error("Error cleaning path", e);
		}

	}

}
