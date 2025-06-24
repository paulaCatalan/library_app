package repository.repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import main.domain.model.Book
import com.example.bookstore.infrastructure.persistance.InMemoryBookRepository

class InMemoryBookRepositoryTest {

    @Test
    fun shouldSaveBookAndReturnAllBooksInMemory() {
        val repository = InMemoryBookRepository()
        val bookForTest = Book("bookId", "Book title", "Author", "Content")
        repository.save(bookForTest)

        val books = repository.findAll()
        assertEquals(listOf(bookForTest), books)
      }

    @Test
    fun shouldFindBookByIdAndReturnIt() {
        val repository = InMemoryBookRepository()
        val bookForTest = Book("bookId", "Book title", "Author", "Content")
        repository.save(bookForTest)

        val book = repository.findById(bookForTest.id)
        assertEquals(bookForTest, book)
    }

    @Test
    fun shouldReturnNullIfBookIdIsNotInMemory() {
        val repository = InMemoryBookRepository()
        val bookForTest = Book("bookId", "Book title", "Author", "Content")
        repository.save(bookForTest)

        val book = repository.findById("notContainedId")
        assertNull(book)
    }

 }
