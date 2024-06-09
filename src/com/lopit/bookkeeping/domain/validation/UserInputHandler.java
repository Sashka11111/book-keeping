package com.lopit.bookkeeping.validation;

import java.util.Scanner;

public class UserInputHandler {

  private Scanner scanner;

  public UserInputHandler() {
    this.scanner = new Scanner(System.in);
  }

  // Метод для отримання цілочисельного значення від користувача
  public int promptUserForInteger(String prompt) {
    System.out.print(prompt + ": ");

    while (!scanner.hasNextInt()) {
      System.out.println("Будь ласка, введіть ціле число.");
      System.out.print(prompt + ": ");
      scanner.next();
    }
    int result = scanner.nextInt();
    scanner.nextLine();
    return result;
  }

}
