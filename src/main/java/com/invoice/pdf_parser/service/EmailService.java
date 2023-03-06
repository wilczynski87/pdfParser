package com.invoice.pdf_parser.service;

import com.invoice.pdf_parser.data.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
