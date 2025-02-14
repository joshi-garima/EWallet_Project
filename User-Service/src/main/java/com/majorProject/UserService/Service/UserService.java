package com.majorProject.UserService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.majorProject.UserService.Repository.UserRepo;
import com.majorProject.UserService.dto.UserDTO.UserRequestDTO;
import com.majorProject.UserService.dto.UserDTO.UserResponseDTO;
import com.majorProject.UserService.model.Users;
import com.majorProject.utils.CommonConstants;

import org.json.simple.JSONObject;

// import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.SQLIntegrityConstraintViolationException;


@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("$(user.Authority)")
    private String userAuthority;


//    kafkaTemplate<T1, T2> -> T1 represents the topic,
//    T2 represents the message type
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public UserResponseDTO addUpdate(UserRequestDTO userRequestDTO) throws JsonProcessingException, SQLIntegrityConstraintViolationException {
        Users user = userRequestDTO.toUser();
        user.setAuthorities(userAuthority);
        user.setPassword(encoder.encode(userRequestDTO.getPassword()));
        Users resultUserFromDb = userRepo.findByEmail(userRequestDTO.getEmail());
        JSONObject jsonObject = new JSONObject();
        

        if(resultUserFromDb == null) {
            jsonObject.put(CommonConstants.NEW_USER, "true");
        }

//        Implementation of Queue - as we want to send notification from the user Creation

        Users userFromDb =  userRepo.save(user);
        jsonObject.put(CommonConstants.USER_CONTACT, user.getPhoneNo());
        jsonObject.put(CommonConstants.USER_EMAIL, user.getEmail());
        jsonObject.put(CommonConstants.USER_IDENTIFIER, user.getIdentifier());
        jsonObject.put(CommonConstants.USER_IDENTIFIER_VALUE, user.getUserIdentifierValue());
        jsonObject.put(CommonConstants.USER_NAME, user.getName());
        jsonObject.put(CommonConstants.USER_ID, user.getId());

        kafkaTemplate.send(CommonConstants.USER_CREATION_TOPIC, objectMapper.writeValueAsString(jsonObject));


        return UserResponseDTO.builder().
                name(userFromDb.getUsername()).
                address(userFromDb.getAddress()).
                phoneNo(userFromDb.getPhoneNo()).
                identifier(userFromDb.getIdentifier()).
                email(userFromDb.getEmail()).
                build();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
        Users users = userRepo.findByEmail(username);
        return users;
    }

}
