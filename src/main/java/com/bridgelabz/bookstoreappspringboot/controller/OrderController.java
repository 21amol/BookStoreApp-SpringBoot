package com.bridgelabz.bookstoreappspringboot.controller;

import com.bridgelabz.bookstoreappspringboot.dto.OrderDTO;
import com.bridgelabz.bookstoreappspringboot.dto.ResponseDTO;
import com.bridgelabz.bookstoreappspringboot.model.OrderData;
import com.bridgelabz.bookstoreappspringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  OrderService orderService;

  //-------------------------------POST-Operation---------------------------------------
  @PostMapping(value = {"/add"})
  public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO, @RequestParam String token) {
    OrderData orderData = orderService.placeOrder(orderDTO, token);
    ResponseDTO responseDTO = new ResponseDTO("Order Placed Successfully!!!", orderData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/get"})
  public ResponseEntity<ResponseDTO> getOrderData() {
    List<OrderData> orderList = orderService.getAllOrder();
    ResponseDTO responseDTO = new ResponseDTO("Order List Called Successfully!!!", orderList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //----------------------------GET-Operation----------------------------------
  @GetMapping(value = {"/getId/{orderId}"})
  public ResponseEntity<ResponseDTO> getOrderById( @PathVariable int orderId, @RequestParam String token) {
    Optional<OrderData> orderData = orderService.getOrderID(orderId, token);
    ResponseDTO responseDTO = new ResponseDTO("Order Id called Successfully!!!", orderData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  //-----------------------PUT-Operation-----------------------------
  @PutMapping("/cancel/{orderId}")
  public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int orderId, @RequestParam String token){
    Optional<OrderData> orderData = orderService.cancelOrder(orderId, token);
    ResponseDTO responseDTO = new ResponseDTO("Order Canceled Successfully!!!", orderData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }



}
