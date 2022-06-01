package com.bridgelabz.bookstoreappspringboot.repository;

import com.bridgelabz.bookstoreappspringboot.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookData, Integer> {

  @Query(value = "select *from book where book_name = :bookName", nativeQuery = true)
  List <BookData> findByBookName(String bookName);

  @Query(value = "select *from book order by price asc", nativeQuery = true)
  List <BookData> sortByPriceAsc();

  @Query(value = "select *from book order by price desc ", nativeQuery = true)
  List <BookData> sortByPriceDesc();

  @Query(value = "select *from book where author_name = :authorName", nativeQuery = true)
  List <BookData> findByauthorName(String authorName);
}
