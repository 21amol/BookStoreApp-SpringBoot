package com.bridgelabz.bookstoreappspringboot.service;

import com.bridgelabz.bookstoreappspringboot.dto.CartDTO;
import com.bridgelabz.bookstoreappspringboot.exception.BookStoreException;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.model.CartData;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import com.bridgelabz.bookstoreappspringboot.repository.CartRepo;
import com.bridgelabz.bookstoreappspringboot.repository.UserRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

  @Autowired
  CartRepo cartRepo;
  @Autowired
  UserRegistrationService userRegistrationService;
  @Autowired
  BookService bookService;

  @Autowired
  UserRegistrationRepo userRegistrationRepo;


  //---------------------------Adding a cart----------------------------------
  public CartData addCart(CartDTO cartDTO, String token) {
    Optional<UserRegistrationData> userRegistrationData = Optional.ofNullable(userRegistrationService.getUserDataById(token));
    BookData bookData = bookService.getBookById(cartDTO.getBookId());
    CartData cartData = new CartData(bookData, userRegistrationData.get(), cartDTO.quantity);
    return cartRepo.save(cartData);
  }


  //-------------------------------Getting all Cart details-----------------------
  public List<CartData> getAllCart() {
    return cartRepo.findAll();
  }


  //-------------------------Getting cart details by id and token---------------------
  public Optional<CartData> getCartById(int cartId, String token) {
    UserRegistrationData userRegistrationData = userRegistrationService.getUserDataById(token);
    Optional<CartData> cartData = cartRepo.findById(cartId);
    if (userRegistrationData != null) {
      return cartData;
    } else {
      throw (new BookStoreException("Cart Id not Found!!!"));
    }
  }

  //----------------Deleting a Cart---------------------------------
  public Object deleteCartData(int cartId, String token) {
    UserRegistrationData userRegistrationData = userRegistrationService.getUserDataById(token);
    Optional<CartData> cartData = cartRepo.findById(cartId);
    if (userRegistrationData != null && cartData.isPresent()) {
      cartRepo.delete(cartData.get());
      return "Cart Data with id " + cartId + " ---> DELETED!!!";
    } else {
      throw (new BookStoreException("Cart Id not Found!!!"));
    }
  }

  //-----------------------Updating the cart details--------------------
  public Optional<CartData> updateCartData(int cartId, String token, CartDTO cartDTO) {
    UserRegistrationData userRegistrationData = userRegistrationService.getUserDataById(token);
    BookData bookData = bookService.getBookById(cartDTO.getBookId());
    if (cartRepo.findById(cartId).isPresent() && userRegistrationData != null) {
      CartData cartData = new CartData(bookData, userRegistrationData, cartDTO.quantity);
      CartData search = cartRepo.save(cartData);
      return Optional.of(search);
    }
    throw (new BookStoreException("Record not Found"));
  }
}
