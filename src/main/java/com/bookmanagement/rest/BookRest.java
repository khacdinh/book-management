package com.bookmanagement.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.entity.Book;
import com.bookmanagement.model.BookDetail;
import com.bookmanagement.service.BookService;
import com.bookmanagement.util.ResultCode;

@RestController
@RequestMapping("/api/v1/bookRest")
public class BookRest {

    private static final Logger logger = LoggerFactory.getLogger(BookRest.class);

    @Autowired
    private BookService bookService;

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

}
