package com.example.sparta.library.dto;

import com.example.sparta.library.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "책 리스트 응답 DTO")
@Getter
public class BookResponseDto {
    @Schema(description = "책 고유 ID")
    private Long bookId;
    @Schema(description = "책 제목")
    private String title;
    @Schema(description = "책 저자")
    private String author;
    @Schema(description = "언어")
    private String language;
    @Schema(description = "출판사")
    private String publisher;
    @Schema(description = "등록일")
    private LocalDateTime createdAt;

    public BookResponseDto(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.language = book.getLanguage();
        this.publisher = book.getPublisher();
        this.createdAt = book.getCreatedAt();
    }
}
