package com.services;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
    protected Connection connection = null;
    public MySqlConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quan_ly_lop_hoc","root","123456");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}