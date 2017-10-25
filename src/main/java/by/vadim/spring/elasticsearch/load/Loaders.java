package by.vadim.spring.elasticsearch.load;

import by.vadim.spring.elasticsearch.model.User;
import by.vadim.spring.elasticsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loaders {

    @Qualifier("operations")
    @Autowired
    private ElasticsearchOperations operations;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void loadAll(){
        operations.putMapping(User.class);
        userRepository.save(getData());
    }

    private List<User> getData() {
        List<User> users = new ArrayList<>();
        users.add(new User("vadim",1L));
        users.add(new User("alex",12L));
        users.add(new User("misha",123L));
        return users;
    }
}
