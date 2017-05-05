package com.bookmanagement.rest;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.entity.Book;
import com.bookmanagement.exception.MyResourceNotFoundException;
import com.bookmanagement.model.BookDetail;
import com.bookmanagement.service.BookService;
import com.bookmanagement.util.ResultCode;

@RestController
@RequestMapping("/api/v1/bookRest")
public class BookRest {

    private static final Logger logger = LoggerFactory.getLogger(BookRest.class);

    @Autowired
    private BookService bookService;

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 200; i++) {
            Book book = new Book();
            book.setAuthor("author" + i);
            book.setTitle("title" + i);
            book.setDescription("Description" + i);
            book.setDateCreate(new Date());
            bookService.addBook(book);
        }
    }

    /**
     * Get All book
     *
     * @return
     */
    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks() {
        logger.info("getAllBooks");
        return bookService.getAllBooks();

    }

    /**
     * add new Book
     *
     * @param book
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultCode addBook(@RequestBody Book book) {
        ResultCode resultCode = bookService.addBook(book);
        logger.info("addBook: Result Code {}", resultCode);
        return resultCode;
    }

    @RequestMapping(value = "/updateBook/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultCode updateBook(@PathVariable(name = "id", required = false) Long id, @RequestBody Book book) {
        ResultCode resultCode = bookService.updateBook(id, book);
        logger.info("UpdateBook");
        return resultCode;
    }

    @RequestMapping(value = "/delbook/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultCode deleteBook(@PathVariable(name = "id", required = false) Long id) {
        return bookService.deleteBook(id);
    }

    @RequestMapping(value = "/findBookById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public com.bookmanagement.model.Book findBookById(@PathVariable(name = "id", required = false) Long id) {
        return bookService.findBookById(id);
    }

    @RequestMapping(value = "/viewBookDetail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDetail viewBookDetail(@PathVariable(name = "id", required = false) Long id) {
        return bookService.viewBookDetail(id);
    }
    
    /** find all will be using like this
     * 
     * @param pageNumber number 
     * @return
     * Test: URL http://localhost:8080/api/v1/bookRest/bookPageable?pageNumber=1
     */
    @RequestMapping(value = "bookPageable", params = { "pageNumber" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> bookPageable(@RequestParam("pageNumber") int pageNumber) {
        return bookService.getBooks(pageNumber);
    }

    /**
     * 
     * @param keyWord
     * @param pageNumber
     * @return
     * Test: http://localhost:8080/api/v1/bookRest/findBook?keyWord=title&pageNumber=1
     */
    @RequestMapping(value = "findBook", params = { "keyWord", "pageNumber" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> findBookByTitleOrAuthor(@RequestParam("keyWord") String keyWord, @RequestParam("pageNumber") int pageNumber) {
        return bookService.findBook(keyWord, pageNumber);
    }

}
