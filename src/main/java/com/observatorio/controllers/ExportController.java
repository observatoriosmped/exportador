package com.observatorio.exportador.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
        byte[] arquivo = Files.readAllBytes( Paths.get("/home/izaque/Documentos/files/" + fileName) );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=" + fileName);
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);
        return entity;
    }
}
