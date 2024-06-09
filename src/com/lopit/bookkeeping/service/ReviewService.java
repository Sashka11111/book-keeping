package com.lopit.bookkeeping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liamtseva.cookbook.model.Response;
import com.liamtseva.cookbook.model.User;
import com.liamtseva.cookbook.model.ValidationInput;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ResponseService {

  private static final String RESPONSE_FILE_PATH = "Data/response.json";
  private static List<Response> responses;

  public static void main(String[] args) {
    List<Response> responses = JsonDataReader.modelDataJsonReader(RESPONSE_FILE_PATH,
        Response[].class);
    displayResponses(responses);
  }

  public static void displayResponses(List<Response> responses) {
    if (responses.isEmpty()) {
      System.out.println("Список відгуків порожній.");
    } else {
      System.out.println("Список відгуків:");
      for (Response response : responses) {
        System.out.println("ID рецепта: " + response.getbook());
        System.out.println("Коментар: " + response.getComment());
        System.out.println("Автор відгука: " + response.getAuthor());
        System.out.println("Оцінка: " + response.getMark());
        System.out.println(); // Для розділення між записами
      }
    }
  }

  public static void addResponse() {
    Scanner scanner = new Scanner(System.in);
    List<Response> responses = JsonDataReader.modelDataJsonReader(RESPONSE_FILE_PATH,
        Response[].class);

    // Запитати користувача про дані нового відгуку
    System.out.println("Додавання нового відгуку:");

    System.out.println("Введіть ID рецепту:");
    int recipeId = ValidationInput.getValidIntInput(scanner);

    System.out.println("Введіть оцінку (від 1 до 5):");
    int mark = ValidationInput.getValidIntInput(scanner);

    System.out.println("Введіть коментар:");
    String comment = scanner.nextLine();

    Response newResponse = new Response(); // Створюємо новий об'єкт відгуку

// Встановлюємо властивості відгуку
    newResponse.setbook(recipeId); // ID рецепту
    newResponse.setMark(mark); // Оцінка
    newResponse.setComment(comment); // Коментар

// Додати новий рецепт до списку рецептів
    responses.add(newResponse);
    // Зберегти оновлені дані у файлі JSON
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(RESPONSE_FILE_PATH), responses);
      System.out.println("Новий відгук додано успішно.");
    } catch (IOException e) {
      System.out.println("Помилка при додаванні нового відгуку: " + e.getMessage());
    }
  }

  // Приклад методу, що отримує поточного користувача
  private static User getCurrentUser() {
    // Реалізація логіки для отримання поточного користувача
    return new User("username", "password", "email",
        "role"); // Приклад створення нового користувача
  }
}
