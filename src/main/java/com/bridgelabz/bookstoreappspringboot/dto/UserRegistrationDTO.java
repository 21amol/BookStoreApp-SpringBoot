package com.bridgelabz.bookstoreappspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {

  @Pattern(regexp = "^[A-Z]{1}[A-Za-z]{1,}$", message = "Invalid First Name")
  public String firstName;

  @Pattern(regexp = "^[A-Z]{1}[A-Za-z]{1,}$", message = "Invalid Last Name")
  public String lastName;

  @Pattern(regexp = "^([a-z0-9-+]*)[.]{0,1}([0-9a-z]*)@([a-z]+)[.]{1}([a-z]+)$", message = "Invalid Email")
  public String emailId;

  @NotNull(message = "Address cannot be empty")
  public String address;

  @Pattern(regexp = "^([A-Za-z0-9]*){4,}$", message = "Password should be of min 4 digits or letters")
  public String password;

}
