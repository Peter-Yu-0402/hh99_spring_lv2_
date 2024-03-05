package com.example.sparta.library.dto;


import com.example.sparta.library.entity.Gender;
import com.example.sparta.library.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MemberRequestDto {
    @Schema(description = "성별", example = "MALE")
    private Gender gender;
    @Schema(description = "주민번호", example = "240228-4111112")
    private String prno;
    @Schema(description = "이름", example = "김똥개")
    private String name;
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phone;
    @Schema(description = "주소", example = "경기도 수원시 팔달구")
    private String address;




}
