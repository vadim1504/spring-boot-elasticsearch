package by.vadim.spring.elasticsearch.service;

import by.vadim.spring.elasticsearch.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    Article save(Article article);
    Article findOne(String id);
    Iterable<Article> findAll();
    List<Article> findByTitle(String title);
    Page<Article> findByAuthorName(String name, Pageable pageable);
    Page<Article> findByAuthorNameUsingCustomQuery(String name, Pageable pageable);
    long count();
    void delete(Article article);
}