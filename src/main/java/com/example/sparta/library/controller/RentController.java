package com.example.sparta.library.controller;

import com.example.sparta.library.dto.*;
import com.example.sparta.library.repository.RentRepository;
import com.example.sparta.library.service.RentService;
import com.example.sparta.library.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Rent", description = "대여 서비스")
public class RentController {

    private final RentService rentService;

    // 도서 대출 insert 작업 있어야 한다 post
    // 대출할때 book id, member id 함께 가지고 간다
    // 반납하지 않은 책이 있으면 대출이 불가능
    // 선택한 도서가 대출 상태라면 대출이 불가능
    @PostMapping("/rent")
    @Operation(summary = "도서 대여 기능", description = "도서 대여 API")
    @ApiResponse(responseCode = "200", description = "도서 대여 성공")
    public ResponseDto rentBook(@Parameter(description = "도서 번호", example = "3") @RequestParam Long bookId, @Parameter(description = "회원 번호", example = "1") @RequestParam Long memberId) {
        try {
            RentResponseDto rentResponse = rentService.rentBook(bookId, memberId);
            return ResponseUtil.SUCCESS("도서 대출이 완료되었습니다.", rentResponse);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseUtil.FAILURE(e.getMessage(), null);
        } catch (Exception e) {
            return ResponseUtil.ERROR(e.getMessage(), null);
        }
    }

    // 도서 반납 기능
    // 수정작업이 있어야 한다 반납상태, 반납일 변경
    @PutMapping("/rent/{rentId}/return")
    @Operation(summary = "도서 반납 기능", description = "선택한 도서 반납 API")
    @ApiResponse(responseCode = "200", description = "도서 반납 성공")
    public ResponseDto rentBook(@Parameter(description = "rent Id", example = "2") @PathVariable Long rentId) {
        try {
            RentResponseDto rentResponse = rentService.returnBook(rentId);
            return ResponseUtil.SUCCESS("도서 반납이 완료되었습니다.", rentResponse);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseUtil.FAILURE(e.getMessage(), "lentId : " + rentId);
        } catch (Exception e) {
            return ResponseUtil.ERROR(e.getMessage(), "lentId : " + rentId);
        }
    }


    @GetMapping("/rent/{memberId}")
    @Operation(summary = "대출 내역 조회 기능", description = "선택한 회원의 대출 내역 조회 API")
    @ApiResponse(responseCode = "200", description = "회원 도서 내역 조회 성공")
    public ResponseDto rentHistory(@Parameter(description = "회원 Id", example = "1")
                                   @PathVariable Long memberId) {
        try {
            List<RentHistoryDto> rentResponse = rentService.rentHistory(memberId);
            return ResponseUtil.SUCCESS("도서 대출 목록 조회가 완료되었습니다.", rentResponse);
        } catch (EntityNotFoundException e) {
            return ResponseUtil.FAILURE(e.getMessage(), "lentId : " + memberId);
        } catch (Exception e) {
            return ResponseUtil.ERROR(e.getMessage(), "lentId : " + memberId);
        }
    }

}
