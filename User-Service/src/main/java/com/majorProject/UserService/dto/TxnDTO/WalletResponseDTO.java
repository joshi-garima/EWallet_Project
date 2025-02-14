package com.majorProject.UserService.dto.TxnDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletResponseDTO {
    private String messege;
    private String error;
    private Wallet wallet;
    
}
