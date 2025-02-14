//datahandlerフォルダ内にJSONDataHandlerクラスを作成
package com.recipeapp.datahandler;

import java.util.ArrayList;

import main.java.com.recipeapp.model.Recipe;

public class JSONDataHandler implements DataHandler {
    
        //メソッド
    public String getMode() {
        return "JSON";
    }
    public ArrayList<Recipe> readData() {
        return null;
    }
    public void writeData(Recipe recipe) {

    }
    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }
}
