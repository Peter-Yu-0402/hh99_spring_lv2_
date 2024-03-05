package com.example.sparta.library.entity;

import com.example.sparta.library.dto.BookRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Book extends BookBaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long bookId;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="author",nullable = false)
    private String author;

    @Column(name="language",nullable = false)
    private String language;

    @Column(name="publisher",nullable = false)
    private String publisher;

    public Book(BookRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.language = requestDto.getPublisher();
        this.publisher  = requestDto.getLanguage();
    }
}
