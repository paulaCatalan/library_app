package com.example.bookstore.infrastructure.controller

import com.example.bookstore.application.service.BookSuggestionService
import com.example.bookstore.infrastructure.dto.BookSuggestionCreationRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/suggestions")
class BookSuggestionController(val bookSuggestionService: BookSuggestionService) {

    @PostMapping("/suggest")
    fun addBookSuggestion(@RequestBody request: BookSuggestionCreationRequest): ResponseEntity<Void> {
        //TODO sanitize data before saving
        // Use the data from the request object to call your service
        bookSuggestionService.saveBookSuggestion(request.title, request.author)

        // Return an HTTP 201 Created status, which is the standard for successful creation
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
