package com.example.sparta.library.controller;

import com.example.sparta.library.dto.BookRequestDto;
import com.example.sparta.library.dto.BookResponseDto;
import com.example.sparta.library.dto.ResponseDto;
import com.example.sparta.library.service.BookService;
import com.example.sparta.library.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Book", description = "도서관 책 등록 및 조회")
public class BookController {
    private final BookService bookService;

    // 도서 등록 기능 post api/book
    @PostMapping("/book")
    @Operation(summary = "도서 등록 기능", description = "도서 등록 API")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "도서 등록 성공", content = @Content(schema = @Schema(implementation = BookRequestDto.class))),
            })
    public ResponseDto addBook(@RequestBody BookRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        try{
            BookResponseDto bookResponse = bookService.addBook(requestDto);
            return ResponseUtil.SUCCESS("도서가 정상적으로 등록되었습니다.", bookResponse);
        }catch (Exception e){
            return ResponseUtil.ERROR("도서 등록에 실패하였습니다.", null);
        }
    }

    // 선택한 도서 정보 조회 기능 get api/book/{id}
    @GetMapping("/book/{bookId}")
    @Operation(summary = "선택한 도서 정보 조회 기능", description = "선택한 도서의 정보를 조회하는 API")
    @ApiResponse(responseCode = "200", description = "도서 조회 성공",content = @Content(schema = @Schema(implementation = BookRequestDto.class)))
    public ResponseDto getBookInfo(@Parameter(description = "도서 Id", example = "2")
            @PathVariable Long bookId) {
        try {
            BookResponseDto bookResponse = bookService.getBookInfo(bookId);
            return ResponseUtil.SUCCESS("도서 조회를 성공했습니다.", bookResponse);
        } catch (EntityNotFoundException e) {
            return ResponseUtil.FAILURE(e.getMessage(), "book Id : " + bookId);
        } catch (Exception e) {
            return ResponseUtil.ERROR("도서 조회 중 오류가 발생했습니다.", null);
        }
    }


    // 도서 전체 목록 조회 기능 get api/book
    @GetMapping("/book")
    @Operation(summary = "도서 목록 조회 기능", description = "등록된 모든 도서를 조회하는 API")
    @ApiResponse(responseCode = "200", description = "도서 리스트 조회 성공", content = @Content(schema = @Schema(implementation = BookRequestDto.class)))
    public ResponseDto getBookList() {
        try {
            List<BookResponseDto> bookList = bookService.getBookList();
            return ResponseUtil.SUCCESS("도서 조회를 성공했습니다.", bookList);
        }catch (EntityNotFoundException e){
            return ResponseUtil.ERROR(e.getMessage(), null);
        }catch (Exception e){
            return ResponseUtil.ERROR("도서 조회 중 오류가 발생했습니다.", null);
        }
    }


}
