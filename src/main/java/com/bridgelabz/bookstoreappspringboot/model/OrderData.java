package com.bridgelabz.bookstoreappspringboot.model;

import com.bridgelabz.bookstoreappspringboot.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

  @Id
  @GeneratedValue
  private int orderId;
  @ManyToOne
  @JoinColumn(name = "bookId")
  private BookData bookId;

  @OneToOne
  @JoinColumn(name = "userId")
  private UserRegistrationData userId;
  private String address;
  private int totalQuantity;
  private int totalPrice;
  private LocalDate date;
  private boolean cancel;

  public OrderData(BookData bookId, UserRegistrationData userId, String address, int totalQuantity, int totalPrice) {
    this.bookId = bookId;
    this.userId = userId;
    this.address = address;
    this.totalQuantity = totalQuantity;
    this.date = LocalDate.now();
    this.totalPrice = totalPrice;
    this.cancel = isCancel();
  }
}
