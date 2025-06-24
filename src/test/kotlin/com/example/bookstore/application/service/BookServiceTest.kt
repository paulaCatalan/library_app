package com.example.bookstore.application.service

import main.domain.model.Book
import com.example.bookstore.infrastructure.persistance.InMemoryBookRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.concurrent.ConcurrentHashMap

class BookServiceTest {
    val bookId = "alreadySavedBookId"
    val bookInHashMap = Book(bookId, "Saved Title", "Saved Author", "Saved Content")
    val bookInMemoryHashMap = ConcurrentHashMap<String, Book>().apply {
        put(bookId, bookInHashMap)
    }
     val mockBookRepository = spy(InMemoryBookRepository::class.java).apply {
        javaClass.getDeclaredField("books").apply { isAccessible = true }.set(this, bookInMemoryHashMap)
      }

    @Test
    fun shouldCreateBookWithId() {
        val bookService = BookService(mockBookRepository)
        val book = Book("id", "title", "author", "a short content")

        bookService.createBookWithId(book)

        assertEquals(2, mockBookRepository.findAll().size)
        assertTrue(mockBookRepository.findAll().contains(book))

  }
    @Test
    fun shouldNotCreateBookWithIdWithAlreadyContainedId() {
        val bookService = BookService(mockBookRepository)
        val book = Book("alreadySavedBookId", "title", "author", "a short content")

        bookService.createBookWithId(book)

        assertEquals(mockBookRepository.findAll().size, 1)
        assertFalse(mockBookRepository.findAll().contains(book))

    }

    @Test
    fun shouldGenerateIdForBookAndCreate() {
        val bookService = BookService(mockBookRepository)
        val inputTitle = "New Title"
        val inputAuthor = "New Author"
        val inputContent = "New Content"

        bookService.createNewBook(inputTitle, inputAuthor, inputContent)

        assertEquals(mockBookRepository.findAll().size, 2)
        assertNotNull(mockBookRepository.findByTitle("New Title")?.id)

    }

    @Test
    fun shouldReturnAllBooksInRepository(){
        val bookService = BookService(mockBookRepository)

        val booksList = bookService.getAllBooks()

        assertEquals(listOf(bookInHashMap), booksList)
    }
 }
