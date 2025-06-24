package main.domain.repository

import main.domain.model.Book

interface BookRepository {
    fun save(book: Book)
    fun findAll(): List<Book>
    fun findById(id: String): Book?
    fun findByTitle(title: String): Book?
}
