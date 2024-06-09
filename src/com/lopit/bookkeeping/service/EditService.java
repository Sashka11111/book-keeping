package src.com.liamtseva.cookbook.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liamtseva.cookbook.model.Recipes;
import com.liamtseva.cookbook.model.ValidationInput;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EditService {

  private static final String RECIPES_FILE_PATH = "Data/recipes.json";
  private static final String RESPONSES_FILE_PATH = "Data/responses.json";

  public static void editRecipe() {
    Scanner scanner = new Scanner(System.in);
    List<Recipes> recipes = JsonDataReader.modelDataJsonReader(RECIPES_FILE_PATH, Recipes[].class);

    // Вивести користувачу усі рецепти
    System.out.println("Список доступних рецептів:");
    for (Recipes recipe : recipes) {
      System.out.println("ID рецепту: " + recipe.getRecipesId() + ", "
          + "Назва: " + recipe.getName() + ", "
          + "Категорія: " + recipe.getCategory() + ", "
          + "Інгредієнти: " + recipe.getIngredient() + ", "
          + "Інструкція: " + recipe.getInstruction() + ", "
          + "Час приготування: " + recipe.getCookingTime() + " хв.");
    }

    System.out.println("Введіть ID рецепту, який ви хочете редагувати:");
    int idRecipe = ValidationInput.getValidIntInput(scanner);

    Recipes selectedRecipe = null;
    for (Recipes recipe : recipes) {
      if (recipe.getRecipesId() == idRecipe) {
        selectedRecipe = recipe;
        break;
      }
    }

    if (selectedRecipe != null) {
      // Перевірити, чи користувач хоче редагувати рецепт
      System.out.println("Бажаєте редагувати цей рецепт? (Так/Ні):");
      String response = scanner.nextLine();

      if (response.equalsIgnoreCase("так")) {
        // Просимо користувача ввести назву
        System.out.println("Введіть нову назву рецепту:");
        String newName = scanner.nextLine();
        selectedRecipe.setName(newName);

        System.out.println("Введіть нову категорію:");
        String newCategory = scanner.nextLine();
        selectedRecipe.setCategory(newCategory);
        // Просимо користувача ввести інгредієнти
        System.out.println("Введіть нові інгредієнти:");
        String newIngredients = scanner.nextLine();
        selectedRecipe.setIngredient(newIngredients);

        System.out.println("Введіть нову інструкцію:");
        String newInstruction = scanner.nextLine();
        selectedRecipe.setInstruction(newInstruction);
        // Просимо користувача ввести час приготування
        System.out.println("Введіть новий час приготування (у хвилинах):");
        int newCookingTime = ValidationInput.getValidIntInput(scanner);
        selectedRecipe.setCookingTime(newCookingTime);

        // Зберегти оновлені дані у файлі JSON
        try {
          ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
          objectMapper.writeValue(new File(RECIPES_FILE_PATH), recipes);
          System.out.println("Оновлені дані рецепту збережено успішно.");
        } catch (IOException e) {
          System.out.println("Помилка при збереженні оновлених даних рецепту: " + e.getMessage());
        }
      } else {
        System.out.println("Редагування скасовано.");
      }
    } else {
      System.out.println("Рецепт з введеним ID не знайдено.");
    }
  }

}
