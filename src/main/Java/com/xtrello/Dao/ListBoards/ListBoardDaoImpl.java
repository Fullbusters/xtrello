package com.xtrello.Dao.ListBoards;

import com.xtrello.Dao.DataSource;
import com.xtrello.models.ListBoard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListBoardDaoImpl implements ListBoardDao {
    @Override
    public ListBoard createListBoard(String name, long User_id) {

        DataSource dataSource=new DataSource();
        String str= "INSERT INTO `listboards`(`name`,`User_id`) VALUE (\""+name+"\",\""+User_id+"\"); ";
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate(str);
            System.out.println("Congratulation");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ListBoard printListBoard(long User_id) {

        DataSource dataSource=new DataSource();
        String str = "Select idListBoards ,name, User_id From listboards Where listboards.User_id =\""+User_id+"\";";

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){


            ResultSet rs = stmt.executeQuery(str);
            if(rs.next()){
                ListBoard listBoard=new ListBoard(
                        rs.getLong("idListBoards"),
                        rs.getString("name"),
                        rs.getLong("User_id")

                );
                return listBoard;
            }

            System.out.println("Congratulation  print listboard");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }


}
