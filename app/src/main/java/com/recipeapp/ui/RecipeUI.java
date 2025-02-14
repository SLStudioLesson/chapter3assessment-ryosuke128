package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.recipeapp.datahandler.DataHandler;
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
         * 受け取ったデータを初めの「,」で区切りレシピ名と材料に分割
         * 上記を出力
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
                    ArrayList<Ingredients> ingredients = recipe.get(i).getIngetIngredients();
                    for (int j = 0; j < ingredients.size(); j++) {
                        String ingredient = "";
                        ingredient = ingredients.get(j).getName();
                    }
                    System.out.println("Recipe Name: " + name);
                    System.out.println("Main Ingredients: " + ingredient);
                    System.out.println("-----------------------------------");
                }
                for (String recipe : recipes) {
                }
            }else {
                System.out.println("No recipes available.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
