package me.tamara.service;

import me.tamara.User;
import me.tamara.exeption.UserNonUniqueException;
import me.tamara.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserServiceImpl userService;

    final String login1 = "login1";
    final String login2 = "login2";
    final String login3 = "login3";
    final String password1 = "password1";
    final String password2 = "password2";
    final String password3 = "password3";
    final User user1 = new User(login1, password1);
    final User user2 = new User(login2, password2);
    final User user3 = new User(login3, password3);

    @Test
    @DisplayName("When user with this login and password exist then true")
    void isUserWithLoginAndPassword() {
        when(userRepositoryMock.findByLoginAndPassword(login1, password1)).thenReturn(Optional.of(user1));
        assertTrue(userService.isUserWithLoginAndPassword(login1, password1));
    }

    @Test
    @DisplayName("When user with this login and password does not exist then false")
    void isNotUserWithLoginAndPassword() {
        when(userRepositoryMock.findByLoginAndPassword(login1, password2)).thenReturn(Optional.ofNullable(null));
        assertFalse(userService.isUserWithLoginAndPassword(login1, password2));
    }

    @Test
    @DisplayName("When called getAllLogin then all logins return")
    void getAllLogin() {
        when(userRepositoryMock.getAllUsers()).thenReturn(List.of(user1, user2, user3));
        List<String> expectedList = List.of(login1, login2, login3);
        assertEquals(expectedList, userService.getAllLogin());
    }

    @Test
    @DisplayName("When login is null then IllegalArgumentException")
    void createUserNullLogin() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(null, password1));
    }

    @Test
    @DisplayName("When password is null then IllegalArgumentException")
    void createUserNullPassword() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(login1, null));
    }

    @Test
    @DisplayName("When login is blank then IllegalArgumentException")
    void createUserBlankLogin() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(" ", password1));
    }

    @Test
    @DisplayName("When password is blank then IllegalArgumentException")
    void createUserBlankPassword() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(login1, " "));
    }

    @Test
    @DisplayName("When login is not unique then UserNonUniqueException")
    void createUserNotUnique() {
        when(userRepositoryMock.findByLogin(login1)).thenReturn(Optional.of(user1));
        assertThrows(UserNonUniqueException.class, () -> userService.createUser(login1, password1));
    }


}