package com.wfs.fileconverter.service;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ConverterService {

	Resource convert(MultipartFile file);

	Resource loadFileAsResource(Path filePath);

}
