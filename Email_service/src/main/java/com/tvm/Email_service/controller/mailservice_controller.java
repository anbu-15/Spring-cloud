package com.tvm.Email_service.controller;


import com.tvm.Email_service.model.EmailRequest;
import com.tvm.Email_service.service.mail_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class mailservice_controller {
    @Autowired
    private mail_service emailService;


    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest);
        return "Send Successfully...";
    }

}
