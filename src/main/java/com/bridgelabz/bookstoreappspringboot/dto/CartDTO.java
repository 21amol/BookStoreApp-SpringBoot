package com.bridgelabz.bookstoreappspringboot.dto;

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
