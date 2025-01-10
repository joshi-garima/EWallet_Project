package com.majorProject.UserService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.majorProject.UserService.Service.UserService;
import com.majorProject.UserService.dto.UserRequestDTO;
import com.majorProject.UserService.dto.UserResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController



@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUpdate")
    public ResponseEntity<UserResponseDTO> add(@RequestBody @Validated UserRequestDTO userRequestDTO) throws JsonProcessingException {
        UserResponseDTO user = userService.addUpdate(userRequestDTO);
        if(user != null){
            ResponseEntity response = new ResponseEntity(user, HttpStatus.OK);
            return response;
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

//u should not return users but return the dto, convert it into userResponse dto
// first then return
