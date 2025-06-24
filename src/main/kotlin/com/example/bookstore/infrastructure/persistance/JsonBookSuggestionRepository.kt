package com.example.bookstore.infrastructure.persistence

import com.example.bookstore.domain.model.BookSuggestion
import com.example.bookstore.domain.repository.BookSuggestionsRepository
import com.example.bookstore.infrastructure.dto.BookSuggestionJson
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import java.nio.file.Paths
import java.util.concurrent.locks.ReentrantReadWriteLock


@Repository
class JsonBookSuggestionRepository (
    // Inject the file path from application.properties for flexibility
    @Value("\${suggestions.file.path:suggestions.json}") private val filePath: String
) : BookSuggestionsRepository {

    // The main Jackson object for reading and writing
    private val objectMapper = jacksonObjectMapper()
    private val suggestionsFile = Paths.get(filePath).toFile()

    // A ReadWriteLock ensures that multiple threads can read the file simultaneously,
    // but only one thread can write at a time, preventing data corruption.
    private val lock = ReentrantReadWriteLock()

    init {
        // Ensure the file exists when the application starts
        if (!suggestionsFile.exists()) {
            // Only try to create parent directories if there are any
            suggestionsFile.parentFile?.mkdirs()
            suggestionsFile.createNewFile()
            // Initialize with an empty array structure
            writeToFile(emptyList())
        }
    }

    fun save(bookSuggestion: BookSuggestion) {
        lock.writeLock().lock() // Acquire the write lock
        try {
            val suggestions = readFromFile().toMutableList()
            val bookSuggestionJson = BookSuggestionJson.fromDomain(bookSuggestion)
            suggestions.add(bookSuggestionJson)
            writeToFile(suggestions)
        } finally {
            lock.writeLock().unlock() // Always release the lock in a finally block
        }
    }

    fun findAll(): List<BookSuggestion> {
        lock.readLock().lock() // Acquire the read lock
        try {
            return readFromFile().toMutableList()
                .map { suggestion -> suggestion.toDomain() }

        } finally {
            lock.readLock().unlock() // Always release the lock
        }
    }

    private fun readFromFile(): List<BookSuggestionJson> {
        if (!suggestionsFile.exists() || suggestionsFile.length() < 10) { // Check for minimal content like {"suggestions":[]}
            return emptyList()
        }
        val suggestionList: List<BookSuggestionJson> = objectMapper.readValue(suggestionsFile)
        return suggestionList
    }

    private fun writeToFile(suggestions: List<BookSuggestionJson>) {
        // Use a pretty printer to make the JSON file human-readable
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(suggestionsFile, suggestions)
    }
}
