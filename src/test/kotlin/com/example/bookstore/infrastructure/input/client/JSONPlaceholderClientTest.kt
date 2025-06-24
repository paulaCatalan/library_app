package com.example.bookstore.infrastructure.input.client

import com.example.bookstore.domain.model.Comment
import com.example.bookstore.infrastructure.converter.CommentConverter
import com.example.bookstore.infrastructure.dto.CommentDTO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import org.springframework.web.client.RestTemplate

class JSONPlaceholderClientTest {
   private lateinit var JSONPlaceholderClient: JSONPlaceholderClient

   lateinit var mockRestTemplate: RestTemplate

   lateinit var mockCommentConverter: CommentConverter

   val apiUrl = "testApiUrl"

   @BeforeEach
   fun setUp() {
      mockRestTemplate = mock(RestTemplate::class.java)
      mockCommentConverter = mock(CommentConverter::class.java)

      JSONPlaceholderClient = JSONPlaceholderClient(mockRestTemplate, mockCommentConverter, apiUrl)
   }

   @Test
   fun shouldRetrieveCommentsFromJsonPlaceholder() {
      val commentsEndpoint = "$apiUrl/comments"

      val commentDTO = CommentDTO("postId", "id", "name", "email", "body")

      val expectedComment = Comment("postId", "id", "name", "email", "body")

      whenever(mockRestTemplate.getForObject(commentsEndpoint, Array<CommentDTO>::class.java))
          .thenReturn(arrayOf(commentDTO))

      whenever(mockCommentConverter.convert(commentDTO)).thenReturn(expectedComment)

      val retrievedComments = JSONPlaceholderClient.retrieveAllComments()

      assertEquals(listOf(expectedComment), retrievedComments)

   }
}
