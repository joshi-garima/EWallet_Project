package com.majorProject.NotificationService.consumer;

import org.json.simple.JSONObject;

// import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.majorProject.NotificationService.utils.Constants;

@Service
public class UserCreatedNotification {

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ObjectMapper objectMapper;


    @KafkaListener(topics = {Constants.USER_CREATION_TOPIC}, groupId = "notification-service")
    public void sendNotification(String message) throws JsonMappingException, JsonProcessingException {
        JSONObject object = objectMapper.readValue(message, JSONObject.class);
        String name = (String) object.get(Constants.USER_NAME);
        String email = (String) object.get(Constants.USER_EMAIL);

        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Ewallet User Created");
        simpleMailMessage.setFrom("ewallet@jdbl-76.com");
        simpleMailMessage.setText("Welcome " + name + " to EWallet. User has been Created, wallet will be created shortly.");
        mailSender.send(simpleMailMessage);
        
        
    


    }



}
