//datahandlerフォルダ内にCSVDataHandlerクラスを作成
package com.recipeapp.datahandler;

import java.util.ArrayList;
import java.io.*;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    //コンストラクタ
    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }
    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    //メソッド
    public String getMode() {
        return "CSV";
    }
    public ArrayList<Recipe> readData() throws IOException {
        /*
         * recipes.csvから1行ずつデータを読み込みArrayListに格納
         * レシピ名、材料に分割
         * Recipeクラスを上記情報を引数にインスタンス化
         * RecipeクラスをArrayListに格納
         * 例外がスローされたら呼び出しもとへスロー
         */
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String line;
        ArrayList<Recipe> recipes =new ArrayList<>();
        while ((line = reader.readLine()) != null) {
                String[] recipe =  line.split(",", 2);
                String name = recipe[0];
                String[] ingredients = recipe[1].split(",");
                ArrayList<Ingredient> ingredient = new ArrayList<>();
                for (int i = 0; i < ingredients.length; i++) {
                    Ingredient in = new Ingredient();
                    in.setName(ingredients[i]);
                    ingredient.add(in);
                }
                Recipe r = new Recipe(name, ingredient);
                recipes.add(r);
        }
        return recipes;
    }
    public void writeData(Recipe recipe) throws IOException {
        /*
         * 引数で受け取ったRecipeオブジェクトからname、ingredientsを取り出し
         * ingredients配列からnameを受け取り「,」で結合しファイルへ書き込み
         * 例外が発生したら呼び出しもとにスロー
         */

        String name = recipe.getName();
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        String ingredient = "";
        for (int i = 0; i < ingredients.size(); i++) {
            if (i < ingredients.size() - 1) {
                ingredient = ingredient + ingredients.get(i).getName()+ ",";
            } else if (i == ingredients.size() - 1) {
                ingredient += ingredients.get(i).getName();
            }
            
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, true));
        writer.write(name + "," + ingredient);
        writer.newLine();
        writer.close();


        
    }
    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }
}
