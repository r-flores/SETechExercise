package main;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionFloresDiary {
   static Connection connection = null;

   static void getDBConnection() {
      System.out.println("-------- MySQL JDBC Connection Testing ------------");
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("Where is your MySQL JDBC Driver?");
         e.printStackTrace();
         return;
      }
      System.out.println("MySQL JDBC Driver Registered!");

      connection = null;
      try {
         //UtilPropDBFloresDiary.loadProperty();
         connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
      } catch (Exception e) {
         System.out.println("Connection Failed! Check output console");
         e.printStackTrace();
         return;
      }

      if (connection != null) {
         System.out.println("You made it, take control your database now!");
      } else {
         System.out.println("Failed to make connection!");
      }
   }

   static String getURL() {
      String url = "jdbc:mysql://ec2-13-59-224-79.us-east-2.compute.amazonaws.com:3306/MyDBFloresTE";
      return url;
   }

   static String getUserName() {
      String usr = "rickyremote";
      return usr;
   }

   static String getPassword() {
      String pwd = "xenoblade7";
      return pwd;
   }
}
