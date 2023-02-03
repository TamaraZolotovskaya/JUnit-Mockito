package me.tamara.repository;

import me.tamara.User;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    private final Set<User> users = new HashSet<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }


    @Override
    public void clearUsers() {
        users.clear();
    }

    @Override
    public Collection<User> getAllUsers() {
        return Collections.unmodifiableSet(users);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

}
