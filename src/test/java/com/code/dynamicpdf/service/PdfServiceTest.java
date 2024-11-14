package com.code.dynamicpdf.service;

import com.code.dynamicpdf.entity.Item;
import com.code.dynamicpdf.entity.SellerBuyerInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PdfServiceTest {

    @InjectMocks
    private PdfService pdfService;

    @Mock
    private SpringTemplateEngine templateEngine;

    private final String PDF_DIRECTORY = "generated_pdfs/";

    @BeforeEach
    public void setUp() {
        // Create the PDF directory for testing
        File directory = new File(PDF_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }


    @Test
    public void testGeneratePdf_ExistingPdf() throws Exception {
        // Arrange
        SellerBuyerInfo sellerBuyerInfo = new SellerBuyerInfo();
        sellerBuyerInfo.setSeller("XYZ Pvt. Ltd.");
        sellerBuyerInfo.setBuyer("Vedant Computers");
        sellerBuyerInfo.setSellerGstin("29AABBCCDD121ZD");
        sellerBuyerInfo.setBuyerGstin("29AABBCCDD131ZD");
        sellerBuyerInfo.setSellerAddress("New Delhi, India");
        sellerBuyerInfo.setBuyerAddress("New Delhi, India");

        // Create an Item with a reference to the SellerBuyerInfo
        Item item = new Item(1L, "Product 1", "12 Nos", 123.00, 1476.00, sellerBuyerInfo);
        sellerBuyerInfo.setItems(Collections.singletonList(item));

        String expectedPdfFileName = "XYZ Pvt. Ltd._Vedant Computers.pdf";
        String expectedPdfPath = PDF_DIRECTORY + expectedPdfFileName;

        // Create the PDF file
        try (FileOutputStream outputStream = new FileOutputStream(expectedPdfPath)) {
            outputStream.write(new byte[]{1, 2, 3}); // Dummy content
        }


        String pdfPath = pdfService.generatePdf(sellerBuyerInfo);


        assertEquals(expectedPdfPath, pdfPath);
        assertTrue(Files.exists(Paths.get(pdfPath))); // Check if the PDF file still exists
        verify(templateEngine, never()).process(anyString(), any());
    }
}

