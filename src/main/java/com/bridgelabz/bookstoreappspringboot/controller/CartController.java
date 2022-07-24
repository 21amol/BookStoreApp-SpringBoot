package com.bridgelabz.bookstoreappspringboot.controller;

import com.bridgelabz.bookstoreappspringboot.dto.BookDTO;
import com.bridgelabz.bookstoreappspringboot.dto.CartDTO;
import com.bridgelabz.bookstoreappspringboot.dto.ResponseDTO;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.model.CartData;
import com.bridgelabz.bookstoreappspringboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartService cartService;

  //-------------------------------POST-Operation---------------------------------------
  @PostMapping(value = {"/add"})
  public ResponseEntity<ResponseDTO> addCart(@RequestBody CartDTO cartDTO) {
    CartData cartData = cartService.addCart(cartDTO);
    ResponseDTO responseDTO = new ResponseDTO("Items Added to Cart Successfully!!!", cartData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/get"})
  public ResponseEntity<ResponseDTO> getCartsData() {
    List<CartData> cartList = cartService.getAllCart();
    ResponseDTO responseDTO = new ResponseDTO("Cart List Called Successfully!!!", cartList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/getId/{cartId}"})
  public ResponseEntity<ResponseDTO> getCartById(@RequestParam String token, @PathVariable int cartId) {
    Optional<CartData> cartData = cartService.getCartById(cartId, token);
    ResponseDTO responseDTO = new ResponseDTO("Success Call for Cart Id!!!", cartData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //-------------------------DELETE-Operation----------------------------
  @DeleteMapping(value = {"/remove/{cartId}"})
  public ResponseEntity<ResponseDTO> deleteCartData(@PathVariable int cartId) {
    cartService.deleteCartRecord(cartId);
    ResponseDTO responseDTO = new ResponseDTO("Data DELETED Successfully!!!",
            "Cart with token and ID: " + cartId + " --> DELETED!!!");
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //-----------------------PUT-Operation-----------------------------
  @PutMapping(value = {"/edit/{cartId}"})
  public ResponseEntity<ResponseDTO> updateCartData(@PathVariable int cartId, @RequestParam String token,
                                                    @RequestBody CartDTO cartDTO) {
    Optional<CartData> cartData =  cartService.updateCartData(cartId, token, cartDTO);
    ResponseDTO responseDTO = new ResponseDTO("Cart Updated Successfully!!!", cartData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  @GetMapping("/cartid/{id}")
  public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer id, @RequestParam Integer quantity) {
    CartData newCart = cartService.updateCartByIds(id, quantity);
    ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully!!!", newCart);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/getCartByBookId/{bookId}")
  public ResponseEntity<ResponseDTO> getCartRecordByBookId(@PathVariable int bookId){
    List<CartData> newCart = cartService.getCartRecordByBookId(bookId);
    ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newCart);
    return new ResponseEntity<ResponseDTO>(dto,HttpStatus.OK);
  }


  @GetMapping("/retrieveCartByUserId/{userId}")
  public ResponseEntity<ResponseDTO> getCartRecordByUserId(@PathVariable int userId){
    List<CartData> newCart = cartService.getCartRecordByUserId(userId);
    ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newCart);
    return new ResponseEntity<>(dto,HttpStatus.OK);
  }

  @GetMapping("/decreaseQuantity/{id}")
  public ResponseEntity<ResponseDTO> decreaseQuantity(@PathVariable int id){
    CartData newCart = cartService.decreaseQuantity(id);
    ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",newCart);
    return new ResponseEntity<>(dto,HttpStatus.OK);
  }



//
//  @GetMapping("/increaseQuantity/{id}")
//  public ResponseEntity<ResponseDTO> increaseQuantity(@PathVariable int id){
//    CartData newCart = cartService.increaseQuantity(id);
//    ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",newCart);
//    return new ResponseEntity<>(dto,HttpStatus.OK);
//  }


}
