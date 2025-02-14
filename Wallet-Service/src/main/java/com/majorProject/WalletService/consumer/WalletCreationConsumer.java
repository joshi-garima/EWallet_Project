package com.majorProject.WalletService.consumer;




import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.majorProject.WalletService.model.Wallet;
import com.majorProject.WalletService.repository.WalletRepo;
import com.majorProject.utils.CommonConstants;

@Service
public class WalletCreationConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${user.creation.time.balance}")
    private double balance;

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = {CommonConstants.USER_CREATION_TOPIC},
     groupId = "${Wallet.group.id}",
     containerFactory = "factory1"
    )
    public void walletCreation(String message, Acknowledgment acknowledgment) throws JsonMappingException, JsonProcessingException{
        try{


            JSONObject jsonObject = objectMapper.readValue(message, JSONObject.class);
            Integer userId = (Integer) jsonObject.get(CommonConstants.USER_ID);
            String contact = (String) jsonObject.get(CommonConstants.USER_CONTACT);
            // String name = (String) jsonObject.get(CommonConstants.USER_NAME);
            
            Wallet wallet = Wallet.builder().
                        contact(contact).
                        userId(userId).
                        balance(balance).
                        build();

            wallet = walletRepo.save(wallet);
            // 
            JSONObject o = new JSONObject();
            o.put(CommonConstants.USER_ID, userId);
            o.put(CommonConstants.USER_EMAIL, jsonObject.get(CommonConstants.USER_EMAIL));
            o.put(CommonConstants.USER_CREATION_TIME_WALLET, balance);
            o.put(CommonConstants.USER_NAME, jsonObject.get(CommonConstants.USER_NAME));

            
            kafkaTemplate.send(CommonConstants.WALLET_CREATION_TOPIC, objectMapper.writeValueAsString(o));
            

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        // will send acknowledgement to the kafka server that the messege has been read
        acknowledgment.acknowledge();
        
    }
}