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

  public CartData addCart(CartDTO cartDTO) {
    Optional<UserRegistrationData> userRegistrationData = userRegistrationRepo.findById(cartDTO.getUserId());
    BookData bookData = bookService.getBookById(cartDTO.getBookId());
    CartData cartData = new CartData (bookData, userRegistrationData.get(), cartDTO.quantity);
    return cartRepo.save(cartData);
  }

  public List<CartData> getAllCart() {
    return cartRepo.findAll();
  }

  public CartData getCartById(int cartId) {
    return cartRepo.findById(cartId)
            .orElseThrow(() -> new BookStoreException("Cart Id not Found!!!"));
  }

  public void deleteCartData(int cartId) {
    CartData cartData = this.getCartById(cartId);
    cartRepo.delete(cartData);
  }

  public CartData updateCartData(int cartId, CartDTO cartDTO ) {
    CartData cartData = this.getCartById(cartId);
    cartData.updateCartData(cartDTO);
    return cartRepo.save(cartData);
  }
}
