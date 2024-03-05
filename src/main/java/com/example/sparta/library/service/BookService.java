package com.example.sparta.library.service;

import com.example.sparta.library.dto.BookRequestDto;
import com.example.sparta.library.dto.BookResponseDto;
import com.example.sparta.library.entity.Book;
import com.example.sparta.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 도서 등록
    public BookResponseDto addBook(BookRequestDto requestDto) {
            Book book = new Book(requestDto);
            bookRepository.save(book);
        return new BookResponseDto(book);
    }

    public List<BookResponseDto> getBookList() {
        // 등록일자, 책 제목 순으로 내림차순
       return bookRepository.findAllByOrderByCreatedAtDescTitleDesc().stream().map(BookResponseDto::new).toList();
    }

    public BookResponseDto getBookInfo(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("도서를 찾을 수 없습니다.")
        );
        return new BookResponseDto(book);
    }
}
