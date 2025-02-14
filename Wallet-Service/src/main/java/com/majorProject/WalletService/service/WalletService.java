package com.majorProject.WalletService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majorProject.WalletService.Enum.Type;
import com.majorProject.WalletService.dto.WalletResponseDTO;
import com.majorProject.WalletService.model.Wallet;
import com.majorProject.WalletService.repository.WalletRepo;

@Service
public class WalletService {

    @Autowired
    private WalletRepo walletRepo;

    public WalletResponseDTO walletDetails(String contact, Type type, double amount) {

        Wallet wallet = walletRepo.findByContact(contact);

        if(wallet == null){
            return WalletResponseDTO.
                    builder().
                    error("wallet is not present.").
                    messege("failed").
                    wallet(null).
                    build();

        }

        // check whether the user is sender or receiver in
        //  order for checking the balance if the user is sender.
        
        if(type == Type.SENDER){
            if(wallet.getBalance() < amount){
                return WalletResponseDTO.
                        builder().
                        error("wallet amount is less than the transaction amount.").
                        messege("txn failed").
                        wallet(null).
                        build();
            }
        }

        return WalletResponseDTO.
                builder().
                error(null).
                messege("success").
                wallet(wallet).
                build();


    }

    
}
