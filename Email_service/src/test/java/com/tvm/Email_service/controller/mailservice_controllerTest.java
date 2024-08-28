package com.tvm.Email_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.Email_service.model.EmailRequest;
import com.tvm.Email_service.service.mail_service;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(mailservice_controller.class)
public class    mailservice_controllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private mail_service emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSendEmail() throws Exception {
        // Arrange
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setToEmail("test@example.com");
        emailRequest.setSubject("Test Subject");
        emailRequest.setBody("Test Body");

        String emailRequestJson = objectMapper.writeValueAsString(emailRequest);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emailRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Send Successfully..."));

        // Capture the argument passed to sendEmail
        ArgumentCaptor<EmailRequest> argumentCaptor = ArgumentCaptor.forClass(EmailRequest.class);
        verify(emailService, times(1)).sendEmail(argumentCaptor.capture());

        // Assert the captured argument
        EmailRequest capturedEmailRequest = argumentCaptor.getValue();
        System.out.println("Captured To Email: " + capturedEmailRequest.getToEmail());
        System.out.println("Captured Subject: " + capturedEmailRequest.getSubject());
        System.out.println("Captured Body: " + capturedEmailRequest.getBody());

    }
}
