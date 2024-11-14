package com.code.dynamicpdf.controller;



import com.code.dynamicpdf.entity.SellerBuyerInfo;
import com.code.dynamicpdf.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<String> generatePdf(@RequestBody SellerBuyerInfo sellerBuyerInfo) {
        try {
            String pdfPath = pdfService.generatePdf(sellerBuyerInfo);
            File pdfFile = new File(pdfPath);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + pdfFile.getName());return ResponseEntity.ok()
                    .headers(headers)
                    .body("PDF generated successfully: " + pdfFile.getAbsolutePath());
        } catch (Exception e) {
            return ResponseEntity .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating PDF: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String fileName) {
        try {
            byte[] pdfBytes = pdfService.downloadPdf(fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + fileName);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
