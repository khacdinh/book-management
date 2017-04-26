package com.bookmanagement.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmanagement.entity.Book;
import com.bookmanagement.log.LoggingUtil;
import com.bookmanagement.model.BookDetail;
import com.bookmanagement.model.UserForm;
import com.bookmanagement.repository.BookRepository;
import com.bookmanagement.service.BookService;
import com.bookmanagement.util.ResultCode;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserForm userForm;

    @Autowired
    private LoggingUtil LoggingUtil;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Service add new book
     */
    @Override
    public ResultCode addBook(Book book) {
        book.setDateCreate(new Date());
        book.setDateUpdate(new Date());
        book.setCreateBy(userForm.getUserName());
        this.bookRepository.save(book);
        LoggingUtil.getBookHandleLogger().info("addBook: {}", book);
        logger.debug("Add new Book Service" + userForm.getUserName());
        return ResultCode.SUCCESS;
    }

    /**
     * Service update book
     */
    @Override
    public ResultCode updateBook(Long id, Book book) {
        logger.debug("update Book with bookId = {}", id);
        LoggingUtil.getBookHandleLogger().info("updateBook Id: {}, author: {}, description : {} ", id, book.getAuthor(), book.getDescription());
        Book bookUpdate = bookRepository.findOne(id);
        if (bookUpdate != null) {
            bookUpdate.setDescription(book.getDescription());
            bookUpdate.setTitle(book.getTitle());
            bookUpdate.setAuthor(book.getAuthor());
            bookUpdate.setId(id);
            bookUpdate.setDateUpdate(new Date());
            bookUpdate.setUpdateBy(userForm.getUserName());
            this.bookRepository.save(bookUpdate);
        } else {
            this.bookRepository.save(book);
        }
        return ResultCode.SUCCESS;
    }

    /**
     * Service delete Book
     */
    @Override
    public ResultCode deleteBook(Long id) {
        LoggingUtil.getBookHandleLogger().info("deleteBook Id: {}", id);
        logger.debug("Delete Book with bookId = {}", id);
        this.bookRepository.delete(id);
        return ResultCode.SUCCESS;
    }

    /**
     * Service find book by Id
     */
    @Override
    public com.bookmanagement.model.Book findBookById(Long id) {
        LoggingUtil.getBookHandleLogger().info("deleteBook book Id: {}", id);
        Book bookEntity = bookRepository.findOne(id);
        com.bookmanagement.model.Book bookModel = new com.bookmanagement.model.Book();
        if (bookEntity != null) {
            BeanUtils.copyProperties(bookEntity, bookModel);
        }
        return bookModel;
    }

    @Override
    public BookDetail viewBookDetail(Long id) {
        Book book = bookRepository.findOne(id);
        LoggingUtil.getBookHandleLogger().info("viewBookDetail {}",book.toString());
        BookDetail bookDetail = new BookDetail();
        BeanUtils.copyProperties(book, bookDetail);
        bookDetail.setDateCreate(book.getDateCreate() != null ? DateFormatUtils.format(book.getDateCreate(), "yyyy-MM-dd HH:mm:ss") : "");
        bookDetail.setDateUpdate(book.getDateUpdate() != null ? DateFormatUtils.format(book.getDateUpdate(), "yyyy-MM-dd HH:mm:ss") : "");
        return bookDetail;
    }

}
