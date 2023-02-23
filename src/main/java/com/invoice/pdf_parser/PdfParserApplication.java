package com.invoice.pdf_parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PdfParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfParserApplication.class, args);
		log.info("\n\nApp 'PdfParser' has started :-)\n");
	}

}
