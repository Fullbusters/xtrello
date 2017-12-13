package com.xtrello.dao.listBoards;

import com.xtrello.dao.DataSource;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

public class ListBoardDaoImpl implements ListBoardDao {
    @Override
    public void createListBoard(String name, long User_id,String text) {

        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("INSERT INTO `listboards`(`name`,`User_id`, `text`) VALUE (\""+name+"\",\""+User_id+"\",\""+text+"\"); ");

            System.out.println("Congratulation");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteListBoard(long idlistcard) {
        DataSource dataSource=new DataSource();

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("DELETE FROM listboards WHERE idListBoards=\""+idlistcard+"\";");

            System.out.println("Congratulation delete listcard");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
