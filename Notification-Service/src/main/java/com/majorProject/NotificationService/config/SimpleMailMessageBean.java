package com.majorProject.NotificationService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class SimpleMailMessageBean {

    @Bean
    public SimpleMailMessage getMailMessage() {
        return new SimpleMailMessage();
    }

}
