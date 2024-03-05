package com.example.sparta.library.repository;

import com.example.sparta.library.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {
    boolean existsByBookIdAndReturnStatus(Long bookId, boolean returnStatus);

    boolean existsByMemberIdAndReturnStatus(Long memberId, boolean returnStatus);

    List<Rent> findAllByMemberIdOrderByRentDateAsc(Long memberId);
}
