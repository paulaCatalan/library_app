package com.example.bookstore.application.service
import com.example.bookstore.domain.model.BookSuggestion
import com.example.bookstore.infrastructure.persistence.JsonBookSuggestionRepository
import org.springframework.stereotype.Service

@Service
class BookSuggestionService(val bookSuggestionRepository: JsonBookSuggestionRepository) {

    fun saveBookSuggestion(title: String, author: String) {
        val bookSuggestion = BookSuggestion(title, author)
        bookSuggestionRepository.save(bookSuggestion)
    }
}
