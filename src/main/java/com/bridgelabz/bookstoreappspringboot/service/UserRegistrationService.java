package com.bridgelabz.bookstoreappspringboot.service;

import com.bridgelabz.bookstoreappspringboot.dto.LoginDTO;
import com.bridgelabz.bookstoreappspringboot.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreappspringboot.email.EmailService;
import com.bridgelabz.bookstoreappspringboot.exception.BookStoreException;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import com.bridgelabz.bookstoreappspringboot.repository.UserRegistrationRepo;
import com.bridgelabz.bookstoreappspringboot.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class UserRegistrationService {

  @Autowired
  UserRegistrationRepo userRegistrationRepo;
  @Autowired
  TokenUtil tokenUtil;
  @Autowired
  EmailService emailService;

  //------------------Adding a User -------------------------
  public UserRegistrationData addUserData(UserRegistrationDTO userRegistrationDTO) {
    UserRegistrationData userRegistrationData = new UserRegistrationData(userRegistrationDTO);
    userRegistrationRepo.save(userRegistrationData);
    String token=tokenUtil.createToken(userRegistrationData.getUserId());
    emailService.sendEmail(userRegistrationData.getEmailId(), "Token",
            "Registration SuccessFull and Generated Token is--> "+token);
    return userRegistrationData;
  }

  //--------------------------Getting full user list--------------------------
  public List<UserRegistrationData> getUserData() {
    return userRegistrationRepo.findAll();
  }


  //------------------Getting user data by token--------------------
  public UserRegistrationData getUserDataById(String token) {
    int userId = tokenUtil.decodeToken(token);
    return userRegistrationRepo.findById(userId).orElseThrow(() -> new BookStoreException("User Id not Found!!!"));
  }

  //----------------Getting user Data by Email id--------------------
  public List<UserRegistrationData> getUserDataByEmailId(String emailId) {
    return userRegistrationRepo.findByEmailId(emailId);
  }

  //----------------Updating User Data-----------------------
  public UserRegistrationData updateUserData(String token, UserRegistrationDTO userRegistrationDTO) {
    UserRegistrationData userRegistrationData = this.getUserDataById(token);
    userRegistrationData.updateData(userRegistrationDTO);
    return userRegistrationRepo.save(userRegistrationData);
  }

  //-----------------Login into User Account-------------------
  public Optional<UserRegistrationData> loginUser(LoginDTO loginDTO) {
    Optional<UserRegistrationData> userLogin = userRegistrationRepo.findByEmailIdAndPassword(loginDTO.emailId,
            loginDTO.password);

    if (userLogin.isPresent()) {
      log.info("Login Successfully!!!");
      return userLogin;

    } else {
      log.error("User not Found!!!");
    }
    return null;
  }
}
