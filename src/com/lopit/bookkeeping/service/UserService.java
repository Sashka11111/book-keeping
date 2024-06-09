package src.com.liamtseva.cookbook.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liamtseva.cookbook.model.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {

  private static final String USER_FILE_PATH = "Data/user.json";

  private final List<User> users;

  public UserService() {
    this.users = new ArrayList<>();
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
        List<User> users = JsonDataReader.modelDataJsonReader(USER_FILE_PATH, User[].class);
        for (User user : users) {
          if (user.getUsername().equals(username)) {
            user.setPassword(newPassword);
            user.setEmail(newEmail);
            break;
          }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File(USER_FILE_PATH), users);
        System.out.println("Дані користувача успішно оновлені та збережені.");
      } catch (IOException e) {
        System.out.println("Помилка при збереженні оновлених даних користувача: " + e.getMessage());
      }
    } else {
      System.out.println("Користувача з ім'ям " + username + " не знайдено.");
    }
  }

  public static void saveUserData(User user) {
  }


  public void createUser(User user) {
    users.add(user);
  }

  public List<User> getAllUsers() {
    return new ArrayList<>(users);
  }

  // Метод для отримання користувача за ідентифікатором
  public User getUserById(long userId) {
    for (User user : users) {
      if (user.getId().equals(userId)) {
        return user;
      }
    }
    return null;
  }

  // Метод для оновлення інформації про користувача
  public void updateUser(User updatedUser) {
    for (User user : users) {
      if (user.getId() == updatedUser.getId()) {
        // Оновлення інформації про користувача
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        break;
      }
    }
  }

  public User getUserByUsername(String username) {
    List<User> usersJson = JsonDataReader.modelDataJsonReader(USER_FILE_PATH, User[].class);
    for (User user : usersJson) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null; // Якщо користувач не знайдений
  }
}