package me.tamara.service;

import me.tamara.User;
import me.tamara.exeption.UserNonUniqueException;
import me.tamara.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getAllLogin() {
        return userRepository.getAllUsers().stream()
                .map(User::getLogin)
                .toList();
    }

    @Override
    public void createUser(String login, String password) {
        if (login==null || password==null || login.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Login or password is not correct");
        }
        if (userRepository.findByLogin(login).isPresent()) {
            throw new UserNonUniqueException();
        }
        userRepository.addUser(new User(login, password));
    }

    @Override
    public boolean isUserWithLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).isPresent();
    }


}
