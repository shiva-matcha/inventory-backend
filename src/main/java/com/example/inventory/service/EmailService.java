package com.example.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${inventory.alert.recipient}")
    private String recipient;

    public void sendLowStockAlert(String productName, int quantity) {
        String subject = " Low Stock Alert: " + productName;
        String body = "Product '" + productName + "' has low stock.\nCurrent quantity: " + quantity;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
