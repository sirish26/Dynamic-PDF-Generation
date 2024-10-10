package com.example.dynamic_pdf_generator.controller;

import com.example.dynamic_pdf_generator.model.InvoiceRequest;
import com.example.dynamic_pdf_generator.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfGeneratorService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generatePdf(@RequestBody InvoiceRequest invoiceRequest) {
        try {
            // Generate PDF as byte array
            byte[] pdfData = pdfService.generatePdf(invoiceRequest);

            // Create headers for downloading the PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            headers.setContentDispositionFormData("attachment", "invoice.pdf");

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
