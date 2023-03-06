package com.invoice.pdf_parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.pdf_parser.data.EmailDetails;
import com.invoice.pdf_parser.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendTestEmail")
    public String sendEmail(@RequestBody EmailDetails emailDetails) {

        return emailService.sendSimpleMail(emailDetails);
    }
    
}
