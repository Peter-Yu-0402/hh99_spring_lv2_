package com.example.sparta.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BookRequestDto {

    @Schema(description = "책 제목",example="마흔에 읽는 쇼펜하우어")
    private String title;
    @Schema(description = "책 저자", example = "강용수")
    private String author;
    @Schema(description = "언어", example="한국어")
    private String language;
    @Schema(description = "출판사", example="유노북스")
    private String publisher;
}
