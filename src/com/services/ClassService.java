package com.services;

import jdk.nashorn.internal.runtime.ECMAException;
import com.module.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassService extends MySqlConnection{
//    private void close(Connection myConn, PreparedStatement myStmt, ResultSet myRs) {
//        try {
//            if (myRs !=null) {
//                myRs.close();
//            }
//            if (myStmt !=null) {
//                myStmt.close();
//            }
//            if (myConn !=null) {
//                myConn.close();
//            }
//        }
//        catch (Exception exc) {
//            exc.printStackTrace();
//        }
//
//    }


    public ArrayList<Class> displayJTable() {
        ArrayList<Class> classes = new ArrayList<Class>();
        try{
            String sql = "select * from class;";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Class c = new Class();
                c.setId(resultSet.getInt(1));
                c.setName(resultSet.getString(2));
                c.setTeacher(resultSet.getString(3));
                classes.add(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return classes;
    }


    public int deleteClass(int id) {
        try{
            String sql = "delete from class where id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

        return -1;
    }

    public boolean checkUnique(int id){
        try{
            String sql = "select * from class where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public int updateClass(Class c) {
        try{
            String sql = "update class set name=?, teacher=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,c.getName());
            preparedStatement.setString(2,c.getTeacher());
            preparedStatement.setInt(3,c.getId());
            return preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public int addClass(Class c) {
        try{
            String sql = "insert into class(id,name,teacher) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,c.getId());
            preparedStatement.setString(2,c.getName());
            preparedStatement.setString(3,c.getTeacher());
            return preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

//    public int searchClass(String s) {
//        try {
//            if (s != null && s.trim().length() > 0) {
//                // create sql to search for class by name
//                String sql = "select * from class where  lower(class) like ?";
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                // set params
//                String theSearchNameLike = "%" + s.toLowerCase() + "%";
//                preparedStatement.setString(1,theSearchNameLike);
//
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return -1;
//    }
}
