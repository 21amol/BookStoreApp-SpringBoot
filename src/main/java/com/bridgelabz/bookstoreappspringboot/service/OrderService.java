package com.bridgelabz.bookstoreappspringboot.service;

import com.bridgelabz.bookstoreappspringboot.dto.OrderDTO;
import com.bridgelabz.bookstoreappspringboot.dto.ResponseDTO;
import com.bridgelabz.bookstoreappspringboot.exception.BookStoreException;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.model.CartData;
import com.bridgelabz.bookstoreappspringboot.model.OrderData;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import com.bridgelabz.bookstoreappspringboot.repository.OrderRepo;
import com.bridgelabz.bookstoreappspringboot.repository.UserRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  @Autowired
  OrderRepo orderRepo;
  @Autowired
  UserRegistrationService userRegistrationService;
  @Autowired
  BookService bookService;
  @Autowired
  UserRegistrationRepo userRegistrationRepo;

  public OrderData placeOrder(OrderDTO orderDTO) {
    BookData bookData = bookService.getBookById(orderDTO.getBookId()); //optional
    Optional<UserRegistrationData> userRegistrationData = userRegistrationRepo.findById(orderDTO.getUserId()); //optional
    int totalQuantity = orderDTO.getTotalQuantity();
    int totalPrice = bookData.getPrice() * totalQuantity;
    System.out.println("total Price " + totalPrice);
    OrderData orderData = new OrderData(bookData, userRegistrationData.get(), orderDTO.address, totalQuantity, totalPrice);
    return orderRepo.save(orderData);
  }

  public OrderData getOrderID(int id) {
    return orderRepo.findById(id).orElseThrow(() -> new BookStoreException("Order Id Not Found!!!"));
  }

  public List<OrderData> getAllOrder() {
    return orderRepo.findAll();
  }

  public void deleteOrderData(int orderId) {
    OrderData orderData = this.getOrderID(orderId);
    orderRepo.delete(orderData);
  }

  public OrderData cancelOrder(int orderId) {
    OrderData order = this.getOrderID(orderId);
    order.setCancel(true);
    return orderRepo.save(order);
  }
}
