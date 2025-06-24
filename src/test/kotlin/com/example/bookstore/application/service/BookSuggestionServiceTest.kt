package com.example.bookstore.application.service

import com.example.bookstore.domain.model.BookSuggestion
import com.example.bookstore.infrastructure.persistence.JsonBookSuggestionRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class BookSuggestionServiceTest {

 lateinit var bookSuggestionService: BookSuggestionService

 val bookSuggestionRepository = mock(JsonBookSuggestionRepository::class.java)

 @Test
 fun shouldSaveBookSuggestion() {
  bookSuggestionService = BookSuggestionService(bookSuggestionRepository)
  val title = "Fundamentals of Software Architecture"
  val author = "Mark"
  bookSuggestionService.saveBookSuggestion(title, author)

  verify(bookSuggestionRepository).save(BookSuggestion(title, author))


 }
}
