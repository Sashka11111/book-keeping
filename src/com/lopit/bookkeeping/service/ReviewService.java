package com.lopit.bookkeeping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lopit.bookkeeping.domain.model.Review;
import com.lopit.bookkeeping.domain.validation.ValidationInput;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ReviewService {

  private static final String REVIEWS_FILE_PATH = "Data/reviews.json";
  private static List<Review> reviews;

  public static void main(String[] args) {
    reviews = JsonDataReader.modelDataJsonReader(REVIEWS_FILE_PATH, Review[].class);
    displayReviews(reviews);
  }

  public static void displayReviews(List<Review> reviews) {
    if (reviews.isEmpty()) {
      System.out.println("Список відгуків порожній.");
    } else {
      System.out.println("Список відгуків:");
      for (Review review : reviews) {
        System.out.println("Id відгука: " + review.getId());
        System.out.println("Книга: " + review.getbook());
        System.out.println("Коментар: " + review.getComment());
        System.out.println("Оцінка: " + review.getMark());
        System.out.println(); // Для розділення між записами
      }
    }
  }

  public static void addReview() {
    Scanner scanner = new Scanner(System.in);
    reviews = JsonDataReader.modelDataJsonReader(REVIEWS_FILE_PATH, Review[].class);
    // Запитати користувача про дані нового відгуку
    System.out.println("Додавання нового відгуку:");

    System.out.println("Введіть ID книги:");
    int bookId = ValidationInput.getValidIntInput(scanner);

    System.out.println("Введіть оцінку (від 1 до 5):");
    int mark = ValidationInput.getValidIntInput(scanner);

    System.out.println("Введіть коментар:");
    String comment = scanner.nextLine();

    Review newReview = new Review(); // Створюємо новий об'єкт відгуку

    // Встановлюємо властивості відгуку
    int newReviewId = generateUniqueId();
    newReview.setId(newReviewId);
    newReview.setbook(bookId); // ID книги
    newReview.setMark(mark); // Оцінка
    newReview.setComment(comment); // Коментар

    reviews.add(newReview);

    // Зберегти оновлені дані у файлі JSON
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(REVIEWS_FILE_PATH), reviews);
      System.out.println("Новий відгук додано успішно.");
    } catch (IOException e) {
      System.out.println("Помилка при додаванні нового відгуку: " + e.getMessage());
    }
  }

  public static void editReview(int reviewId) {
    Scanner scanner = new Scanner(System.in);

    for (Review review : reviews) {
      if (review.getId() == reviewId) {
        System.out.println("Editing review with ID: " + reviewId);
        System.out.println("Enter new mark (1 to 5):");
        int newMark = ValidationInput.getValidIntInput(scanner);
        System.out.println("Enter new comment:");
        String newComment = scanner.nextLine();

        // Update review properties
        review.setMark(newMark);
        review.setComment(newComment);

        // Save updated reviews to JSON file
        saveReviewsToJson();

        System.out.println("Review updated successfully.");
        return;
      }
    }

    System.out.println("Review not found with ID: " + reviewId);
  }

  public static void deleteReview(int reviewId) {
    Iterator<Review> iterator = reviews.iterator();
    boolean found = false;

    while (iterator.hasNext()) {
      Review review = iterator.next();
      if (review.getId() == reviewId) {
        iterator.remove();
        found = true;
        break;
      }
    }

    if (found) {
      // Save updated reviews to JSON file
      saveReviewsToJson();
      System.out.println("Review deleted successfully.");
    } else {
      System.out.println("Review not found with ID: " + reviewId);
    }
  }

  private static void saveReviewsToJson() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(REVIEWS_FILE_PATH), reviews);
    } catch (IOException e) {
      System.out.println("Error saving reviews to JSON file: " + e.getMessage());
    }
  }
  // Логіка для генерації унікального ідентифікатора для нового відгуку, що починається з 1 і збільшується на 1 з кожним новим відгуком
  private static int generateUniqueId() {
    // Ініціалізуємо локальну змінну, що буде зберігати наступне значення унікального ідентифікатора
    int nextId = 1;

    // Шукаємо максимальний ідентифікатор серед існуючих відгуків
    for (Review review : reviews) {
      // Якщо ідентифікатор поточного відгуку більший за поточне значення nextId, оновлюємо nextId
      if (review.getId() >= nextId) {
        nextId = review.getId() + 1; // Збільшуємо на 1
      }
    }

    return nextId; // Повертаємо наступне унікальне значення ідентифікатора
  }

}
