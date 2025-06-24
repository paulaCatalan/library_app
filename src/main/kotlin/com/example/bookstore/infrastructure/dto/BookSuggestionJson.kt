package com.example.bookstore.infrastructure.dto

import com.example.bookstore.domain.model.BookSuggestion
import com.fasterxml.jackson.annotation.JsonProperty

data class BookSuggestionJson(
    @JsonProperty("title") val title: String,
    @JsonProperty("author") val author: String
) {
    fun toDomain(): BookSuggestion {
        return BookSuggestion(title = title, author = author)
    }

    companion object {
        fun fromDomain(bookSuggestion: BookSuggestion): BookSuggestionJson {
            return BookSuggestionJson(
                title = bookSuggestion.title,
                author = bookSuggestion.author
            )
        }
    }
}

