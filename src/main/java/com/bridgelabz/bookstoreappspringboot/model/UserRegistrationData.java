package com.bridgelabz.bookstoreappspringboot.model;

import com.bridgelabz.bookstoreappspringboot.dto.UserRegistrationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_registration")

public class UserRegistrationData {

  @Id
  @GeneratedValue
  @Column(name = "UserId")
  private int userId;

  private String firstName;
  private String lastName;
  private String emailId;
  private String address;
  private String password;


  public UserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
    this.firstName = userRegistrationDTO.firstName;
    this.lastName = userRegistrationDTO.lastName;
    this.emailId = userRegistrationDTO.emailId;
    this.address = userRegistrationDTO.address;
    this.password = userRegistrationDTO.password;
  }

  public void updateData(UserRegistrationDTO userRegistrationDTO) {
    this.firstName = userRegistrationDTO.firstName;
    this.lastName = userRegistrationDTO.lastName;
    this.emailId = userRegistrationDTO.emailId;
    this.address = userRegistrationDTO.address;
    this.password = userRegistrationDTO.password;
  }
}
