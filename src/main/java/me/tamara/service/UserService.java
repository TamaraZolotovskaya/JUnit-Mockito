package me.tamara.service;


import java.util.List;

public interface UserService {


    List<String> getAllLogin();

    void createUser(String login, String password);

    boolean isUserWithLoginAndPassword(String login, String password);
}







