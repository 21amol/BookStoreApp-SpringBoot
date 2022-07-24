package com.bridgelabz.bookstoreappspringboot.service;

import com.bridgelabz.bookstoreappspringboot.dto.OrderDTO;
import com.bridgelabz.bookstoreappspringboot.exception.BookStoreException;
import com.bridgelabz.bookstoreappspringboot.model.BookData;
import com.bridgelabz.bookstoreappspringboot.model.OrderData;
import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import com.bridgelabz.bookstoreappspringboot.repository.BookRepo;
import com.bridgelabz.bookstoreappspringboot.repository.OrderRepo;
import com.bridgelabz.bookstoreappspringboot.repository.UserRegistrationRepo;
import com.bridgelabz.bookstoreappspringboot.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
  @Autowired
  BookRepo bookRepo;


  //-------------------Placing a Order--------------------------
  public OrderData placeOrder(OrderDTO orderDTO) {
    BookData bookData = bookService.getBookById(orderDTO.getBookId());
    Optional<UserRegistrationData> userRegistrationData = userRegistrationRepo.findById(orderDTO.getUserId());
    //
    //Optional<UserRegistrationData> userRegistrationData = Optional.ofNullable((userRegistrationService.getUserDataById(String.valueOf(orderDTO.getUserId()))));
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


//  public OrderData insertOrder(OrderDTO orderdto) {
//    Optional<BookData>  book = bookRepo.findById(orderdto.getBookId());
//    Optional<UserRegistrationData> user = userRegistrationRepo.findById(orderdto.getUserId());
//    if(book.isPresent() && user.isPresent()) {
//      if(orderdto.getTotalQuantity() < book.get().getQuantity()) {
//        OrderData newOrder = new OrderData(book.get().getPrice(),orderdto.getTotalQuantity(),
//                orderdto.getAddress(),book.get(),user.get(),orderdto.isCancel());
//        orderRepo.save(newOrder);
//        book.get().setQuantity(book.get().getQuantity() - orderdto.getTotalQuantity());
//        bookRepo.save(book.get());
//        log.info("Order record inserted successfully");
//        return newOrder;
//      }else {
//        throw new BookStoreException("Requested quantity is not available");
//      }
//    }else {
//      throw new BookStoreException("Book or User doesn't exists");
//    }
//  }

}

