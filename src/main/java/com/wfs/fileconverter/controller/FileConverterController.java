package com.wfs.fileconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wfs.fileconverter.service.ConverterService;

@CrossOrigin
@Controller
public class FileConverterController {

	private ConverterService converterService;

	@Autowired
	public FileConverterController(ConverterService converterService) {
		this.converterService = converterService;
	}

	// XXX implement some kind of security for this endpoint
	@PostMapping("/convert")
	public ResponseEntity<?> handleFileConvert(@RequestParam("file") MultipartFile file) {

		Resource output = converterService.convert(file);

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + output.getFilename() + "\"")
				.body(output);

	}

}
