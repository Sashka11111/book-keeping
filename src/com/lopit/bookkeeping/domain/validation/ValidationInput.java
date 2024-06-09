package com.lopit.bookkeeping.validation;

import java.util.Scanner;

public class ValidationInput {

  public static boolean checkNumericInput(String[] inputs) {
    for (String input : inputs) {
      if (!input.trim().matches("-?\\d+(\\.\\d+)?")) {
        return false;
      }
    }
    return true;
  }

  // Метод для отримання від користувача правильного числового вводу типу double
  public static double getValidDoubleInput(Scanner scanner) {
    double input;
    while (true) {
      try {
        input = Double.parseDouble(scanner.nextLine().trim());
        break; // Правильний ввід, виходимо з циклу
      } catch (NumberFormatException e) {
        System.out.println("Неправильний формат. Будь ласка, введіть числове значення:");
      }
    }
    return input;
  }

  // Метод для отримання від користувача правильного числового вводу типу int
  public static int getValidIntInput(Scanner scanner) {
    int input;
    while (true) {
      try {
        input = Integer.parseInt(scanner.nextLine().trim());
        break; // Правильний ввід, виходимо з циклу
      } catch (NumberFormatException e) {
        System.out.println("Неправильний формат. Будь ласка, введіть ціле числове значення:");
      }
    }
    return input;
  }
}
