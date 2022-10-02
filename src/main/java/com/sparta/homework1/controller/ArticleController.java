package com.sparta.homework1.controller;

import com.sparta.homework1.dto.ArticlePasswordRequestDto;
import com.sparta.homework1.dto.ArticleRequestDto;
import com.sparta.homework1.entity.Article;
import com.sparta.homework1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/articles")
    public List<Article> getArticles() throws SQLException {
        List<Article> articles = articleService.getArticles();

        // 응답 보내기
        return articles;
    }

    @GetMapping("/api/article/{id}")
    public Article getArticle(@PathVariable Long id) throws SQLException {
        Article article = articleService.getArticle(id);

        return article;
    }

    @PostMapping("/api/article")
    public Article createArticle(@RequestBody ArticleRequestDto requestDto) throws SQLException {
        Article article = articleService.createArticle(requestDto);

        // 응답 보내기
        return article;
    }

    @PostMapping("/api/article/{id}")
    public String checkPassword(@PathVariable Long id, @RequestBody ArticlePasswordRequestDto requestDto) throws SQLException {
        String response = articleService.checkPassword(id, requestDto);

        // 응답 보내기
        return response;
    }

    @DeleteMapping("/api/article/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return id;
    }

    @PutMapping("/api/article/{id}")
    public Article updateMemo(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        Article article = articleService.update(id, requestDto);
        return article;
    }
}
