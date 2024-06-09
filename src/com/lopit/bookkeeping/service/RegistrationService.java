package src.com.liamtseva.cookbook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liamtseva.cookbook.cookbook.Application;
import com.liamtseva.cookbook.model.User;
import com.liamtseva.cookbook.view.Menu;
import com.liamtseva.cookbook.view.UserInputHandler;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RegistrationService {

  public static void registration() {
    String username;
    String password;
    String email;

    do {
      System.out.println("Введіть логін");
      Scanner scanner = new Scanner(System.in);
      username = scanner.nextLine();
      if (Application.users != null && !isLoginUnique(username)) {
        System.out.println("Цей логін вже використовується. Виберіть інший.");
      }
    } while (Application.users != null && !isLoginUnique(username));

    System.out.println("Введіть пароль");
    password = new Scanner(System.in).nextLine();
    System.out.println("Введіть email");
    email = new Scanner(System.in).nextLine();

    // Add a menu for role selection
    System.out.println("Оберіть роль:");
    System.out.println("1) Користувач");
    System.out.println("2) Адмін");
    int roleChoice = new UserInputHandler().promptUserForInteger("Ваш вибір");

    String role;
    switch (roleChoice) {
      case 1:
        role = "Користувач";
        break;
      case 2:
        role = "Адмін";
        break;
      default:
        role = "Користувач";
        break;
    }

    User newUser = new User(username, password, email, role);
    Application.currentUser = newUser;

    if (Application.users == null) {
      Application.users = new User[]{};
    }
    Application.users = addNewUser(Application.users, newUser);

    saveUsersToJson(Application.users,
        "Data/user.json");

    System.out.println("Реєстрація пройшла успішно.");
    try {
      Menu.show();
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private static User[] addNewUser(User[] users, User newUser) {
    User[] newUsers = Arrays.copyOf(users, users.length + 1);
    newUsers[users.length] = newUser;
    return newUsers;
  }

  private static boolean isLoginUnique(String login) {
    if (Application.users == null) {
      return true; // If no users, login is unique
    }
    for (User existingUser : Application.users) {
      if (existingUser.getUsername().equals(login)) {
        return false; // Login is not unique
      }
    }
    return true; // Login is unique
  }

  private static void saveUsersToJson(User[] users, String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      File file = new File(filePath);
      User[] existingUsers;

      if (file.exists()) {
        // If the file exists, try to deserialize it into an array of users
        existingUsers = objectMapper.readValue(file, User[].class);
      } else {
        // If the file doesn't exist, create an empty array
        existingUsers = new User[]{};
      }

      // Concatenate the existing users with the new user
      User[] updatedUsers = Arrays.copyOf(existingUsers, existingUsers.length + 1);
      updatedUsers[existingUsers.length] = users[0];

      // Write the updated array back to the file
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, updatedUsers);

    } catch (IOException e) {
      e.printStackTrace();
      // Handle errors during file operations
    }
  }
}
