package com.example.dynamic_pdf_generator.service;

import com.example.dynamic_pdf_generator.model.InvoiceRequest;
import com.example.dynamic_pdf_generator.model.Item;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGeneratorService {

    public byte[] generatePdf(InvoiceRequest invoiceRequest) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Font
        var font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        Table combinedTable = new Table(new float[]{150f, 150f, 150f, 150f});

        // Row 1: Seller and Buyer Information
        combinedTable.addCell(new Cell(1, 2).add(new Paragraph("Seller:\n" + invoiceRequest.getSeller() +
                "\n" + invoiceRequest.getSellerAddress() +
                "\nGSTIN: " + invoiceRequest.getSellerGstin())).setFont(font));
        combinedTable.addCell(new Cell(1, 2).add(new Paragraph("Buyer:\n" + invoiceRequest.getBuyer() +
                "\n" + invoiceRequest.getBuyerAddress() +
                "\nGSTIN: " + invoiceRequest.getBuyerGstin())).setFont(font));

        // Row 2: Headers
        combinedTable.addCell(new Cell().add(new Paragraph("Item")).setFont(font));
        combinedTable.addCell(new Cell().add(new Paragraph("Quantity")).setFont(font));
        combinedTable.addCell(new Cell().add(new Paragraph("Rate")).setFont(font));
        combinedTable.addCell(new Cell().add(new Paragraph("Amount")).setFont(font));

        // Row 3: Item Details
        for (Item item : invoiceRequest.getItems()) {
            combinedTable.addCell(new Cell().add(new Paragraph(item.getName())).setFont(font));
            combinedTable.addCell(new Cell().add(new Paragraph(String.valueOf(item.getQuantity()))).setFont(font));
            combinedTable.addCell(new Cell().add(new Paragraph(String.valueOf(item.getRate()))).setFont(font));
            combinedTable.addCell(new Cell().add(new Paragraph(String.valueOf(item.getAmount()))).setFont(font));
        }

        document.add(combinedTable);

        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
