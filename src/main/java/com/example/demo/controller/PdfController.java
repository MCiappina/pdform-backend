package com.example.demo.controller;

import com.example.demo.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadPdf() throws Exception {
        byte[] pdfContent = pdfService.generatePdf("Hello from Spring Boot and PDFBox!");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }
}