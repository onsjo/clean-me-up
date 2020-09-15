package com.effcode.clean.me.rest.dto;

public class EmailModel {

    private String address;
    private String subject;
    private String content;

    public EmailModel(String address, String subject, String content) {
        this.address = address;
        this.subject = subject;
        this.content = content;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "EmailModel{" +
                "address='" + address + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
