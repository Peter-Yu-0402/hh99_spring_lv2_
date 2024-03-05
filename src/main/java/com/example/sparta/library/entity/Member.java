package com.example.sparta.library.entity;

import com.example.sparta.library.dto.MemberRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "member") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;


    @Column(name = "prno", nullable = false, length = 500, unique=true)
    private String prno; // 주민등록번호

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "phone", nullable = false, length = 15, unique=true)
    private String phone;

    @Column(name = "address", nullable = false, length = 100)
    private String address;


    public Member(MemberRequestDto requestDto) {
        this.gender = requestDto.getGender();
        this.prno = requestDto.getPrno();
        this.name = requestDto.getName();
        this.phone = requestDto.getPhone();
        this.address = requestDto.getAddress();
    }
}