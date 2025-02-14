//datahandlerフォルダ内にCSVDataHandlerクラスを作成
package com.recipeapp.datahandler;

import java.util.ArrayList;
import java.io.*;

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
                String ingredients = recipe[1];
                Recipe r = new Recipe(name, ingredients);
                recipes.add(r);
        }
        return recipes;
    }
    public void writeData(Recipe recipe) {

    }
    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }
}
