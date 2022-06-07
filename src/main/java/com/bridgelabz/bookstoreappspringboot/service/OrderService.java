package com.bridgelabz.bookstoreappspringboot.service;

import com.bridgelabz.bookstoreappspringboot.dto.OrderDTO;
import com.bridgelabz.bookstoreappspringboot.exception.BookStoreException;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.model.OrderData;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import com.bridgelabz.bookstoreappspringboot.repository.OrderRepo;
import com.bridgelabz.bookstoreappspringboot.repository.UserRegistrationRepo;
import com.bridgelabz.bookstoreappspringboot.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  @Autowired
  TokenUtil tokenUtil;

  //-------------------Placing a Order--------------------------
  public OrderData placeOrder(OrderDTO orderDTO, String token) {
    BookData bookData = bookService.getBookById(orderDTO.getBookId());
    Optional<UserRegistrationData> userRegistrationData = Optional.ofNullable(userRegistrationService.getUserDataById(token));
    int totalQuantity = orderDTO.getTotalQuantity();
    int totalPrice = bookData.getPrice() * totalQuantity;
    System.out.println("total Price " + totalPrice);
    OrderData orderData = new OrderData(bookData, userRegistrationData.get(), orderDTO.address,
            totalQuantity, totalPrice);
    return orderRepo.save(orderData);
  }


  //--------------Getting specific order details---------------------
  public Optional<OrderData> getOrderID(int orderId, String token) {
    UserRegistrationData userRegistrationData = userRegistrationService.getUserDataById(token);
    Optional<OrderData> orderData = orderRepo.findById(orderId);
    if (userRegistrationData != null) {
      return orderData;
    } else {
      throw (new BookStoreException("Order Id not Found!!!"));
    }
  }

  //-----------------Getting all order details-------------------
  public List<OrderData> getAllOrder() {
    return orderRepo.findAll();
  }


  //----------------Cancelling a Order--------------------
  public Optional<OrderData> cancelOrder(int orderId, String token) {
    Optional<OrderData> order = getOrderID(orderId, token);
    order.get().setCancel(true);
    return order;
  }
}

