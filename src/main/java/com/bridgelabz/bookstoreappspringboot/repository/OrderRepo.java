package com.bridgelabz.bookstoreappspringboot.repository;

import com.bridgelabz.bookstoreappspringboot.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderData,Integer> {
}
