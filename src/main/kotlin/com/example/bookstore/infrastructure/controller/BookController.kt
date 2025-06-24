package com.example.bookstore.infrastructure.controller

import com.example.bookstore.application.service.BookService
import main.domain.model.Book
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(val bookService: BookService) {

    @GetMapping("/list")
    fun getAllBooks(): List<Book> {
        return bookService.getAllBooks()
    }

}
