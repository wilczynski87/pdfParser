package com.invoice.pdf_parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdfParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfParserApplication.class, args);
		System.out.println("\n\nApp 'PdfParser' has started :-)\n");
	}

}
