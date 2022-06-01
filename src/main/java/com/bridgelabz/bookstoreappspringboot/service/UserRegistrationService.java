package com.bridgelabz.bookstoreappspringboot.service;

import com.bridgelabz.bookstoreappspringboot.dto.LoginDTO;
import com.bridgelabz.bookstoreappspringboot.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreappspringboot.exception.UserRegistrationException;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import com.bridgelabz.bookstoreappspringboot.repository.UserRegistrationRepo;
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

  public UserRegistrationData addUserData(UserRegistrationDTO userRegistrationDTO) {
    UserRegistrationData userRegistrationData = new UserRegistrationData(userRegistrationDTO);
    userRegistrationRepo.save(userRegistrationData);
    return userRegistrationData;
  }

  public List<UserRegistrationData> getUserData() {
    return userRegistrationRepo.findAll();
  }

  public UserRegistrationData getUserDataById(int userId) {
    return userRegistrationRepo.findById(userId)
            .orElseThrow(() -> new UserRegistrationException("User Id not Found!!!"));
  }

  public List<UserRegistrationData> getUserDataByEmailId(String emailId) {
    return userRegistrationRepo.findByEmailId(emailId);
  }

  public UserRegistrationData updateUserData(int userId, UserRegistrationDTO userRegistrationDTO) {
    UserRegistrationData userRegistrationData = this.getUserDataById(userId);
    userRegistrationData.updateData(userRegistrationDTO);
    return userRegistrationRepo.save(userRegistrationData);
  }

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
