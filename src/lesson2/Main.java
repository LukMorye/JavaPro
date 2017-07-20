package lesson2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by valentintitov on 20.07.17.
 */
public class Main {

    private static DBManager dbManager;
    private static final String COST = "/цена";
    private static final String CHANGE_COST = "/сменитьцену";
    private static final String GET_RANGE = "/товарыпоцене";
    private static final String EXIT = "/выход";
    private static final String DELIMETER = " ";

    public static void main(String[] args) {
        dbManager = new DBManager();
        dbManager.connect();
        dbManager.createProductsTable();
        dbManager.generateProductsContent();
        waitingCommands();
    }

    private static void waitingCommands() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nВведите команду > ");
            String command = scanner.nextLine();
            String[] cmdComponents = command.split(DELIMETER);
            switch (cmdComponents[0]) {
                case EXIT:
                    dbManager.disconnect();
                    System.out.println("Bye!");
                    return;
                case COST:
                    getCost(cmdComponents[1]);
                    break;
                case CHANGE_COST:
                    changeCost(cmdComponents[1],Integer.parseInt(cmdComponents[2]));
                    getCost(cmdComponents[1]);
                    break;
                case GET_RANGE:
                    showFromRange(Integer.parseInt(cmdComponents[1]),Integer.parseInt(cmdComponents[2]));
                    break;
                default:
                    System.out.println("Неизвестная команда, повторите попытку");
                    break;
            }
        }
    }

    private static void getCost(String title) {
        int cost = dbManager.getCost(title);
        if (cost == -1) {
            System.out.println("Такого товара нет");
        } else {
            System.out.println("Цена для " + title + ": " + cost);
        }
    }

    private static void changeCost(String title, int newCost) {
        dbManager.changeCost(newCost,title);
    }

    private static void showFromRange(int from, int to) {
        ArrayList<String> products = dbManager.getProductsFromRange(from,to);
        if (products == null) {
            System.out.println("Товара в этом ценовом диапазоне не найдено");
        } else {
            System.out.println("Товары в ценовом диапазоне от " + from + " до " + to);
            int size = products.size();
            for (int i = 0; i < size; i++) {
                System.out.println(products.get(i));
            }
        }
    }

}
