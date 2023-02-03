package me.tamara.repository;

import me.tamara.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    void addUser(User user);

    void clearUsers();

    Collection<User> getAllUsers();

    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndPassword(String login, String password);
}
