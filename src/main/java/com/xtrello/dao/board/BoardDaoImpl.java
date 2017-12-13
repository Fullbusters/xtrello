package com.xtrello.dao.board;

import com.xtrello.dao.DataSource;
import com.xtrello.models.Board;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BoardDaoImpl implements BoardDao {
    private static Logger log = Logger.getLogger(BoardDaoImpl.class.getName());
    @Override
    public void createBoard(String name,long listboard_id, long User_id) {

        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement()
        ){

            stmt.executeUpdate("INSERT INTO `boards`(`name`,`ListBoard_id`,`creator_id`) VALUE (\""+name+"\",\""+listboard_id+"\",\""+User_id+"\"); ");
            log.info("create board"+name);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<Board> getBoardByListBoardId(long ListBoard_id) {
        DataSource dataSource=new DataSource();
        List<Board> boards=  new ArrayList<>();

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("Select * From boards Where boards.ListBoard_id =\""+ListBoard_id+"\";");
        ){


            while (rs.next()){
                Board board=new Board(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getLong("ListBoard_id"),
                    rs.getLong("creator_id")

                );
                boards.add(board);

            }
           log.info("getBoardByListBoardId  fine"+ListBoard_id);
            return boards;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Board> getBoardByUserId(long user_id) {
        DataSource dataSource=new DataSource();
        List<Board> boards=  new ArrayList<>();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM boards WHERE boards.ListBoard_id=\"1\" AND (boards.creator_id=\""+user_id+"\" OR boards.id IN (Select Board_id From sharedboard Where sharedboard.User_id =\"" + user_id +"\"));");
        ){

            while (rs.next()){
                Board board=new Board(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("ListBoard_id"),
                        rs.getLong("creator_id")

                );
                boards.add(board);

            }
            log.info("getBoardByUserId fine userid="+user_id);
            return boards;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteBoard(long idboard) {

        DataSource dataSource=new DataSource();


        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement()
        ){

            stmt.executeUpdate("DELETE FROM boards WHERE id=\""+idboard+"\";");

            log.info("delete board "+idboard);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Board getBoardByBoardId(long id) {

        DataSource dataSource=new DataSource();


        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("Select * From boards Where boards.id =\""+id+"\";")
        ){

            if (rs.next()){
                Board board=new Board(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("ListBoard_id"),
                        rs.getLong("creator_id")

                );
                return board;
            }
           log.info("getBoardByBoardId"+id);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
