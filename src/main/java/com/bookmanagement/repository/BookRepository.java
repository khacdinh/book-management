package com.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmanagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
