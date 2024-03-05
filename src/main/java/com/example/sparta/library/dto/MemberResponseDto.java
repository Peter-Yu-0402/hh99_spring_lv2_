package com.example.sparta.library.dto;

import com.example.sparta.library.entity.Gender;
import com.example.sparta.library.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    @Schema(description = "회원 번호")
    private Long memberId;
    @Schema(description = "성별")
    private Gender gender;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "전화번호")
    private String phone;
    @Schema(description = "주소")
    private String address;

    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.gender = member.getGender();
        this.phone = member.getPhone();
        this.address = member.getAddress();
    }
}
