package com.example.books.web;

import com.example.books.models.dto.AddBookDTO;
import com.example.books.models.dto.BookDTO;
import com.example.books.service.BookService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.
                ok(this.bookService.findAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<BookDTO> bookById = this.bookService.getBookById(id);
        if (bookById.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.
                    ok(bookById.get());
        }
    }

//    TODO: Post request not work, need to fix
    @PostMapping("/add")
    public ResponseEntity<AddBookDTO> newBook(@RequestBody AddBookDTO addBookDTO) {
        BookDTO book = this.bookService.addBook(addBookDTO);
        return ResponseEntity.
                created();
    }
}
