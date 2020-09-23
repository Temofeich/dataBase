package dataBase;
import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
    String userName = "artem";
    String password = "artem";
    String connectionURL = "jdbc:mysql://localhost:3306/mysql";
    Class.forName("com.mysql.cj.jdbc.Driver");

    try(Connection connection = DriverManager.getConnection(connectionURL,userName,password)){
        System.out.println("соединение прошло успешно!");
    }
    }
}
