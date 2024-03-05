package com.example.sparta.library.service;

import com.example.sparta.library.dto.MemberRequestDto;
import com.example.sparta.library.dto.MemberResponseDto;
import com.example.sparta.library.entity.Member;
import com.example.sparta.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {this.memberRepository = memberRepository;}

    public MemberResponseDto registerMember(MemberRequestDto requestDto) {

        // RequestDto -> Entity
        Member member = new Member(requestDto);

        // save메서드 신규 추가. Entity class를 저장한다.
        Member saveMember = memberRepository.save(member);

        // Entity -> ResponseDto
        MemberResponseDto memberResponseDto = new MemberResponseDto(saveMember);

        return memberResponseDto;
    }
}
