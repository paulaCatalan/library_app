package com.example.bookstore.infrastructure.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BookJson(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("author")
    val author: String,
    @JsonProperty("content")
    val content: String
)
