package com.example.bookstore.infrastructure

import com.example.bookstore.domain.model.BookSuggestion
import com.example.bookstore.infrastructure.persistence.JsonBookSuggestionRepository
import org.junit.jupiter.api.Test

class JsonBookSuggestionRepositoryTest {
    val jsonBookSuggestionRepository = JsonBookSuggestionRepository(
        "resources/suggestions-test.json"
    )

    @Test
    fun shouldSaveBookSuggestionToSuggestionsJsonFile() {
        val bookSuggestion = BookSuggestion("Dune", "Frank Herbert")
        jsonBookSuggestionRepository.save(bookSuggestion)

        val savedSuggestions = jsonBookSuggestionRepository.findAll()
        assert(savedSuggestions.any {
            it.title == "Dune" && it.author == "Frank Herbert"
        })
    }
}
