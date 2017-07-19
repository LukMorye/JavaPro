package lesson2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by valentintitov on 19.07.17.
 */
public class DBManager {

    Connection connection;
    Statement statement;

    public DBManager() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:products_db.db");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
            System.out.println("\n---------\nОтключён от БД");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProductsTable() {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS Products (\n" +
                    " id    INTEGER PRIMARY KEY,\n" +
                    " title  TEXT,\n" +
                    " cost INTEGER\n" +
                    " );");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateProductsContent() {
        try {
            statement.execute("DELETE FROM Products");
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Products (title, cost) VALUES(?,?)");
            for (int i = 0; i < 10000; i++) {
                ps.setString(1,"Товар " + (i+1));
                ps.setInt(2,(i+1)*10);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCost(String name) {
        try {
            ResultSet result =  statement.executeQuery("SELECT cost FROM Products WHERE title = '" + name + "'");
            while (result.next()) {
                return result.getInt("cost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean changeCost(int newCost, String title) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Products SET cost = ? WHERE title = ?");
            ps.setInt(1,newCost);
            ps.setString(2,title);
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getProductsFromRange(int from, int to) {
        try {
            ArrayList<String> list = new ArrayList<>();

            ResultSet result =  statement.executeQuery("SELECT title FROM Products WHERE cost >= " + from + " AND cost <= " + to);
            if (!result.next()) { return null; }
            while (result.next()) {
                 list.add(result.getString("title"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
