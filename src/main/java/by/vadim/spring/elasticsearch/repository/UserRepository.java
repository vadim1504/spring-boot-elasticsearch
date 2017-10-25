package by.vadim.spring.elasticsearch.repository;

import by.vadim.spring.elasticsearch.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, Long> {
    List<User> findByName(String text);
}
