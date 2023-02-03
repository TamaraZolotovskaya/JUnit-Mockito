package me.tamara.repository;

import me.tamara.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {

    UserRepository userRepository = new UserRepositoryImpl();
    final String login1 = "login1";
    final String login2 = "login2";
    final String login3 = "login3";
    final String password1 = "password1";
    final String password2 = "password2";
    final String password3 = "password3";
    final User user1 = new User(login1, password1);
    final User user2 = new User(login2, password2);
    final User user3 = new User(login3, password3);

    @BeforeEach
    public void setUp() {
        userRepository.addUser(user1);
    }


    @Test
    @DisplayName("When the set of users is empty then an empty collection is returned")
    void getAllUsersFromEmptySet() {
        userRepository.clearUsers();
        assertTrue(userRepository.getAllUsers().isEmpty(), "Collection must be empty");
    }


    @Test
    @DisplayName("When called getAllUsers then all users return")
    void getAllUsers() {
        final Set<User> expectedUsers = Set.of(user1, user2, user3);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        assertEquals(expectedUsers, userRepository.getAllUsers());
    }

    @Test
    @DisplayName("When user with expected login exist then the user return")
    void findByLoginIfLoginExist() {
        assertTrue(userRepository.findByLogin(login1).isPresent());
    }

    @Test
    @DisplayName("When user with expected login does not exist then correct result")
    void findByLoginIfLoginNotExist() {
        assertTrue(userRepository.findByLogin(login2).isEmpty());
    }

    @Test
    @DisplayName("When user with expected login and password exist then the user return")
    void findByLoginAndPasswordIfLoginAndPasswordExist() {
        assertTrue(userRepository.findByLoginAndPassword(login1, password1).isPresent());
    }

    @Test
    @DisplayName("When expected login exist and expected password does not exist then correct result")
    void findByLoginAndPasswordIfOnlyLoginExist() {
        assertTrue(userRepository.findByLoginAndPassword(login1, password3).isEmpty());
    }

    @Test

    @DisplayName("When expected login does not exist and expected password exist then correct result")
    void findByLoginAndPasswordIfOnlyPasswordExist() {
        assertTrue(userRepository.findByLoginAndPassword(login2, password1).isEmpty());
    }

}