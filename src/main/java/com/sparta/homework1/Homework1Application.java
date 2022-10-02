package com.sparta.homework1;

import com.sparta.homework1.dto.ArticleRequestDto;
import com.sparta.homework1.entity.Article;
import com.sparta.homework1.repository.ArticleRepository;
import com.sparta.homework1.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
@EnableJpaAuditing
@SpringBootApplication
public class Homework1Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework1Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ArticleRepository articleRepository, ArticleService articleService) {
		return (args) -> {
			articleRepository.save(new Article("제목01", "이도운", "1111", "내용01"));
			articleRepository.save(new Article("제목02", "이도희", "2222", "내용02"));
			articleRepository.save(new Article("제목03", "이도영", "3333", "내용03"));

			System.out.println("데이터 인쇄");
			List<Article> articleList = articleRepository.findAll();
			for (int i=0; i<articleList.size(); i++) {
				Article article = articleList.get(i);
				System.out.println(article.getId());
				System.out.println(article.getTitle());
				System.out.println(article.getAuthor());
				System.out.println(article.getPassword());
				System.out.println(article.getContent());
			}
		};
	}
}
