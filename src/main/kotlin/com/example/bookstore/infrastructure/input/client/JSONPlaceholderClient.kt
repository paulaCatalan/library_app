package com.example.bookstore.infrastructure.input.client

import com.example.bookstore.domain.model.Comment
import com.example.bookstore.infrastructure.converter.CommentConverter
import com.example.bookstore.infrastructure.dto.CommentDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Repository
class JSONPlaceholderClient(
    private val restTemplate: RestTemplate,
    private val commentConverter: CommentConverter,
    @Value("\${jsonplaceholder.api.url}") private val apiUrl: String
    ) : CommentClient {

        override fun retrieveAllComments(): List<Comment> {
            val commentsEndpoint = "$apiUrl/comments"
            println("Fetching comments from $commentsEndpoint")

            val comments = restTemplate.getForObject<Array<CommentDTO>>(commentsEndpoint)

            println("Found ${comments.size} comments")

//            return comments.map { Comment(it.postId, it.id, it.name, it.email, it.body) }.toList()
            return comments.map { commentConverter.convert(it) }.toList()
        }


}
