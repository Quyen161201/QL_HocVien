package com.quyen.qlhv.dao;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MY PC
 */
public class DBconnect {
    public static Connection getConnection(){
        Connection con = null;
        try {
         
          Class.forName("com.mysql.cj.jdbc.Driver");
          con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qlhv","root","Pantsirs1");
         
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void main(String[] args) throws SQLException {
        Connection con = getConnection();
        System.out.print(con.toString());
        con.close();
    }
}
