package by.vadim.spring.elasticsearch.builder;

import by.vadim.spring.elasticsearch.model.Article;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchQueryBuilder {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public List<Article> getAll(String text){
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(
                        QueryBuilders.queryStringQuery(text)
                        .lenient(true)
                        .field("title")
                        .field("tags")
                ).should(QueryBuilders.queryStringQuery("*"+text+"*")
                        .lenient(true)
                        .field("title")
                        .field("tags")
                );
        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(query).build();
        return elasticsearchTemplate.queryForList(build, Article.class);
    }
}
