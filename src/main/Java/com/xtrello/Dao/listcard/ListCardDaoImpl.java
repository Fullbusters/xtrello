package com.xtrello.Dao.listcard;

import com.xtrello.Dao.DataSource;

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
        String str = "Select idListCard ,name, Board_id From listcards Where listcards.Board_id =\""+board_id+"\";";
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(str);
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
        String str= "INSERT INTO `listcards`(`name`, `Board_id`) VALUE (\""+name+"\",\""+board_id+"\"); ";

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate(str);

            System.out.println("Congratulation create listcard");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteListCardByListCard_Id(long idlistcard) {

        DataSource dataSource=new DataSource();
        String str= "DELETE FROM listcards WHERE idListCard=\""+idlistcard+"\"; ";

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

    @Override
    public void deleteListCardByBoard_Id(long board_id) {
        DataSource dataSource=new DataSource();
        String str= "DELETE FROM listcards WHERE Board_id=\""+board_id+"\"; ";

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
