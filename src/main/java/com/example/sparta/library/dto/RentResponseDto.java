package com.example.sparta.library.dto;

import com.example.sparta.library.entity.Rent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class RentResponseDto {
    @Schema(description = "대여 번호")
    private Long rentId;
    @Schema(description = "도서 번호")
    private Long bookId; // FK BOOK 참조
    @Schema(description = "회원 번호")
    private Long memberId; // FK MEMBERiD 참조
    @Schema(description = "반납상태")
    private Boolean returnStatus;
    @Schema(description = "대여시작일")
    private LocalDateTime rentDate; // 빌린날
    @Schema(description = "반납일")
    private LocalDateTime returnDate; // 반납하면 반납한 날



    public RentResponseDto(Rent rent) {
        this.rentId = rent.getRentId();
        this.bookId = rent.getBookId();
        this.memberId = rent.getMemberId();
        this.returnStatus = rent.getReturnStatus();
        this.rentDate = rent.getRentDate();
        this.returnDate = rent.getReturnDate();
    }


}
