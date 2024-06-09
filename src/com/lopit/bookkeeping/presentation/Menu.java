package com.lopit.bookkeeping.view;

import com.lopit.bookkeeping.bookkeeping.Application;
import com.lopit.bookkeeping.service.AuthorizationService;
import com.lopit.bookkeeping.service.BookService;
import com.lopit.bookkeeping.service.EditService;
import com.lopit.bookkeeping.service.RegistrationService;
import com.lopit.bookkeeping.service.SearchService;
import com.lopit.bookkeeping.validation.UserInputHandler;

public class Menu {

  public Menu() {
  }

  public static void show() throws IllegalAccessException {
    UserInputHandler userInputHandler = new UserInputHandler();
    while (true) {
      if (Application.currentUser == null) {
        System.out.println("1) Реєстрація");
        System.out.println("2) Авторизація");
        System.out.println("0) Вихід");

        int choice = userInputHandler.promptUserForInteger("Ваш вибір");

        switch (choice) {
          case 1:
            RegistrationService.registration();
            break;
          case 2:
            AuthorizationService.authorization();
            break;
          case 0:
            System.out.println("Дякую за використання.");
            System.exit(0);
            break;
          default:
            System.out.println("Невірний вибір. Спробуйте ще раз.");
            break;
        }
        continue; // Повернення на початок циклу
      }

      String userRole = Application.currentUser.getRole();

      if ("".equals(userRole)) {
        System.out.println("1) Реєстрація");
        System.out.println("2) Авторизація");
      } else {
        String art = "      ______ ______\n" +
            "    _/      Y      \\_\n" +
            "   // ~~ ~~ | ~~ ~  \\\\\n" +
            "  // ~ ~ ~~ | ~~~ ~~ \\\\\n" +
            " //________.|.________\\\\\\\n" +
            "`----------`-'----------'";

        System.out.println(art);
        System.out.println("1) Вийти з головного меню");
        System.out.println("2) Перегляд даних");
        System.out.println("3) Пошук книг");

        if ("Бібліотекар".equals(userRole)) {
          System.out.println("4) Додавання книг");
          System.out.println("5) Редагування книг");
        }
      }

      System.out.println("0) Вихід");

      int choice = userInputHandler.promptUserForInteger("Ваш вибір");

      switch (choice) {
        case 1:
          if ("".equals(userRole)) {
            RegistrationService.registration();
          } else {
            Application.currentUser = null; // Вихід з головного меню
          }
          break;
        case 2:
          if ("".equals(userRole)) {
            AuthorizationService.authorization();
          } else {
            BookService.viewBooks(); // Перегляд даних
          }
          break;
        case 3:
          if ("".equals(userRole)) {
            break;
          } else {
            SearchService.searchService(); // Пошук книг
            break;
          }
        case 4:
          if (!"Бібліотекар".equals(userRole)) {
            break;
          } else {
            BookService.addBook(); // Додавання книг
            break;
          }
        case 5:
          if (!"Бібліотекар".equals(userRole)) {
            break;
          } else {
            EditService.editbook(); // Редагування книг
            break;
          }
        case 0:
          System.out.println("Дякую за використання.");
          System.exit(0);
          break;
        default:
          System.out.println("Невірний вибір. Спробуйте ще раз.");
          break;
      }
    }
  }
}
