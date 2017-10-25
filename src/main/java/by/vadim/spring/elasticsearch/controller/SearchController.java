package by.vadim.spring.elasticsearch.controller;

import by.vadim.spring.elasticsearch.model.User;
import by.vadim.spring.elasticsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserRepository userRepository;

    @GetMapping("/name/{text}")
    public List<User> searchName(@PathVariable String text){
        return userRepository.findByName(text);
    }

    @GetMapping("/all")
    public List<User> all(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
