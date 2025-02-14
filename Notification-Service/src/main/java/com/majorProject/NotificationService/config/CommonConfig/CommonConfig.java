package com.majorProject.NotificationService.config.CommonConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CommonConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public SimpleMailMessage getMailMessage() {
        return new SimpleMailMessage();
    }


}
