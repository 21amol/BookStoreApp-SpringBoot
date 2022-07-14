package com.bridgelabz.bookstoreappspringboot.repository;

import com.bridgelabz.bookstoreappspringboot.model.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepo extends JpaRepository<CartData, Integer> {

  @Query(value = "select *from cart_data where user_id =:userId", nativeQuery = true)
  List<CartData> findByUserID(int userId);

  @Query(value = "select *from cart_data where book_id =:bookId", nativeQuery = true)
  List<CartData> findByBookID(int bookId);

}
