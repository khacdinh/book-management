package com.bookmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bookmanagement.entity.Book;
import com.bookmanagement.model.BookDetail;
import com.bookmanagement.util.ResultCode;

public interface BookService {

    public List<Book> getAllBooks();

    public ResultCode addBook(Book book);

    public ResultCode updateBook(Long id, Book book);

    public ResultCode deleteBook(Long id);

    public com.bookmanagement.model.Book findBookById(Long id);

    public BookDetail viewBookDetail(Long id);
    
    public Page<Book> getBooks(int pageNumber);
    
    public Page<Book> findBook(String keyWord,int pageNumber);


}
