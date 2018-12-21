package net.cryptobrewery.rxdatabaseexample.Database;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class DBConnector {
    private static final String CLAZZ = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://10.0.2.2:3306/database";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="";
    private static Connection conn;g
     static void Connect(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            Class.forName(CLAZZ);
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
     }

     static Connection getConn(){
        if(conn == null){
            Connect();
            return conn;
        }
        return conn;
    }
}
