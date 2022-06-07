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

@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartService cartService;

  //-------------------------------POST-Operation---------------------------------------
  @PostMapping(value = {"/add"})
  public ResponseEntity<ResponseDTO> addCart(@RequestBody CartDTO cartDTO, @RequestParam String token) {
    CartData cartData = cartService.addCart(cartDTO, token);
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
  public ResponseEntity<ResponseDTO> deleteCartData(@PathVariable int cartId, @RequestParam String token) {
    cartService.deleteCartData(cartId, token);
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
}
