package com.example.bookstore.infrastructure.persistance

import main.domain.model.Book
import main.domain.repository.BookRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryBookRepository : BookRepository {

    private val books = ConcurrentHashMap<String, Book>()

    override fun save(book: Book) {
        books[book.id] = book
    }

    override fun findAll(): List<Book> {
        return books.values.toList()
    }

    override fun findById(id: String): Book? {
        return books[id]
    }

    override fun findByTitle(title: String): Book? {
        return books.values.find { it.title == title }
    }

}
