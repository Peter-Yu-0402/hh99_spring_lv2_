package com.example.sparta.library.controller;

import com.example.sparta.library.dto.BookRequestDto;
import com.example.sparta.library.dto.MemberRequestDto;
import com.example.sparta.library.dto.MemberResponseDto;
import com.example.sparta.library.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Member", description = "도서관 회원 등록")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {this.memberService = memberService;}

    @PostMapping("/members")
    @Operation(summary = "회원 등록 기능", description = "회원 등록 API")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "회원 등록 성공", content = @Content(schema = @Schema(implementation = MemberRequestDto.class))),
    })
    public MemberResponseDto registerMember(@RequestBody MemberRequestDto requestDto) {

        // 컨트롤러 메서드와 서비스 메서드 명칭 일치시키기
        return memberService.registerMember(requestDto);
    }


}
