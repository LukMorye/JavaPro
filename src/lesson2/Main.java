package lesson2;

import java.util.ArrayList;

/**
 * Created by valentintitov on 20.07.17.
 */
public class Main {

    private static final String testTitle = "Товар 5";

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        dbManager.connect();
        dbManager.createProductsTable();
        dbManager.generateProductsContent();
        dbManager.changeCost(125,testTitle);
        int cost = dbManager.getCost(testTitle);
        if (cost == -1) {
            System.out.println("Такого товара нет");
        } else {
            System.out.println("Цена товара 5: " + cost);
        }
        int from = 30;
        int to = 100;
        ArrayList<String> products = dbManager.getProductsFromRange(from,to);
        if (products == null) {
            System.out.println("Товара в этом ценовом диапазоне не найдено");
        } else {
            System.out.println("Найден товар в ценовом диапазоне от " + from + " до " + to);
            int size = products.size();
            for (int i = 0; i < size; i++) {
                System.out.println(products.get(i));
            }
        }
        dbManager.disconnect();
    }
}
