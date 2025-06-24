package com.example.bookstore.infrastructure.controller

import com.example.bookstore.application.service.BookSuggestionService
import com.example.bookstore.infrastructure.dto.BookSuggestionCreationRequest
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class BookSuggestionControllerTest{
 lateinit var bookSuggestionController: BookSuggestionController

 val bookSuggestionService = mock(BookSuggestionService::class.java)


 @Test
 fun shouldSaveBookSuggestion() {
  bookSuggestionController = BookSuggestionController(bookSuggestionService)

  bookSuggestionController.addBookSuggestion(BookSuggestionCreationRequest("title", "author"))

  verify(bookSuggestionService).saveBookSuggestion("title", "author")

 }
}
