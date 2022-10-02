package com.sparta.homework1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArticlePasswordRequestDto {
    private String password;

    public ArticlePasswordRequestDto(String password) {
        this.password = password;
    }
}
