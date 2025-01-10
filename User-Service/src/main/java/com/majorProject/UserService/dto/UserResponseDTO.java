package com.majorProject.UserService.dto;

import com.majorProject.UserService.Enum.UserIdentifier;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {
    private String name;
    private String phoneNo;
    private String email;
    private String address;

    private UserIdentifier identifier;

//    private String userIdentifierValue;

    private String password;
}
