package com.example.bookstore.application.service

import main.domain.model.Book
import main.domain.repository.BookRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(val bookRepository: BookRepository) {
    fun createBookWithId(book: Book) {

        if (bookRepository.findById(book.id) == null) {
            bookRepository.save(book)
        }
        else {
            println("Book already exists")
        }

    }

    fun createNewBook(title: String, author: String, content: String) {
        val uuid = UUID.randomUUID()
        val book = Book(uuid.toString(), title, author, content)
        bookRepository.save(book)
    }

    fun getAllBooks(): List<Book> {
        return bookRepository.findAll()
    }

}
