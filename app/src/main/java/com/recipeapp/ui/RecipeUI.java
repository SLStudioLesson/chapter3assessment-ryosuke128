package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                    displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        /*
         * フィールドのDataHandlerクラスからreadData()メソッドを呼び出し
         * ArrayList<Recipe>からname、ArrayList<Ingredient>を取り出し
         * ArrayList<Ingredient>からIngredientオブジェクトを取り出し
         * 取り出したIngredientオブジェクトのnameを取り出し「,」で結合
         * 上記Recipe.name、Ingredient.nameを出力
         * readData()メソッドから何も帰ってこない場合はメッセージ出力
         * readData()メソッドから例外を受け取った場合はメッセージを出力
         * 
         */
        
        try {
            ArrayList<Recipe> recipes = this.dataHandler.readData();
            if (!recipes.isEmpty()) {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                for (int i = 0; i < recipes.size(); i++) {
                    String name = recipes.get(i).getName();
                    ArrayList<Ingredient> ingredients = recipes.get(i).getIngredients();
                    String ingredient = "";
                    for (int j = 0; j < ingredients.size(); j++) {
                        if (j < ingredients.size() - 1) {
                            ingredient = ingredient + ingredients.get(j).getName()+ ",";
                        } else if (j == ingredients.size() - 1) {
                            ingredient += ingredients.get(j).getName();
                        }
                    }
                    System.out.println("Recipe Name: " + name);
                    System.out.println("Main Ingredients: " + ingredient);
                    System.out.println("-----------------------------------");
                }
                
            }else {
                System.out.println("No recipes available.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void addNewRecipe() {
        /*
         * ユーザーに入力を要求
         * Ingredientとして入力されたものは配列に格納
         * doneが入力されるまで入力を繰り返す
         * 上記情報を引数にRecipeクラスをインスタンス化
         * インスタンス化したものを引数にwriteDataを呼び出し
         */

        try {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = this.reader.readLine();
            System.out.println("Enter ingredients (type 'done' when finished):");
            while (true) {
                System.out.print("Ingredient: ");
                String ingredientName = this.reader.readLine();
                if (ingredientName.equals("done")) {
                    break;
                }
    
                Ingredient ingredient = new Ingredient(ingredientName);
                ingredients.add(ingredient);
            }
    
            Recipe recipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(recipe);
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }

        System.out.println("Recipe added successfully.");


    }
}
