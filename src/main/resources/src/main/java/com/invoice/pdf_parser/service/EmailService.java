package com.invoice.pdf_parser.service;

import com.invoice.pdf_parser.data.EmailDetails;

public interface EmailService {
    String sendMailWithAttachment(EmailDetails details);
}
