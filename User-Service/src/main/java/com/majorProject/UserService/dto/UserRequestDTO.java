package com.majorProject.UserService.dto;

import com.majorProject.UserService.Enum.UserIdentifier;
import com.majorProject.UserService.model.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestDTO {

    private String name;
    @NotBlank(message = "Phone Number can not be blank")
    private String phoneNo;
    @NotBlank(message = "email can not be blank")
    private String email;
    private String address;
    @NotNull(message = "UserIdentifier is required")
    private UserIdentifier identifier;
    @NotBlank(message = "userIdentifier is required")
    private String userIdentifierValue;
    @NotBlank(message = "password can not be blank")
    private String password;

    public Users toUser() {

//    return Users.builder().name(this.name).
//            email(this.email).
//            phoneNo(this.phoneNo).
//            address(this.address).
//            userIdentifierValue(this.userIdentifierValue).
//            identifier(this.identifier).
//            enabled(true).
//            accountNonExpired(true).
//            accountNonLocked(true).
//            credentialsNonExpired(true).
//            build();
        return Users.builder().name(this.name).email(this.email).phoneNo(this.phoneNo).build();

    }
}
