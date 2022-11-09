package com.observatorio.exportador.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(value = "/exportar-arquivo")
public class ExportController {
	
	@GetMapping("/por-nome")
	public HttpEntity<byte[]> fileDownload(@RequestParam String fileName) throws IOException {
		byte[] arquivo = null;
	         
		String contentType = "text/csv";
		String headerValue = "attachment; filename=" + "\"" + fileName + "\"";

		try {
			arquivo = Files.readAllBytes( Paths.get("/home/garsoft/Documentos/" + fileName) );
			return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			.header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
			.body(arquivo);
		}
		catch (Exception e) {
			System.out.println("can't export file");
			return new ResponseEntity<byte[]>(arquivo, HttpStatus.BAD_GATEWAY);
		}
    }
}
