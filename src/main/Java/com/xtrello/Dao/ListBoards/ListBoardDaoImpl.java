package com.xtrello.Dao.ListBoards;

import com.xtrello.Dao.DataSource;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

public class ListBoardDaoImpl implements ListBoardDao {
    @Override
    public void createListBoard(String name, long User_id,String text) {

        DataSource dataSource=new DataSource();
        String str= "INSERT INTO `listboards`(`name`,`User_id`, `text`) VALUE (\""+name+"\",\""+User_id+"\",\""+text+"\"); ";

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate(str);

            System.out.println("Congratulation");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteListBoard(long idlistcard) {
        DataSource dataSource=new DataSource();
        String str= "DELETE FROM listboards WHERE idListBoards=\""+idlistcard+"\";";

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate(str);

            System.out.println("Congratulation delete listcard");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
