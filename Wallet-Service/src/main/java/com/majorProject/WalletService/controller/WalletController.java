package com.majorProject.WalletService.controller;

import org.springframework.web.bind.annotation.RestController;

import com.majorProject.WalletService.model.Wallet;
import com.majorProject.WalletService.service.WalletService;
import com.majorProject.WalletService.Enum.Type;
import com.majorProject.WalletService.dto.WalletResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class WalletController {
    
    @Autowired
    private WalletService walletService;

    @GetMapping("/walletDetails")
    public ResponseEntity<WalletResponseDTO> walletDetils(
    @RequestParam("contact") String contact, 
    @RequestParam("type") Type type,
    @RequestParam("amount") Double amount) {
        WalletResponseDTO wallet = walletService.walletDetails(contact, type, amount);

        ResponseEntity<WalletResponseDTO> response = new ResponseEntity<>(wallet, HttpStatus.OK);
        return response;

    }
    
    
}
