package ru.seflet.chat.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.seflet.chat.domain.User;
import ru.seflet.chat.form.UserCredentials;
import ru.seflet.chat.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(UserCredentials userCredentials) {
        User user = new User();
        user.setLogin(userCredentials.getLogin());
        userRepository.save(user);
        return user;
    }

    public List<User> getUsers(Integer page) {
        return userRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User findById(Long id) {
        return id == null? null : userRepository.findById(id).orElse(null);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}
