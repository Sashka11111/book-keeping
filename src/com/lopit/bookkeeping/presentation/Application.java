package com.lopit.bookkeeping.presentation;

import com.lopit.bookkeeping.domain.model.User;
import java.util.ArrayList;
import java.util.List;

public class Application {
  public static List<User> librariansList = new ArrayList<>();
  public static List<User> readersList = new ArrayList<>();
  public static User[] users;
  public static User currentUser;

  static {
    // Додати бібліотекаря
    librariansList.add(new User("librarian1", "password1", "librarian@example.com", "+380968893566","Бібліотекар"));
    // Додати читача
    readersList.add(new User("sofia", "123456", "sofia@gmail.com", "+380968893566","Читач"));
  }

  public static void runner() throws IllegalAccessException {
    Menu.show();
  }

  public static void main(String[] args) throws IllegalAccessException {
    String art = "───▄▀▀▀▄▄▄▄▄▄▄▀▀▀▄───\n"
        + "───█▒▒░░░░░░░░░▒▒█───\n"
        + "────█░░█░░░░░█░░█────\n"
        + "─▄▄──█░░░▀█▀░░░█──▄▄─\n"
        + "█░░█─▀▄░░░░░░░▄▀─█░░█\n"
        + "█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n"
        + "█░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█\n"
        + "█░░║║║╠─║─║─║║║║║╠─░░█\n"
        + "█░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█\n"
        + "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█";
    System.out.println(art);
    runner();
  }

  public static List<User> getLibrariansList() {
    return librariansList;
  }

  public static List<User> getReadersList() {
    return readersList;
  }
}
