package com.majorProject.UserService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.majorProject.UserService.dto.TxnDTO.SenderReceiverInfo;
import com.majorProject.UserService.dto.TxnDTO.UserTxnDTO;
import com.majorProject.UserService.model.Users;
import com.majorProject.utils.CommonConstants;

@Service
public class TxnUserService {
     
    @Autowired
    private KafkaTemplate<String, SenderReceiverInfo> kafkaTemplate;


    public String pushTopic(@AuthenticationPrincipal Users user, 
    @RequestBody UserTxnDTO reqUserTxnDTO) {
        
        SenderReceiverInfo info = SenderReceiverInfo.builder().
        senderContact(user.getPhoneNo()).
        recieverContact(reqUserTxnDTO.getRecieverContact()).
        amount(reqUserTxnDTO.getTxnAmount()).
        msg(reqUserTxnDTO.getMessage()).
        build();
    
        kafkaTemplate.send(CommonConstants.TXN_CREATION_TOPPIC, info);
        
        return "success";

    }

    
}
