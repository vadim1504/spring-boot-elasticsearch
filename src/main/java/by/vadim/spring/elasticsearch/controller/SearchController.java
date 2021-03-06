package by.vadim.spring.elasticsearch.controller;

import by.vadim.spring.elasticsearch.builder.SearchQueryBuilder;
import by.vadim.spring.elasticsearch.model.Article;
import by.vadim.spring.elasticsearch.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rest/search")
public class SearchController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private SearchQueryBuilder queryBuilder;
    @Autowired
    private ElasticsearchTemplate template;

    @GetMapping("/nameAuthor/{text}")
    public Page<Article> searchNameAuthor(@PathVariable String text){
        return articleService.findByAuthorName(text,new PageRequest(0,10));
    }

    @GetMapping("/nameAuthorCustomQuery/{text}")
    public Page<Article> searchNameAuthorUsingCustomQuery(@PathVariable String text){
        return articleService.findByAuthorNameUsingCustomQuery(text,new PageRequest(0,10));
    }

    @GetMapping("/title/{text}")
    public List<Article> searchTitle(@PathVariable String text){
        return articleService.findByTitle(text);
    }

    @GetMapping("/all")
    public List<Article> all(){
        List<Article> articles = new ArrayList<>();
        articleService.findAll().forEach(articles::add);
        return articles;
    }

    @GetMapping("/{text}")
    public List<Article> getAll(@PathVariable String text){
       return queryBuilder.getAll(text);
    }

    @GetMapping("/delete")
    public boolean delete(){
        return template.deleteIndex(Article.class);
    }
}
