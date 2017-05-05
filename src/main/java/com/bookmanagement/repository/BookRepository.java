package com.bookmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookmanagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%',:keyword, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%',:keyword, '%'))")
    Page<Book> findByTitleOrAuthor(@Param("keyword") String keyword, 
                                Pageable pageRequest);
}
