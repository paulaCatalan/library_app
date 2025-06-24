package com.example.bookstore.integration

import com.example.bookstore.infrastructure.dto.BookSuggestionCreationRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import java.io.File
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


// An integration test starts the full Spring Boot application on a random port

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookSuggestionControllerIntegrationTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    companion object {
        @TempDir
        @JvmStatic
        lateinit var tempDir: File

        @JvmStatic
        @DynamicPropertySource
        fun overrideProperties(registry: DynamicPropertyRegistry) {
            val tempFile = File(tempDir, "test-suggestions.json")
            registry.add("suggestions.file.path") { tempFile.absolutePath }
        }
    }

    @Test
    fun shouldAcceptBookSuggestionAndPersistItCorrectly() {
        //GIVEN (a http call with a body and headers)
        // Create the request body (DTO) that our frontend would send.
        val requestBody = BookSuggestionCreationRequest(
            title = "Fundamentals of Software Architecture",
            author = "Mark Richards & Neal Ford",
            )

        // Set the HTTP headers to indicate we are sending JSON.
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        //Create the full HTTP request entity with the body and headers.
        val requestEntity = HttpEntity(requestBody, headers)

        //WHEN (a post is made to our suggestions' endpoint)

        val response = restTemplate.postForEntity("/api/suggestions/suggest", requestEntity, Void::class.java)

        //THEN (The suggestion is correctly created and stored)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        val suggestionsFile = File(tempDir, "test-suggestions.json")
        assertTrue(suggestionsFile.exists(), "Suggestions file should have been created")

        // I'm in RED state (RED-GREEN-REFFACTOR). This following assertions are STILL not implemented
        val fileContent = suggestionsFile.readText()
        assertTrue(fileContent.contains("Fundamentals of Software Architecture"), "File should contain the title of the new suggestion")
        assertTrue(fileContent.contains("Mark Richards & Neal Ford"), "File should contain the author of the new suggestion")
        }
    }
