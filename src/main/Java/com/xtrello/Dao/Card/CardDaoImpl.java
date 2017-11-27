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
}
