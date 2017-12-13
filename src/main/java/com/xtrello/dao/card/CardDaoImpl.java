package com.xtrello.dao.card;

import com.xtrello.dao.DataSource;
import com.xtrello.models.Card;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao{

    @Override
    public void deleteCardByCardId(long id) {
        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("DELETE FROM cards WHERE idCard=\""+id+"\";");

            System.out.println("Congratulation delete card");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Card> getCardByListCardId(long listcardid) {


        DataSource dataSource=new DataSource();
        List<Card> boards=  new ArrayList<>();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("Select idCard ,nameCard, Comentar,ListCard_id From cards Where cards.ListCard_id =\""+listcardid+"\";");
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
    public void deleteCardByListCardId(long idlistcard) {
        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("DELETE FROM cards WHERE ListCard_id=\""+idlistcard+"\";");

            System.out.println("Congratulation delete card");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateComentar(String comentar, long idcard) {
        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("UPDATE cards SET Comentar =\""+comentar+"\" WHERE idCard="+idcard+" ;   ");

            System.out.println("Congratulation create ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Card getCardByCardId(long id) {
        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("Select * From cards Where cards.idCard =\""+id+"\";");
        ){

            if (rs.next()){
                Card card=new Card(
                        rs.getLong("idCard"),
                        rs.getString("nameCard"),
                        rs.getString("Comentar"),
                        rs.getLong("ListCard_id")

                );

                return card;
            }
            System.out.println("Congratulation get card by cardid");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createCard(String name, long idlistcard) {
        DataSource dataSource=new DataSource();
        try (
                Connection con =dataSource.createConnection();
                Statement stmt=con.createStatement();
        ){

            stmt.executeUpdate("INSERT INTO `cards`(`nameCard`,`Comentar`, `ListCard_id`) VALUE (\""+name+"\",\"there should be the contents of the card \",\""+idlistcard+"\"); ");

            System.out.println("Congratulation create listcard");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
