package com.example.bookstore.infrastructure.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CommentDTO(
    val postId: String,
    val id: String,
    val name: String,
    val email: String,
    val body: String
)
