package src.com.liamtseva.cookbook.service;

import com.liamtseva.cookbook.model.Category;
import java.util.List;

public class CategoryService {

  private static final String CATEGORY_FILE_PATH = "Data/category.json";

  public static void main(String[] args) {

    List<Category> categories = JsonDataReader.modelDataJsonReader(CATEGORY_FILE_PATH,
        Category[].class);
    displayCategories(categories);

  }

  public static void displayCategories(List<Category> categories) {
    if (categories.isEmpty()) {
      System.out.println("Список категорій порожній.");
    } else {
      System.out.println("Список категорій:");
      for (Category category : categories) {
        System.out.println("ID категорії: " + category.getCategoryId());
        System.out.println("Назва: " + category.getName());
        System.out.println(); // Для розділення між записами
      }
    }
  }
}

