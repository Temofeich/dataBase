package dataBase;

import java.sql.*;

public class Main
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        String userName = "artem";
        String password = "artem";
        String connectionURL = "jdbc:mysql://localhost:3306/test" +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = connection.createStatement())
        {
//        statement.execute("create table test.developers (first_name VARCHAR(10), second_name VARCHAR(10));");
//               statement.executeUpdate("insert into customers (cust_name) values ('Jim');");
//            statement.execute("delete from customers where cust_name = 'Jim'");
//        ResultSet resultSet = statement.executeQuery("select * from customers");
//        while (resultSet.next()){
//            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));

            ResultSet resultSet1 = statement.executeQuery
                    ("select cust_contact from customers where cust_contact like  ('J%') order by cust_contact;");
            while (resultSet1.next())
            {
                System.out.println(resultSet1.getString(1));
            }

            ResultSet resultSet2 = statement.executeQuery
                    ("select count(*) as num_items, " +
                            "min(prod_price) as price_min," +
                            "max(prod_price) as price_max," +
                            "avg(prod_price) as price_avg from Products;");
            while (resultSet2.next())
            {
                System.out.println(resultSet2.getString(1)
                        + "; " + resultSet2.getString(2)
                        + "; " + resultSet2.getString(3)
                        + "; " + resultSet2.getString(4));
            }

            ResultSet resultSet3 = statement.executeQuery
                    ("select vend_id, count(*) as num_prods from products group by vend_id; ");
            while (resultSet3.next())
            {
                System.out.println(resultSet3.getString(1)
                        + " - " + resultSet3.getString(2));
            }

            ResultSet resultSet4 = statement.executeQuery
                    ("select cust_name, cust_contact from customers where cust_id in " +
                            "( select cust_id from orders where order_num in " +
                            "( select order_num from orderitems where prod_id = 'RGAN01'))");
            while (resultSet4.next())
            {
                System.out.printf("%-15s%-15s%n",resultSet4.getString(1),resultSet4.getString(2));
            }


        }
    }
}