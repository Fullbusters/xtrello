package com.xtrello.dao.listcard;

import com.xtrello.dao.DataSource;

import com.xtrello.models.ListCard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListCardDaoImpl  implements ListCardDao{
    @Override
    public List<ListCard> getListCardByBoardId(long board_id) {
        DataSource dataSource=new DataSource();
        List<ListCard> boards=  new ArrayList<>();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("Select idListCard ,name, Board_id From listcards Where listcards.Board_id =\""+board_id+"\";");
        ){


            while (rs.next()){
                ListCard listcard=new ListCard(
                        rs.getLong("idListCard"),
                        rs.getString("name"),
                        rs.getLong("Board_id")

                );
                boards.add(listcard);

            }
            System.out.println("Congratulation print listcard");
            return boards;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void createListCard(String name, long board_id) {
        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("INSERT INTO `listcards`(`name`, `Board_id`) VALUE (\""+name+"\",\""+board_id+"\"); ");

            System.out.println("Congratulation create listcard");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteListCardByListCard_Id(long idlistcard) {

        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("DELETE FROM listcards WHERE idListCard=\""+idlistcard+"\"; ");

            System.out.println("Congratulation delete listcard");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteListCardByBoard_Id(long board_id) {
        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("DELETE FROM listcards WHERE Board_id=\""+board_id+"\"; ");

            System.out.println("Congratulation delete listcard");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ListCard getListCardByListCardId(long idlistcard) {
        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("Select * From listcards Where listcards.idListCard =\""+idlistcard+"\";");
        ){
            if (rs.next()){
                ListCard listcard=new ListCard(
                        rs.getLong("idListCard"),
                        rs.getString("name"),
                        rs.getLong("Board_id")

                );
            return listcard;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
