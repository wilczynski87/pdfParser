package com.invoice.pdf_parser.data;

public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private byte[] attachmentByte;

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMsgBody() {
        return this.msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public byte[] getAttachmentByte() {
        return this.attachmentByte;
    }

    public void setAttachmentByte(byte[] attachmentByte) {
        this.attachmentByte = attachmentByte;
    }

}
