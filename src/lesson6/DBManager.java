package lesson6;

import java.sql.*;


public class DBManager {

/** Fields */
    private Connection connection;
    private Statement statement;

/** Constructor */
    public DBManager() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

/** Connection */
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:students.db");
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

    public void setAutoCommit(boolean isAutoCommit) {
        try {
            connection.setAutoCommit(isAutoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


/** Queries */
    public void addStudent(String name, int score) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO students (name, score) VALUES(?,?)");
            ps.setString(1,name);
            ps.setInt(2,score);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isExistStudent(String name, int score) {
        try {
            String strRequest = "SELECT name, score FROM students WHERE name = '" + name + "' AND score = '" + score + "'";
            ResultSet result =  statement.executeQuery(strRequest);
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateScore(String name, int newScore) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE students SET score = ? WHERE name = ?");
            ps.setInt(1,newScore);
            ps.setString(2,name);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getScore(String name) {
        try {
            String strRequest = "SELECT score FROM students WHERE name = '" + name + "'";
            ResultSet result =  statement.executeQuery(strRequest);
            while (result.next()) {
                return result.getInt("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }



}
