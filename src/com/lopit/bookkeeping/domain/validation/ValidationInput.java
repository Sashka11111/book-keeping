package com.lopit.bookkeeping.domain.validation;

import java.util.Scanner;

/**
 * Утилітний клас для валідації та обробки вводу користувача.
 */
public class ValidationInput {

  /**
   * Перевіряє, чи всі введені значення є числовими.
   *
   * @param inputs Масив вхідних рядків.
   * @return true, якщо всі введення є числовими, інакше false.
   */
  public static boolean checkNumericInput(String[] inputs) {
    for (String input : inputs) {
      if (!input.trim().matches("-?\\d+(\\.\\d+)?")) {
        return false;
      }
    }
    return true;
  }

  /**
   * Запитує у користувача правильне числове значення типу double.
   *
   * @param scanner Об'єкт Scanner для зчитування вводу користувача.
   * @return Правильне числове значення типу double, введене користувачем.
   */
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

  /**
   * Запитує у користувача правильне числове значення типу int.
   *
   * @param scanner Об'єкт Scanner для зчитування вводу користувача.
   * @return Правильне числове значення типу int, введене користувачем.
   */
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
  public static String getValidPhoneInput(Scanner scanner) {
    String input;
    while (true) {
      input = scanner.nextLine().trim();
      if (input.matches("^\\+[0-9]+$")) {
        break; // Правильний ввід, виходимо з циклу
      } else {
        System.out.println("Неправильний формат номеру телефону. Почніть номер з + та введіть цифри без пробілів.");
      }
    }
    return input;
  }

}
