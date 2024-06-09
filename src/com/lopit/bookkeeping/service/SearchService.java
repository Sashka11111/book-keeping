package src.com.liamtseva.cookbook.service;

import com.liamtseva.cookbook.model.Recipes;
import com.liamtseva.cookbook.model.ValidationInput;
import java.util.List;
import java.util.Scanner;

public class SearchService {

  private static final String RECIPES_FILE_PATH = "Data/recipes.json";

  public static void searchService() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введіть ID рецепта:");
    int searchId = ValidationInput.getValidIntInput(scanner);

    List<Recipes> recipes = JsonDataReader.modelDataJsonReader(RECIPES_FILE_PATH, Recipes[].class);

    for (Recipes recipe : recipes) {
      if (recipe.getRecipesId() == searchId) {
        System.out.println("ID рецепту: " + recipe.getRecipesId());
        System.out.println("Інгредієнти: " + recipe.getIngredient());
        System.out.println("Назва: " + recipe.getName());
        System.out.println("Категорія: " + recipe.getCategory());
        System.out.println("Інструкція: " + recipe.getInstruction());
        System.out.println("Час готування: " + recipe.getCookingTime());
        System.out.println("");
        break;
      }
    }
  }
}
