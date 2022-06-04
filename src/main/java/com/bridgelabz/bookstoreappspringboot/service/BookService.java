package com.bridgelabz.bookstoreappspringboot.service;

import com.bridgelabz.bookstoreappspringboot.dto.BookDTO;
import com.bridgelabz.bookstoreappspringboot.exception.BookStoreException;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  @Autowired
  BookRepo bookRepo;

  public BookData insertBook(BookDTO bookDTO ) {
    BookData bookData = new BookData(bookDTO);
    bookRepo.save(bookData);
    return bookData;
  }

  public List<BookData> getAllBooks() {
    return bookRepo.findAll();
  }

  public BookData getBookById(int cartId) {
    return bookRepo.findById(cartId)
            .orElseThrow(() -> new BookStoreException("Book Id not Found!!!"));
  }

  public void deleteBookData(int cartId) {
    BookData bookData = this.getBookById(cartId);
    bookRepo.delete(bookData);
  }

  public List<BookData> getBookByName(String bookName) {
    return bookRepo.findByBookName(bookName);
  }

  public BookData updateBookData(int id, BookDTO bookDTO) {
    BookData bookData = this.getBookById(id);
    bookData.updateData(bookDTO);
    return bookRepo.save(bookData);
  }

  public List<BookData> sortByPriceAsc() {
    return bookRepo.sortByPriceAsc();
  }

  public List<BookData> sortByPriceDesc() {
    return bookRepo.sortByPriceDesc();
  }

  public List<BookData> getBookByAuthorName(String authorName) {
    return bookRepo.findByauthorName(authorName);
  }

}
