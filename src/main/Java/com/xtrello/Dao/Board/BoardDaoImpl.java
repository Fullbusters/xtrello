package com.xtrello.Dao.Board;

import com.xtrello.Dao.DataSource;
import com.xtrello.models.Board;
import com.xtrello.models.ListBoard;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {
    @Override
    public void createBoard(String name,long listboard_id, long User_id) {

        DataSource dataSource=new DataSource();
        String str = "INSERT INTO `boards`(`name`,`ListBoard_id`,`creator_id`) VALUE (\""+name+"\",\""+listboard_id+"\",\""+User_id+"\"); ";
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){
            System.out.println(name);
            stmt.executeUpdate(str);
            System.out.println("Congratulation");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createBoardNotListBoard(String name, long user_id) {
        DataSource dataSource=new DataSource();
       // String str = ;
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("INSERT INTO `boards`(`name`,`ListBoard_id`,`creator_id`) VALUE (\""+name+"\",\"1\",\""+user_id+"\"); ");
            System.out.println(name);
            System.out.println("Congratulation create board not listboardId");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Board> getBoardByListBoardId(long ListBoard_id) {
        DataSource dataSource=new DataSource();
        List<Board> boards=  new ArrayList<>();
        String str = "Select * From boards Where boards.ListBoard_id =\""+ListBoard_id+"\";";

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(str);
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
            System.out.println("Congratulation print board");
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
        String str2="SELECT * FROM boards WHERE boards.ListBoard_id=\"1\" AND (boards.creator_id=\""+user_id+"\" OR boards.id IN (Select Board_id From sharedboard Where sharedboard.User_id =\"" + user_id +"\"));";

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(str2);
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
            System.out.println("Congratulation get board");
            return boards;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteBoard(long idboard) {

        DataSource dataSource=new DataSource();
        String str= "DELETE FROM boards WHERE id=\""+idboard+"\";";

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate(str);

            System.out.println("Congratulation delete board");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ListBoard getListBoardByBoard_ListBoardId(long id) {
        DataSource dataSource=new DataSource();
        String str = "Select * From listboards Where listboards.idListBoards =\""+id+"\";";


        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(str);
        ){

            if (rs.next()){
                ListBoard listBoard=new ListBoard(
                        rs.getLong("idListBoards"),
                        rs.getString("name"),
                        rs.getLong("User_id"),
                        rs.getString("text")

                );
                return listBoard;
            }
            System.out.println("Congratulation add shared board");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Board getBoardByBoardId(long id) {

        DataSource dataSource=new DataSource();
        String str = "Select * From boards Where boards.id =\""+id+"\";";


        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(str);
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
            System.out.println("Congratulation add shared board");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
