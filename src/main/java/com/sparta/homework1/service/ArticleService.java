package com.sparta.homework1.service;

import com.sparta.homework1.dto.ArticlePasswordRequestDto;
import com.sparta.homework1.dto.ArticleRequestDto;
import com.sparta.homework1.entity.Article;
import com.sparta.homework1.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    @Autowired
    private final ArticleRepository articleRepository;

    public List<Article> getArticles() throws SQLException {
        List<Article> articles = articleRepository.findAll();

        return articles;
    }

    public Article getArticle(Long id) throws SQLException {
        Article article = articleRepository.findById(id).orElse(null);

        return article;
    }

    public String checkPassword(Long id, ArticlePasswordRequestDto requestDto) throws SQLException {
        Article article = articleRepository.findById(id).orElse(null);

        try {
            if(article.getPassword().equals(requestDto.getPassword())) {
                return "일치합니다.";
            } else {
                return "일치하지 않습니다.";
            }
        }
        catch (NullPointerException ex) {
            return ex.getMessage();
        }
    }

    public Article createArticle(ArticleRequestDto requestDto) throws SQLException {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Article article = new Article(requestDto);

        articleRepository.save(article);

        return article;
    }

    public Long deleteArticle(Long id) {
        articleRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Article update(Long id, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        article.setAuthor(requestDto.getAuthor());
        article.setTitle(requestDto.getTitle());
        article.setContent(requestDto.getContent());
        article.setPassword(requestDto.getPassword());
        articleRepository.save(article);

        return article;
    }
}
