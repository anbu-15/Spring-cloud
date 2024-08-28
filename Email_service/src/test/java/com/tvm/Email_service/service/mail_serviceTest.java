package com.tvm.Email_service.service;

import com.tvm.Email_service.model.EmailRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test") // Use a separate profile if needed
public class mail_serviceTest {

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private mail_service emailService;

    @Test
    public void testSendEmail() {
        // Arrange
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setToEmail("test@example.com");
        emailRequest.setSubject("Test Subject");
        emailRequest.setBody("Test Body");

        // Act
        emailService.sendEmail(emailRequest);

        // Capture the argument passed to mailSender.send()
        ArgumentCaptor<SimpleMailMessage> argumentCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(argumentCaptor.capture());

        SimpleMailMessage sentMessage = argumentCaptor.getValue();

        // Assert the captured argument
        SimpleMailMessage capturedEmailRequest = argumentCaptor.getValue();
        System.out.println("Captured To Email: " + capturedEmailRequest.getFrom());
        System.out.println("Captured Subject: " + capturedEmailRequest.getSubject());
        System.out.println("Captured Body: " + capturedEmailRequest.getText());
    }
}
