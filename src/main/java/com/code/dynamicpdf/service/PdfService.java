package com.code.dynamicpdf.service;

import com.code.dynamicpdf.entity.SellerBuyerInfo;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class PdfService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    private final String PDF_DIRECTORY = "generated_pdfs/";

    public PdfService() {
        // Create directory if it doesn't exist
        File directory = new File(PDF_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public String generatePdf(SellerBuyerInfo sellerBuyerInfo) throws Exception {
        String pdfFileName = sellerBuyerInfo.getSeller() + "_" + sellerBuyerInfo.getBuyer() + ".pdf";
        String pdfPath = PDF_DIRECTORY + pdfFileName;

        // Check if the PDF already exists
        if (Files.exists(Paths.get(pdfPath))) {
            return pdfPath;
        }

        // Prepare Thymeleaf context
        Context context = new Context();
        context.setVariable("seller", sellerBuyerInfo.getSeller());
        context.setVariable("sellerGstin", sellerBuyerInfo.getSellerGstin());
        context.setVariable("sellerAddress", sellerBuyerInfo.getSellerAddress());
        context.setVariable("buyer", sellerBuyerInfo.getBuyer());
        context.setVariable("buyerGstin", sellerBuyerInfo.getBuyerGstin());
        context.setVariable("buyerAddress", sellerBuyerInfo.getBuyerAddress());
        context.setVariable("items", sellerBuyerInfo.getItems());


        String htmlContent = templateEngine.process("pdfTemplate", context);

        // Generate PDF
        try (FileOutputStream outputStream = new FileOutputStream(pdfPath)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent, PDF_DIRECTORY);
            builder.toStream(outputStream);
            builder.run(); // Generate the PDF
        }

        return pdfPath;
    }

    public byte[] downloadPdf(String pdfFileName) throws Exception {
        String pdfPath = PDF_DIRECTORY + pdfFileName;
        return Files.readAllBytes(Paths.get(pdfPath));
    }
}