package com.xtrello.Dao.Board;

import com.xtrello.Dao.DataSource;
import com.xtrello.models.Board;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {
    @Override
    public Board createBoard(String name,long listboard_id, long User_id) {

        DataSource dataSource=new DataSource();
        String str = "INSERT INTO `boards`(`name`,`ListBoard_id`,`creator_id`) VALUE (\""+name+"\",\""+listboard_id+"\",\""+User_id+"\"); ";
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
    public List<Board> getBoardByListBoardId(long ListBoard_id) {
        DataSource dataSource=new DataSource();
        List<Board> boards=  new ArrayList<>();
        String str = "Select id ,name, ListBoard_id From boards Where boards.ListBoard_id =\""+ListBoard_id+"\";";

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(str);
        ){


            while (rs.next()){
                Board board=new Board(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getLong("ListBoard_id")

                );
                boards.add(board);

            }
            System.out.println("Congratulation print board");
            return boards;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
