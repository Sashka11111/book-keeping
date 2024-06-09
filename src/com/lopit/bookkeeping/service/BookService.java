package com.lopit.bookkeeping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liamtseva.cookbook.model.Recipes;
import com.liamtseva.cookbook.model.ValidationInput;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RecipesService {

  private static final String RECIPES_FILE_PATH = "Data/recipes.json";
  private static List<Recipes> recipess;

  public static void main(String[] args) {
    recipess = JsonDataReader.modelDataJsonReader(RECIPES_FILE_PATH, Recipes[].class);
    displayRecipes(recipess);
  }

  public static void displayRecipes(List<Recipes> recipesList) {
    for (Recipes recipe : recipesList) {
      System.out.println("ID рецепту: " + recipe.getbook());
      System.out.println("Інгредієнти: " + recipe.getIngredient());
      System.out.println("Назва: " + recipe.getName());
      System.out.println("Категорія: " + recipe.getCategory());
      System.out.println("Інструкція: " + recipe.getInstruction());
      System.out.println("Час готування: " + recipe.getCookingTime());
      System.out.println(""); // Для розділення між записами
    }
  }

  public static void addRecipe() {
    Scanner scanner = new Scanner(System.in);
    List<Recipes> recipes = JsonDataReader.modelDataJsonReader(RECIPES_FILE_PATH, Recipes[].class);

    // Запитати користувача про дані нового рецепту
    System.out.println("Додавання нового рецепту:");

    System.out.println("Введіть назву рецепту:");
    String name = scanner.nextLine();

    System.out.println("Введіть категорію рецепту:");
    String category = scanner.nextLine();

    System.out.println("Введіть інгредієнти рецепту:");
    String ingredients = scanner.nextLine();

    System.out.println("Введіть інструкцію для приготування рецепту:");
    String instruction = scanner.nextLine();

    System.out.println("Введіть час приготування рецепту (у хвилинах):");
    int cookingTime = ValidationInput.getValidIntInput(scanner);

    Recipes newRecipe = new Recipes();
    newRecipe.setbook(
        recipes.size() + 1); // Або використайте інший спосіб генерації унікального ідентифікатора
    newRecipe.setCategory(category);
    newRecipe.setName(name);
    newRecipe.setIngredient(ingredients);
    newRecipe.setInstruction(instruction);
    newRecipe.setCookingTime(cookingTime);

// Додати новий рецепт до списку рецептів
    recipes.add(newRecipe);

    // Зберегти оновлені дані у файлі JSON
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(RECIPES_FILE_PATH), recipes);
      System.out.println("Новий рецепт додано успішно.");
    } catch (IOException e) {
      System.out.println("Помилка при додаванні нового рецепту: " + e.getMessage());
    }
  }

}
