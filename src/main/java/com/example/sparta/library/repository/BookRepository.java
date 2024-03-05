package com.example.sparta.library.repository;

import com.example.sparta.library.dto.BookResponseDto;
import com.example.sparta.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByCreatedAtDescTitleDesc();
}
