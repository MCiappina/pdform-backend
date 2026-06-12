package com.example.demo.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.Margin;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class PdfGenerationService {

    private final SpringTemplateEngine templateEngine;

    public PdfGenerationService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdfFromContext(String templateName, Context context) {
        
        // 1. Process the HTML template to a String (Thymeleaf doing its job)
        context.setVariable("baseUrl", "http://localhost:10000");
        String htmlContent = templateEngine.process(templateName, context);

        // 2. Spin up Playwright to act as our "Printer"
        try (Playwright playwright = Playwright.create()) {
            
            // Launch an invisible Chromium browser
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            
            // Feed your beautiful Flexbox HTML to the browser
            page.setContent(htmlContent);
            
            // Print it to PDF (matches your A4 and 20mm margin requirements)
            byte[] pdfBytes = page.pdf(new Page.PdfOptions()
                    .setFormat("A4")
                    .setMargin(new Margin().setTop("20mm").setRight("0mm").setBottom("10mm").setLeft("0mm"))
                    .setPrintBackground(true)); // Crucial: Ensures backgrounds and gradients render!
            
            browser.close();
            return pdfBytes;
        }
    }
}