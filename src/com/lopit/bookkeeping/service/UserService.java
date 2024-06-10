package com.lopit.bookkeeping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lopit.bookkeeping.domain.model.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {

  private static final String USERS_FILE_PATH = "Data/users.json";

  private final List<User> users;

  public UserService() {
    this.users = new ArrayList<>();
    // Ініціалізація з існуючого файлу JSON (якщо потрібно)
    loadUsersFromJson();
  }

  private void loadUsersFromJson() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      User[] usersArray = objectMapper.readValue(new File(USERS_FILE_PATH), User[].class);
      for (User user : usersArray) {
        users.add(user);
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Помилка при завантаженні користувачів: " + e.getMessage());
    }
  }

  public static void updateUserInMenu() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Введіть ім'я користувача якого хочете змінити:");
    String username = scanner.nextLine();

    UserService userService = new UserService();
    User userToUpdate = userService.getUserByUsername(username);

    if (userToUpdate != null) {
      System.out.println("Введіть новий пароль:");
      String newPassword = scanner.nextLine();

      System.out.println("Введіть нову електронну пошту:");
      String newEmail = scanner.nextLine();

      userToUpdate.setPassword(newPassword);
      userToUpdate.setEmail(newEmail);

      try {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File(USERS_FILE_PATH), userService.getAllUsers().toArray(new User[0]));
        System.out.println("Дані користувача успішно оновлені та збережені.");
      } catch (IOException e) {
        System.out.println("Помилка при збереженні оновлених даних користувача: " + e.getMessage());
      }
    } else {
      System.out.println("Користувача з ім'ям " + username + " не знайдено.");
    }
  }

  public void createUser(User user) {
    users.add(user);
    saveUsersToJson();
  }

  public List<User> getAllUsers() {
    return new ArrayList<>(users);
  }

  public User getUserByUsername(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null; // Якщо користувач не знайдений
  }

  private void saveUsersToJson() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(USERS_FILE_PATH), users.toArray(new User[0]));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void updateUser(User updatedUser) {
    User user = getUserByUsername(updatedUser.getUsername());
    if (user != null) {
      user.setPassword(updatedUser.getPassword());
      user.setEmail(updatedUser.getEmail());
      saveUsersToJson();
    }
  }
}
