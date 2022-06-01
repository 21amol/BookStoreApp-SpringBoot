package com.bridgelabz.bookstoreappspringboot.controller;

import com.bridgelabz.bookstoreappspringboot.dto.LoginDTO;
import com.bridgelabz.bookstoreappspringboot.dto.ResponseDTO;
import com.bridgelabz.bookstoreappspringboot.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import com.bridgelabz.bookstoreappspringboot.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {

  @Autowired
  UserRegistrationService userRegistrationService;

  @PostMapping(value = {"/add"})
  public ResponseEntity<ResponseDTO> addUserData(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
    UserRegistrationData userRegistrationData = userRegistrationService.addUserData(userRegistrationDTO);
    ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully!!!", userRegistrationData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  @GetMapping(value = {"/get"})
  public ResponseEntity<ResponseDTO> getUserData() {
    List<UserRegistrationData> userDataList = userRegistrationService.getUserData();
    ResponseDTO responseDTO = new ResponseDTO("User List Called Successfully!!!", userDataList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @GetMapping(value = {"/get/{userId}"})
  public ResponseEntity<ResponseDTO> getUserDataById(@PathVariable int userId) {
    UserRegistrationData userRegistrationData = userRegistrationService.getUserDataById(userId);
    ResponseDTO responseDTO = new ResponseDTO("Success Call for User Id!!!", userRegistrationData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @GetMapping(value = {"/email"})
  public ResponseEntity<ResponseDTO> getUserDataByEmailId(@Valid @RequestParam String emailId) {
    List<UserRegistrationData> userDataList = userRegistrationService.getUserDataByEmailId(emailId);
    ResponseDTO responseDTO = new ResponseDTO("Success Call for Email Id!!!", userDataList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @PutMapping(value = {"/update/{userId}"})
  public ResponseEntity<ResponseDTO> updateData(@PathVariable int userId,
                                               @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
    UserRegistrationData userRegistrationData = userRegistrationService.updateUserData(userId,
            userRegistrationDTO);
    ResponseDTO responseDTO = new ResponseDTO("Data UPDATED Successfully!!!", userRegistrationData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @PostMapping(value = {"/login"})
  public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
    Optional<UserRegistrationData> login = userRegistrationService.loginUser(loginDTO);

    if (login != null) {
      ResponseDTO dto = new ResponseDTO("Login Successfully!!!", login);
      return new ResponseEntity<>(dto, HttpStatus.OK);

    } else {
      ResponseDTO dto = new ResponseDTO("Login Failed!!!", login);
      return new ResponseEntity<>(dto, HttpStatus.OK);
    }
  }
}
