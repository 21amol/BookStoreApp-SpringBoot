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

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserRegistrationController {

  @Autowired
  UserRegistrationService userRegistrationService;

  //-------------------------------POST-Operation---------------------------------------
  @PostMapping(value = {"/add"})
  public ResponseEntity<ResponseDTO> addUserData(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
    UserRegistrationData userRegistrationData = userRegistrationService.addUserData(userRegistrationDTO);
    ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully!!!", userRegistrationData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/get"})
  public ResponseEntity<ResponseDTO> getUserData() {
    List<UserRegistrationData> userDataList = userRegistrationService.getUserData();
    ResponseDTO responseDTO = new ResponseDTO("User List Called Successfully!!!", userDataList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/getId"})
  public ResponseEntity<ResponseDTO> getUserDataById(@RequestParam String token) {
    UserRegistrationData userRegistrationData = userRegistrationService.getUserDataById(token);
    ResponseDTO responseDTO = new ResponseDTO("Success Call for User Id!!!", userRegistrationData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/email"})
  public ResponseEntity<ResponseDTO> getUserDataByEmailId(@Valid @RequestParam String emailId) {
    Optional<UserRegistrationData> userDataList = userRegistrationService.getUserDataByEmailId(emailId);
    ResponseDTO responseDTO = new ResponseDTO("Success Call for Email Id!!!", userDataList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //-----------------------PUT-Operation-----------------------------
  @PutMapping(value = {"/update/{token}"})
  public ResponseEntity<ResponseDTO> updateData(@PathVariable String token,
                                                @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
    UserRegistrationData userRegistrationData = userRegistrationService.updateUserData(token,
            userRegistrationDTO);
    ResponseDTO responseDTO = new ResponseDTO("Data UPDATED Successfully!!!", userRegistrationData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //-------------------------------POST-Operation---------------------------------------
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

  @GetMapping("/getToken/{emailId}")
  public ResponseEntity<ResponseDTO> getToken(@PathVariable String emailId){
    String generatedToken=userRegistrationService.getToken(emailId);
    ResponseDTO responseDTO=new ResponseDTO("Token for mail id sent on mail successfully",generatedToken);
    return new ResponseEntity(responseDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/getrecordByToken/{token}")
  public ResponseEntity<ResponseDTO> getRecordByToken(@PathVariable String token) {
    UserRegistrationData user = userRegistrationService.getRecordByToken(token);
    ResponseDTO dto = new ResponseDTO("Data retrieved successfully",user);
    return new ResponseEntity(dto,HttpStatus.OK);
  }
}
