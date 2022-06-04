package com.bridgelabz.bookstoreappspringboot.controller;

import com.bridgelabz.bookstoreappspringboot.dto.BookDTO;
import com.bridgelabz.bookstoreappspringboot.dto.OrderDTO;
import com.bridgelabz.bookstoreappspringboot.dto.ResponseDTO;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.model.CartData;
import com.bridgelabz.bookstoreappspringboot.model.OrderData;
import com.bridgelabz.bookstoreappspringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping(value = {"/add"})
  public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
    OrderData orderData = orderService.placeOrder(orderDTO);
    ResponseDTO responseDTO = new ResponseDTO("Order Placed Successfully!!!", orderData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  @GetMapping(value = {"/get"})
  public ResponseEntity<ResponseDTO> getOrderData() {
    List<OrderData> orderList = orderService.getAllOrder();
    ResponseDTO responseDTO = new ResponseDTO("Order List Called Successfully!!!", orderList);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @GetMapping(value = {"/get/{orderId}"})
  public ResponseEntity<ResponseDTO> getOrderById(@PathVariable int orderId) {
    OrderData orderData = orderService.getOrderID(orderId);
    ResponseDTO responseDTO = new ResponseDTO("Order Id called Successfully!!!", orderData);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = {"/remove/{orderId}"})
  public ResponseEntity<ResponseDTO> deleteOrderData(@PathVariable int orderId) {
    orderService.deleteOrderData(orderId);
    ResponseDTO responseDTO = new ResponseDTO("Order DELETED Successfully!!!",
            "Order id number: " + orderId + " --> DELETED!!!");
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @PutMapping("/cancel/{orderId}")
  public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable int orderId){
    OrderData orderData = orderService.cancelOrder(orderId);
    ResponseDTO responseDTO = new ResponseDTO("Order Canceled Successfully!!!", orderData);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }



}
