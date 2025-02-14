package com.majorProject.NotificationService.consumer;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.majorProject.utils.CommonConstants;


@Service
public class WalletCreationNotification {
    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = {CommonConstants.WALLET_CREATION_TOPIC}, groupId = "notification-service" )
    public void sendNotification(String message) throws JsonMappingException, JsonProcessingException {


        JSONObject object = objectMapper.readValue(message, JSONObject.class);
        // String userId = (String) object.get(CommonConstants.USER_ID);
        String email = (String) object.get(CommonConstants.USER_EMAIL);
        String name = (String) object.get(CommonConstants.USER_NAME);
        String balance = String.valueOf(object.get(CommonConstants.USER_CREATION_TIME_WALLET));

        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Wallet Created");
        simpleMailMessage.setFrom("ewallet@jdbl-76.com");
        simpleMailMessage.setText(  "Welcome " + name + ". " + balance +  " has been addded to your wallet");
        mailSender.send(simpleMailMessage);

    }
}
