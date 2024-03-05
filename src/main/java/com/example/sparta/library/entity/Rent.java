package com.example.sparta.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Rent extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentId;


//    private Book book;
//
//    private Member member;

    private Long bookId; // FK BOOK 참조
    private Long memberId; // FK MEMBERiD 참조

    private Boolean returnStatus; // 대출 가능여부 return status flase 이면 대출중

    public Rent(Book book, Member member){
        this.bookId = book.getBookId();
        this.memberId = member.getMemberId();
        this.returnStatus = false; // rent 진행
    }

    public void returnBook() { // 반납하는 거
        this.returnStatus=true; // 반납해서 대출 가능 상태로 변경
    }
}
