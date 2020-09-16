package com.effcode.clean.me.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EmailModel {

    @NotEmpty(message = "Email must not be empty.")
    @Email(message = "Email was invalid.")
    private String address;

    @NotEmpty(message = "Subject must not be empty.")
    // https://stackoverflow.com/questions/1592291/what-is-the-email-subject-length-limit
    @Size(max = 78, message = "Subject must be below 78 characters.")
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

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
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
