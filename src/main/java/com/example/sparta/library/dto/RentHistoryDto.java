package com.example.sparta.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RentHistoryDto { // 회원 대출 내역 반환 해줄 dto
    @Schema(description = "이름")
    private String name; // 회원 이름
    @Schema(description = "전화번호")
    private String phone; // 회원 전화번호
    @Schema(description = "도서 제목")
    private String title; //도서 제목
    @Schema(description = "저자")
    private String author;   // 저자
    @Schema(description = "대여일")
    private LocalDateTime rentDate; // 대출일

}
