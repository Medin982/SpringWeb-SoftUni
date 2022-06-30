package com.example.books.service;

import com.example.books.models.dto.AddBookDTO;
import com.example.books.models.dto.BookDTO;
import com.example.books.models.entity.Author;
import com.example.books.models.entity.Book;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<BookDTO> getBookById(Long id) {
       return this.bookRepository.
                findById(id).
                map(book -> this.modelMapper.map(book, BookDTO.class));
    }

    public List<BookDTO> findAllBooks() {
        return this.bookRepository.
                findAll().
                stream().
                map(book -> this.modelMapper.map(book, BookDTO.class)).
                collect(Collectors.toList());
    }

    public BookDTO addBook(AddBookDTO addBookDTO) {
        Book book = this.modelMapper.map(addBookDTO, Book.class);
        Optional<Author> authorByName = this.authorRepository.findByName(addBookDTO.getAuthor());
        if (authorByName.isPresent()) {
            authorByName.get().getBooks().add(book);
            book.setAuthor(authorByName.get());
            authorRepository.save(authorByName.get());
        } else {
            Author author = new Author();
            author.setName(addBookDTO.getAuthor());
            author.getBooks().add(book);
            book.setAuthor(author);
            this.authorRepository.save(author);
        }
        this.bookRepository.save(book);
        return this.modelMapper.map(book, BookDTO.class);
    }
}
