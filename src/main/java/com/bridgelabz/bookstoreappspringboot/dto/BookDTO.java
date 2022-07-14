package com.bridgelabz.bookstoreappspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

  @NotNull(message = "Book Name cannot be Null" )
  public String bookName;

  @Pattern(regexp = "^[A-Z]{1}[A-Za-z]{1,}$", message = "Ivalid Author Name")
  public String authorName;

  @NotNull(message = "Description cannot be empty")
  public String bookDesc;

  public String bookImg;

  @Min(value = 100, message = "Book Price should be more than Rs 100")
  public int price;

  @Min(value = 1, message = "Quantity should not be less than 1 nos.")
  public int quantity;

}
