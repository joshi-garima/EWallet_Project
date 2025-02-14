package com.majorProject.UserService.dto.TxnDTO;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class Wallet {
    
    private Integer id;

    private String contact;

    private Double balance;

    private Integer userId;

    private String name;

     @CreationTimestamp
    protected Date createdOn;

    @UpdateTimestamp
    protected Date updateOn;


}
