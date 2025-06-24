package com.example.bookstore.infrastructure

    import com.example.bookstore.application.service.BookService
    import com.example.bookstore.infrastructure.input.JsonFileBookLoader
    import org.junit.jupiter.api.Test
    import org.mockito.Mockito.times
    import org.mockito.Mockito.verify
    import org.mockito.kotlin.any
    import org.springframework.beans.factory.annotation.Autowired
    import org.springframework.boot.test.context.SpringBootTest
    import org.springframework.boot.test.mock.mockito.MockBean
    import org.springframework.test.context.TestPropertySource

    @SpringBootTest
    @TestPropertySource(properties = ["book.file.path=books.json"])
    class JsonFileBookLoaderTest {

        @MockBean
        private lateinit var bookService: BookService

        @Autowired
        private lateinit var jsonFileBookLoader: JsonFileBookLoader

        @Test
        fun `run should load books from test json and call createBook on service`() {

            verify(bookService, times(3)).createNewBook(
                any(),
                any(),
                any()
            )

            verify(bookService).createNewBook(
                "India Title",
                "Akshay kumar",
                "India book"
            )

            verify(bookService).createNewBook("Blackwater",
                "Michael McDowell",
                "Originally published as a series of six volumes in 1983, Blackwater is the crowning achievement of Michael McDowell, author of the Southern Gothic classics Cold Moon Over Babylon and The Elementals and screenwriter of Beetlejuice and The Nightmare Before Christmas. This first-ever one-volume edition will allow a new generation of listeners to discover this modern horror classic."
            )

        }
    }
