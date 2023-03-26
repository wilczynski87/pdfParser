package com.invoice.pdf_parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.invoice.pdf_parser.data.EmailDetails;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        // Creating a mime message
        MimeMessage mimeMessage
            = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
 
            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            if(details.getAttachmentByte() == null) return "There is no attachement";

            mimeMessageHelper.addAttachment(
                "fakturaPlac.pdf", new ByteArrayResource(details.getAttachmentByte()));
 
            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent successfully";
        }
 
        // Catch block to handle MessagingException
        catch (Exception e) {

            // Display message when exception occurred
            return "Error while sending mail!!! " + e;
        }
        
    }
    
}
