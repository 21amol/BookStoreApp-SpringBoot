package com.bridgelabz.bookstoreappspringboot.dto;

import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

  public int bookId;
  public int userId;
  public int quantity;
}
