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

@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartService cartService;

  @PostMapping(value = {"/add"})
  public ResponseEntity<ResponseDTO> addCart(@RequestBody CartDTO cartDTO) {
    CartData cartData = cartService.addCart(cartDTO);
    ResponseDTO responseDTO = new ResponseDTO("Items Added to Cart Successfully!!!", cartData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  @GetMapping(value = {"/get"})
  public ResponseEntity<ResponseDTO> getCartsData() {
    List<CartData> cartList = cartService.getAllCart();
    ResponseDTO responseDTO = new ResponseDTO("Cart List Called Successfully!!!", cartList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @GetMapping(value = {"/get/{cartId}"})
  public ResponseEntity<ResponseDTO> getCartById(@PathVariable int cartId) {
    CartData cartData = cartService.getCartById(cartId);
    ResponseDTO responseDTO = new ResponseDTO("Success Call for Cart Id!!!", cartData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = {"/remove/{cartId}"})
  public ResponseEntity<ResponseDTO> deleteCartData(@PathVariable int cartId) {
    cartService.deleteCartData(cartId);
    ResponseDTO responseDTO = new ResponseDTO("Data DELETED Successfully!!!",
            "Cart id number: " + cartId + " --> DELETED!!!");
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @PutMapping(value = {"edit/{cartId}"})
  public ResponseEntity<ResponseDTO> updateCartData(@PathVariable int cartId, @RequestBody CartDTO cartDTO) {
    CartData cartData = cartService.updateCartData(cartId, cartDTO);
    ResponseDTO responseDTO = new ResponseDTO("Cart Updated to Cart Successfully!!!", cartData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }
}
