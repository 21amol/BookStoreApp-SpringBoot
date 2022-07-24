package com.bridgelabz.bookstoreappspringboot.service;

import com.bridgelabz.bookstoreappspringboot.dto.CartDTO;
import com.bridgelabz.bookstoreappspringboot.exception.BookStoreException;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.model.CartData;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import com.bridgelabz.bookstoreappspringboot.repository.BookRepo;
import com.bridgelabz.bookstoreappspringboot.repository.CartRepo;
import com.bridgelabz.bookstoreappspringboot.repository.UserRegistrationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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

  @Autowired
  BookRepo bookRepo;


  //---------------------------Adding a cart----------------------------------
  public CartData addCart(CartDTO cartDTO) {
    Optional<UserRegistrationData> userRegistrationData = userRegistrationRepo.findById(cartDTO.getUserId());
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
//  public Object deleteCartData(int cartId, String token) {
//    UserRegistrationData userRegistrationData = userRegistrationService.getUserDataById(token);
//    Optional<CartData> cartData = cartRepo.findById(cartId);
//    if (userRegistrationData != null && cartData.isPresent()) {
//      cartRepo.delete(cartData.get());
//      return "Cart Data with id " + cartId + " ---> DELETED!!!";
//    } else {
//      throw (new BookStoreException("Cart Id not Found!!!"));
//    }
//  }

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

  public CartData updateCartByIds(int id, int quantity) {
    Optional<CartData> cartData = cartRepo.findById(id);
    Optional<BookData>  bookData =bookRepo.findById(cartData.get().getBookData().getId());
    if(cartData.isPresent()) {
      if(quantity < bookData.get().getQuantity()) {
        cartData.get().setQuantity(quantity);
        cartRepo.save(cartData.get());
        bookData.get().setQuantity(bookData.get().getQuantity() - (quantity - cartData.get().getQuantity()));
        bookRepo.save(bookData.get());
        return cartData.get();
      }
      else {
        throw new BookStoreException("Requested quantity is not available");
      }

    }
    else {
      throw new BookStoreException("Cart Record doesn't exists");
    }
  }

  public List<CartData> getCartRecordByBookId(int bookId) {
    List<CartData> cart = cartRepo.findByBookID(bookId);
    if(cart.isEmpty()) {
      return null;
    }
    else {
      log.info("Cart record called successfully for book id "+bookId);
      return cart;
    }
  }


  public List<CartData> getCartRecordByUserId(Integer userId) {
    List<CartData> cart = cartRepo.findByUserID(userId);
//				if(cart.isEmpty()) {
//					//return null;
//					//throw new BookStoreException("Cart Record doesn't exists");
//				}
//				else {
    log.info("Cart record retrieved successfully for book id "+userId);
    return cart;
    //}
  }


  public CartData deleteCartRecord(int cartId) {
    Optional<CartData> cart = cartRepo.findById(cartId);
    Optional<BookData>  book = bookRepo.findById(cart.get().getBookData().getId());
    if(cart.isEmpty()) {
      throw new BookStoreException("Cart Record doesn't exists");
    }
    else {
      book.get().setQuantity(book.get().getQuantity() + cart.get().getQuantity());
      bookRepo.save(book.get());
      cartRepo.deleteById(cartId);
      log.info("Cart record deleted successfully for id "+cartId);
      return cart.get();
    }
  }



  public CartData decreaseQuantity(int id) {
    Optional<CartData> cart = cartRepo.findById(id);
    Optional<BookData>  book = bookRepo.findById(cart.get().getBookData().getId());
    if(cart.isEmpty()) {
      throw new BookStoreException("Cart Record doesn't exists");
    }
    else {
      if(cart.get().getQuantity() < book.get().getQuantity()) {
        cart.get().setQuantity(cart.get().getQuantity()-1);
        cartRepo.save(cart.get());
        log.info("Quantity in cart record updated successfully");
        book.get().setQuantity(book.get().getQuantity() - ((cart.get().getQuantity()-1) - cart.get().getQuantity()));
        bookRepo.save(book.get());
        return cart.get();
      }
      else {
        throw new BookStoreException("Requested quantity is not available");
      }
    }
  }


//  public CartData increaseQuantity(int id) {
//    Optional<CartData> cart = cartRepo.findById(id);
//    Optional<BookData>  book = bookRepo.findById(cart.get().getBookData().getId());
//    if(cart.isEmpty()) {
//      throw new BookStoreException("Cart Record doesn't exists");
//    }
//    else {
//      if(cart.get().getQuantity() < book.get().getQuantity()) {
//        cart.get().setQuantity(cart.get().getQuantity()+1);
//        cartRepo.save(cart.get());
//        log.info("Quantity in cart record updated successfully");
//        book.get().setQuantity(book.get().getQuantity() - ((cart.get().getQuantity()+1) - cart.get().getQuantity()));
//        bookRepo.save(book.get());
//        return cart.get();
//      }
//      else {
//        throw new BookStoreException("Requested quantity is not available");
//      }
//    }
//  }
//
//

}
