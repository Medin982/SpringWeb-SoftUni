package com.example.books.service;

import com.example.books.models.dto.BookDTO;
import com.example.books.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
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
}
