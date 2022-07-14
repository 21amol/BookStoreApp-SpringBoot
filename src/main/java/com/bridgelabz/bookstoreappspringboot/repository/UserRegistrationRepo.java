package com.bridgelabz.bookstoreappspringboot.repository;

import com.bridgelabz.bookstoreappspringboot.model.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRegistrationRepo extends JpaRepository<UserRegistrationData, Integer> {

  @Query(value = "select *from user_registration where email_id = :emailId", nativeQuery = true)
  Optional<UserRegistrationData> findByEmailId(String emailId);

  Optional <UserRegistrationData> findByEmailIdAndPassword(String emailId, String password);
}
