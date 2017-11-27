package com.xtrello.Dao.ListBoards;

import com.xtrello.Dao.DataSource;
import com.xtrello.Dao.SharedListBoardDaoImpl;
import com.xtrello.models.ListBoard;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListBoardDaoImpl implements ListBoardDao {
    @Override
    public void createListBoard(String name, long User_id,String text) {

        DataSource dataSource=new DataSource();
        SharedListBoardDaoImpl sharedListBoardDao=new SharedListBoardDaoImpl();
        String str= "INSERT INTO `listboards`(`name`,`User_id`, `text`) VALUE (\""+name+"\",\""+User_id+"\",\""+text+"\"); ";
        String str2="SELECT idListBoards FROM listboards WHERE name=\""+name+"\" AND User_id=\""+User_id+"\" AND text=\""+text+"\"";
        long id=0;
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate(str);
            ResultSet rs = stmt.executeQuery(str2);
            while(rs.next()){

                id=rs.getLong("idListBoards");
            }
            System.out.println(id);
            sharedListBoardDao.addUserInSharedListBoards(User_id,id);
            System.out.println("Congratulation");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




}
