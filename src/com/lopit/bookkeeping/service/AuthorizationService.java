package com.lopit.bookkeeping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lopit.bookkeeping.presentation.Application;
import com.lopit.bookkeeping.domain.model.User;
import com.lopit.bookkeeping.presentation.Menu;
import com.lopit.bookkeeping.domain.validation.UserInputHandler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AuthorizationService {

  public static void authorization() {
    // Додати меню для вибору ролі
    System.out.println("Оберіть роль:");
    System.out.println("1) Бібліотекар");
    System.out.println("2) Читач");
    int roleChoice = new UserInputHandler().promptUserForInteger("Ваш вибір");

    String role;
    switch (roleChoice) {
      case 1:
        role = "Бібліотекар";
        break;
      case 2:
        role = "Читач";
        break;
      default:
        System.out.println("Невірний вибір ролі.");
        return;
    }

    Scanner scanner = new Scanner(System.in);
    System.out.println("Введіть логін:");
    String username = scanner.nextLine();

    System.out.println("Введіть пароль:");
    String password = scanner.nextLine();
    System.out.println("Ви ввели логін: " + username + " і пароль: " + password);

    List<User> users = readUsersFromJson("Data/users.json");

    User user = findUserByUsernameAndRole(users, username, role);

    if (user != null && user.getPassword().equals(password)) {
      Application.currentUser = user;
      System.out.println("Авторизація пройшла успішно.");
      try {
        Menu.show();
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }   if (user == null) {
      System.out.println("Це не Ваша роль, авторизуйтесь знову.");
    } else {
      System.out.println("Помилка авторизації. Перевірте логін та пароль.");
    }
  }

  private static User findUserByUsernameAndRole(List<User> users, String username, String role) {
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getRole().equals(role)) {
        return user;
      }
    }
    return null;
  }

  private static List<User> readUsersFromJson(String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return Arrays.asList(objectMapper.readValue(new File(filePath), User[].class));
    } catch (IOException e) {
      e.printStackTrace();
      // Обробка помилок при читанні з файлу
      return new ArrayList<>();
    }
  }
}
