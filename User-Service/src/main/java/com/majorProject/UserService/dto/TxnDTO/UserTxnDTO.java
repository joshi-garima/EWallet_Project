package com.majorProject.UserService.dto.TxnDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTxnDTO {
    
    private String txnAmount;

    private String message;

    private String recieverContact;
}
