
package com.bridgelabz.bookstoreappspringboot.controller;

import com.bridgelabz.bookstoreappspringboot.dto.BookDTO;
import com.bridgelabz.bookstoreappspringboot.dto.ResponseDTO;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired
  BookService bookService;

  //-------------------------------POST-Operation---------------------------------------
  @PostMapping(value = {"/add"})
  public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody BookDTO bookDTO) {
    BookData bookData = bookService.insertBook(bookDTO);
    ResponseDTO responseDTO = new ResponseDTO("Book Added Successfully!!!", bookData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/get"})
  public ResponseEntity<ResponseDTO> getBooksData() {
    List<BookData> bookList = bookService.getAllBooks();
    ResponseDTO responseDTO = new ResponseDTO("Books List Called Successfully!!!", bookList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/get/{id}"})
  public ResponseEntity<ResponseDTO> getBookById(@PathVariable int id) {
    BookData bookData = bookService.getBookById(id);
    ResponseDTO responseDTO = new ResponseDTO("Success Call for Book Id!!!", bookData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //-------------------------DELETE-Operation----------------------------
  @DeleteMapping(value = {"/remove/{id}"})
  public ResponseEntity<ResponseDTO> deleteBookData(@PathVariable int id) {
    bookService.deleteBookData(id);
    ResponseDTO responseDTO = new ResponseDTO("Data DELETED Successfully!!!",
            "Book id number: " + id + " --> DELETED!!!");
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/bookname"})
  public ResponseEntity<ResponseDTO> getBooksData(@Valid @RequestParam String bookName) {
    List<BookData> bookList = bookService.getBookByName(bookName);
    ResponseDTO responseDTO = new ResponseDTO("Book Called Successfully!!!", bookList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //-----------------------PUT-Operation-----------------------------
  @PutMapping(value = {"/update/{id}"})
  public ResponseEntity<ResponseDTO> updateData(@PathVariable int id, @Valid @RequestBody BookDTO bookDTO) {
    BookData bookData = bookService.updateBookData(id, bookDTO);
    ResponseDTO responseDTO = new ResponseDTO("Data UPDATED Successfully!!!", bookData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/sortAsc"})
  public ResponseEntity<ResponseDTO> sortByPriceAsc() {
    List<BookData> bookList = bookService.sortByPriceAsc();
    ResponseDTO responseDTO = new ResponseDTO("Sorting Called Successfully!!!", bookList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/sortDesc"})
  public ResponseEntity<ResponseDTO> sortByPriceDesc() {
    List<BookData> bookList = bookService.sortByPriceDesc();
    ResponseDTO responseDTO = new ResponseDTO("Sorting Called Successfully!!!", bookList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/authorname"})
  public ResponseEntity<ResponseDTO> getBooksDataByAuthor(@Valid @RequestParam String authorName) {
    List<BookData> bookList = bookService.getBookByAuthorName(authorName);
    ResponseDTO responseDTO = new ResponseDTO("Book by Author Name Called Successfully!!!", bookList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }
}
