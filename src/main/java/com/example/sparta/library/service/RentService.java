package com.example.sparta.library.service;

import com.example.sparta.library.dto.RentHistoryDto;
import com.example.sparta.library.dto.RentResponseDto;
import com.example.sparta.library.entity.Book;
import com.example.sparta.library.entity.Member;
import com.example.sparta.library.entity.Rent;
import com.example.sparta.library.repository.BookRepository;
import com.example.sparta.library.repository.MemberRepository;
import com.example.sparta.library.repository.RentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {

    private final RentRepository rentRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Transactional
    public RentResponseDto rentBook(Long bookId, Long memberId) {
        try {
            // 존재하는 책 확인
            Book book = bookRepository.findById(bookId).orElseThrow(()
                    -> new EntityNotFoundException("도서를 찾을 수 없습니다."));
            // 존재하는 회원 확인
            Member member = memberRepository.findById(memberId).orElseThrow(()
                    -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

            // 둘다 존재하면 대출 가능 여부 체크 - 대출 가능 체크
            if (isBookAvailableRent(bookId)) {
                throw new IllegalArgumentException("이미 대여중인 도서입니다!"); // 얘네는 대여 못함
            }
            if (isMemberBookRentable(memberId)) {
                throw new IllegalArgumentException("대여중인 도서가 있습니다.");
            }
            //나머지 대여 진행
            Rent rent = new Rent(book, member);
            rentRepository.save(rent);
            return new RentResponseDto(rent);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException("도서 대여 중 오류가 발생했습니다.", e);
        }
    }


    // 도서 대출 반납
    // 1. 반납하는 도서 대출 목록에서 조회 대출 기록 없으면 반납 못함
    // 2. 반납 상태 false -> true 로 변경
    @Transactional
    public RentResponseDto returnBook(Long rentId) {
        try {
            // 대출 기록이 존재하는지 확인
            Rent rent = rentRepository.findById(rentId).orElseThrow(()
                    -> new EntityNotFoundException("대출 기록이 없습니다."));

            // 이미 반납이 완료된 책인지 확인
            if (rent.getReturnStatus()) {
                throw new IllegalArgumentException("이미 반납이 완료 된 책입니다.");
            }
            // 존재하면 반납 상태 false → true
            // dirty checking returnStatus 위해서 update
            rent.returnBook();
            rentRepository.save(rent);
            return new RentResponseDto(rent);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            throw e; // e 예외를 호출한 곳으로 반환
        }catch (Exception e) {
            throw new RuntimeException("도서 반납 중 오류가 발생했습니다.", e);
        }
    }

    public List<RentHistoryDto> rentHistory(Long memberId) {
        try {
            // 1. 대출 기록이 존재하는지 아닌지 확인
            // 대출 기록이 존재하는지 확인
            List<Rent> rentList = rentRepository.findAllByMemberIdOrderByRentDateAsc(memberId).stream().toList();
            if (rentList.isEmpty()) {
                throw new EntityNotFoundException("도서 대출 내역이 없습니다.");
            }
            // 회원 정보 찾기
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

            List<RentHistoryDto> rentHistoryDtoList = new ArrayList<>();
            // responseDto 에 넣어야 하는데 도서정보, 회원정보도 넣어줘야함 순서대로 배열에 저장
            for (Rent rent : rentList) {
                RentHistoryDto rentHistoryDto = new RentHistoryDto(); // 객체 생성 하나씩 넣어줘;;
                Long bookId = rent.getBookId(); // 북 객체 내용 가져오기 위해 string id 저장

                Book book = bookRepository.findById(bookId).orElseThrow(
                        () -> new EntityNotFoundException("도서를 찾을 수 없습니다.")
                );
                
                // RentHistoryDto에 도서 정보 저장
                rentHistoryDto.setTitle(book.getTitle());
                rentHistoryDto.setAuthor(book.getAuthor());

                // RentHistoryDto에 회원 정보 저장
                rentHistoryDto.setName(member.getName());
                rentHistoryDto.setPhone(member.getPhone());

                // RentHistoryDto에 대출일 저장
                rentHistoryDto.setRentDate(rent.getRentDate());

                //list에 추가
                rentHistoryDtoList.add(rentHistoryDto);
            }
            return rentHistoryDtoList; //리스트 반환
        } catch (EntityNotFoundException e) {
            throw e; // 예외를 다시 던져서 클라이언트에게 전달
        } catch (Exception e) {
            throw new RuntimeException("대출 내역 조회 중 오류가 발생했습니다.", e);
        }
    }


    // 현재 대출 중인 책인지 확인 해당 bookid + false 인 column 이 있으면 return false (대출중이니까), 없으면 true
    // 현재 대여중이면 true 값 반환
    private boolean isBookAvailableRent(Long bookId) {
        return rentRepository.existsByBookIdAndReturnStatus(bookId, false);
    }

    // 책 빌릴 수 있는 사람인지 확인하는 메서드 returnStatus false 인건 빌린책이 있다는것
    // 빌린 책이 있으면 true 값 반환
    private boolean isMemberBookRentable(Long memberId) {
        return rentRepository.existsByMemberIdAndReturnStatus(memberId, false);
    }


}
