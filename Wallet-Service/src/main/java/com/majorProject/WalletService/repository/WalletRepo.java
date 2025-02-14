package com.majorProject.WalletService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majorProject.WalletService.model.Wallet;

public interface WalletRepo extends JpaRepository<Wallet, Integer> {
    
    Wallet findByContact(String contact);

}
