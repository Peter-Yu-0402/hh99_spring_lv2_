package com.example.sparta.library.dto;

import com.example.sparta.library.utils.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> { //응답 담아서 보내기

    private final ResponseStatus status;
    private final String message;
    private final T data;
}
