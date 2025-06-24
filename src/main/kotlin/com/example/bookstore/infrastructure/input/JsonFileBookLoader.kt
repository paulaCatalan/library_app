package com.example.bookstore.infrastructure.input

import com.example.bookstore.application.service.BookService
import com.example.bookstore.infrastructure.dto.BookJsonList
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource

@Component
class JsonFileBookLoader(
    private val bookService: BookService,
    @Value("classpath:\${book.file.path}") private val booksResource: Resource
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (!booksResource.exists()) {
            println("INFO: books.json not found, skipping file load.")
            return
        }

        try {
            val objectMapper = jacksonObjectMapper()
            val bookJsonList: BookJsonList = objectMapper.readValue(booksResource.inputStream, BookJsonList::class.java)
            println("--- Loading books from ${booksResource.filename} ---")
            bookJsonList.books.forEach { book ->
                bookService.createNewBook(book.title, book.author, book.content)
                println("Loaded book: '${book.title}'")
            }
            println("--- Finished loading books. ---")

        } catch (e: Exception) {
            println("ERROR: Could not load books from file: ${e.message}")
            e.printStackTrace()
        }
    }
}
