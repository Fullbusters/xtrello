package com.xtrello.Dao.Card;

import com.xtrello.Dao.DataSource;
import com.xtrello.models.Card;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao{

    @Override
    public void deleteCard(long id) {
        DataSource dataSource=new DataSource();
        String str= "DELETE FROM cards WHERE idCard=\""+id+"\";";

        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate(str);

            System.out.println("Congratulation delete card");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Card> getCardByListCardId(long listcardid) {


        DataSource dataSource=new DataSource();
        List<Card> boards=  new ArrayList<>();
        String str = "Select idCard ,nameCard, Comentar,ListCard_id From cards Where cards.ListCard_id =\""+listcardid+"\";";
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery(str);
        ){


            while (rs.next()){
                Card card=new Card(
                        rs.getLong("idCard"),
                        rs.getString("nameCard"),
                        rs.getString("Comentar"),
                        rs.getLong("ListCard_id")

                );
                boards.add(card);

            }
            System.out.println("Congratulation print card");
            return boards;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }



    @Override
    public void createCard(String name, long idlistcard) {
        DataSource dataSource=new DataSource();
        String str= "INSERT INTO `cards`(`nameCard`, `ListCard_id`) VALUE (\""+name+"\",\""+idlistcard+"\"); ";

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



}
