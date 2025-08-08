package com.dipComputer.Dip.Computer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dipComputer.Dip.Computer.Dto.EnquiryRequest;
import com.dipComputer.Dip.Computer.service.EmailService;

@RestController
@RequestMapping("/api/enquiry")
public class EnquiryController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> submitEnquiry(@RequestBody EnquiryRequest request) {
        emailService.sendEnquiryMail(request.getName(), request.getEmail(), request.getMessage());
        return ResponseEntity.ok("Enquiry sent successfully!");
    }
}
