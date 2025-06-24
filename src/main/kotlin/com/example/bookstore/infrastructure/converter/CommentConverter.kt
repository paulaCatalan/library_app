package com.example.bookstore.infrastructure.converter

import com.example.bookstore.domain.model.Comment
import com.example.bookstore.infrastructure.dto.CommentDTO
import org.springframework.stereotype.Component

@Component
class CommentConverter {

    fun convert(commentDTO: CommentDTO): Comment {
        return Comment(commentDTO.postId, commentDTO.id, commentDTO.name, commentDTO.email, commentDTO.body)
    }

}
