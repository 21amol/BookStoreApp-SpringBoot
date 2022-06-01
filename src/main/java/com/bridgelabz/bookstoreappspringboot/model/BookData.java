package com.bridgelabz.bookstoreappspringboot.model;

import com.bridgelabz.bookstoreappspringboot.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book")
public class BookData {
  @Id
  @GeneratedValue
  @Column(name = "bookId")
  private int id;


  private String bookName;
  private String authorName;
  private String bookDesc;
  private String bookImg;
  private int price;
  private int quantity;

  public BookData(BookDTO bookDTO) {
    this.bookName = bookDTO.bookName;
    this.authorName = bookDTO.authorName;
    this.bookDesc = bookDTO.bookDesc;
    this.bookImg = bookDTO.bookImg;
    this.price = bookDTO.price;
    this.quantity = bookDTO.quantity;

  }

  public void updateData(BookDTO bookDTO) {
    this.bookName = bookDTO.bookName;
    this.authorName = bookDTO.authorName;
    this.bookDesc = bookDTO.bookDesc;
    this.bookImg = bookDTO.bookImg;
    this.price = bookDTO.price;
    this.quantity = bookDTO.quantity;

  }
}
