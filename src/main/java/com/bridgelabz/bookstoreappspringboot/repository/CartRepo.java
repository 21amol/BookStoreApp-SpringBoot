package com.bridgelabz.bookstoreappspringboot.repository;

import com.bridgelabz.bookstoreappspringboot.model.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartData, Integer> {
}
