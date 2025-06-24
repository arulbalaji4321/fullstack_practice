package com.test;

import java.sql.*;

public class PurchaseDAO {

private static final String DB_URL = "jdbc:sqlite:purchase.db";

public PurchaseDAO(){
    try(Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement())  
    {
        String sql = "CREATE TABLE IF NOT EXISTS purchases (" 
         + "id INTEGER PRIMARY KEY AUTOINCREMENT," 
         + "product TEXT NOT NULL,"
         + "price REAL NOT NULL);";
         stmt.execute(sql);

    }
    catch(SQLException e){
        e.printStackTrace();
    }
}

public void insertPurchase(String product, double price){

    String sql = "INSERT INTO purchases (product, price) VALUES (?,?)";
    try (Connection conn = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)){
         
         pstmt.setString(1, product);
         pstmt.setDouble(2, price);
         pstmt.executeUpdate();
    }
    catch(SQLException e){
       e.printStackTrace();
    }
}

public double getTotalProfit(){

    double total = 0;
    String sql  = "SELECT SUM(price) FROM purchases";
    try(Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql)){
            if(res.next()){
                total = res.getDouble(1);
            }
        }
    catch(SQLException e){
       e.printStackTrace();
    }
     return total;
 }

}
