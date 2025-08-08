package com.dipComputer.Dip.Computer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEnquiryMail(String name, String email, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("skillmate001@gmail.com");
        mailMessage.setSubject("New Enquiry from Website");
        mailMessage.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);

        mailSender.send(mailMessage);
    }
}
