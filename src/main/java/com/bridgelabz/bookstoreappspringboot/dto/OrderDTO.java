package com.bridgelabz.bookstoreappspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

  public int userId;
  public int bookId;
  public int totalQuantity;
 // public int totalPrice;
  public String address;
 // public LocalDate date;

}
