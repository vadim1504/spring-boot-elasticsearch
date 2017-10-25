package by.vadim.spring.elasticsearch.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "user", type = "user", shards = 1)
public class User {

    private String name;
    private Long id;

    public User() {
    }

    public User(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
