package com.majorProject.UserService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.majorProject.UserService.dto.TxnDTO.UserTxnDTO;
import com.majorProject.UserService.dto.TxnDTO.Wallet;
import com.majorProject.UserService.dto.TxnDTO.WalletResponseDTO;
import com.majorProject.UserService.model.Users;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/userTxn")
public class UserValidationBeforeTxn {

    @Autowired
    private RestTemplate restTemplate;

    
    @SuppressWarnings("unchecked")
    @PostMapping("/start-txn")
    public String validateUserBeforeStartTxn(
    @RequestBody UserTxnDTO reqUserTxnDTO,
    @AuthenticationPrincipal Users user) {
        // sender is having a wallet associated with me or not
        // receiver is having a wallet associated with wallet
        // RestTemplate - for real time call

        ResponseEntity<WalletResponseDTO> senderInfo = restTemplate.exchange("http://localhost:8083/walletDetails?contact=" + 
        user.getPhoneNo(), HttpMethod.GET, null,
        ResponseEntity.class).getBody();

        ResponseEntity<WalletResponseDTO> receiverInfo = restTemplate.exchange("http://localhost:8083/walletDetails?contact=" + 
        reqUserTxnDTO.getRecieverContact(), HttpMethod.GET, null, 
        ResponseEntity.class).getBody();

        // the below code is when we are not using WalletResponseDTO - Wallet
        // if(senderInfo.getBody() == null && receiverInfo == null)_{
        //         return null;
        // }

        WalletResponseDTO dto1 = receiverInfo.getBody();
        WalletResponseDTO dto2 = senderInfo.getBody();

        if(dto1.getMessege() != "success"){
            return dto1.getError();
        }
        if(dto2.getMessege() != "success"){
            return dto1.getError();
        }

        // push the txn in the queue

        return "txn has been started, you will be notified shorlty once your txn done.";

        
    }
    

}
