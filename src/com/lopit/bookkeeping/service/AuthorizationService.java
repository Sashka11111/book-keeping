package src.com.liamtseva.cookbook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liamtseva.cookbook.cookbook.Application;
import com.liamtseva.cookbook.model.User;
import com.liamtseva.cookbook.view.Menu;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AuthorizationService {

  public static void authorization() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введіть логін:");
    String username = scanner.nextLine();

    System.out.println("Введіть пароль:");
    String password = scanner.nextLine();
    System.out.println("Ви ввели логін: " + username + " і пароль: " + password);

    List<User> users = readUsersFromJson(
        "Data/user.json");

    User user = findUserByUsername(users, username);

    if (user != null && user.getPassword().equals(password)) {
      Application.currentUser = user;
      System.out.println("Авторизація пройшла успішно.");
      try {
        Menu.show();
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    } else {
      System.out.println("Помилка авторизації. Перевірте логін та пароль.");
    }
  }

  private static User findUserByUsername(List<User> users, String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
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



