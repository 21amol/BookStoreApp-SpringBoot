package com.bridgelabz.bookstoreappspringboot.service;

import com.bridgelabz.bookstoreappspringboot.dto.BookDTO;
import com.bridgelabz.bookstoreappspringboot.email.EmailService;
import com.bridgelabz.bookstoreappspringboot.exception.BookStoreException;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import com.bridgelabz.bookstoreappspringboot.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  @Autowired
  BookRepo bookRepo;
  @Autowired
  EmailService emailService;

//----------------------------------Adding a Book-------------------------------
  public BookData insertBook(BookDTO bookDTO) {
    BookData bookData = new BookData(bookDTO);
    bookRepo.save(bookData);
    return bookData;
  }

  //-------------------------------Getting the list of books--------------------
  public List<BookData> getAllBooks() {
    return bookRepo.findAll();
  }


  //--------------------------------Getting a booy by its id---------------------
  public BookData getBookById(int id) {
    return bookRepo.findById(id)
            .orElseThrow(() -> new BookStoreException("Book Id not Found!!!"));
  }

  //--------------------------------Deleting a book-----------------------------
  public void deleteBookData(int id) {
    BookData bookData = this.getBookById(id);
    bookRepo.delete(bookData);
  }

  //---------------------------------Getting a book by its name-------------------
  public List<BookData> getBookByName(String bookName) {
    return bookRepo.findByBookName(bookName);
  }


  //-------------------------------Updating the book data------------------------
  public BookData updateBookData(int id, BookDTO bookDTO) {
    BookData bookData = this.getBookById(id);
    bookData.updateData(bookDTO);
    return bookRepo.save(bookData);
  }

  //----------------------------------Sorting Books----------------------------
  public List<BookData> sortByPriceAsc() {
    return bookRepo.sortByPriceAsc();
  }

  public List<BookData> sortByPriceDesc() {
    return bookRepo.sortByPriceDesc();
  }


  //----------------------------------Getting b ook by its Author name-------------------------
  public List<BookData> getBookByAuthorName(String authorName) {
    return bookRepo.findByauthorName(authorName);
  }

}
