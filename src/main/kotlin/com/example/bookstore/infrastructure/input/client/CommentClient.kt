package com.example.bookstore.infrastructure.input.client

import com.example.bookstore.domain.model.Comment


interface CommentClient {
    fun retrieveAllComments(): List<Comment>
}
