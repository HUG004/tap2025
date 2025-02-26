package com.example.tap2025.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    private static String DB = "restaurantec";
    private static String USER = "admin2";
    private static String PWD = "123";
    private static String HOST = "localhost"; //127.0.0.1 (loopback)
    private static String PORT = "3306";
    public static Connection connection;

    public static void createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB, USER,PWD); //socket: mecanismo de comunicacion entre java y msql
            System.out.println("Conexion establecida (:");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
