package com.example.bookstore.domain.model

data class Comment(
    val bookId: String,
    val commentId: String,
    val name: String,
    val email: String,
    val content: String,
)
