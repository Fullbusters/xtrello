package com.xtrello.Dao;

import com.xtrello.models.ListBoard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SharedListBoardDaoImpl implements SharedListBoardsDao {
    @Override
    public List<ListBoard> getListBoardsByUserId(long id) {


        DataSource dataSource = new DataSource();
        List<ListBoard> lstlistboard = new ArrayList<>();
        String str = "SELECT * FROM listboards WHERE listboards.User_id=\""+id+"\" OR listboards.idListBoards IN (Select listBoard_id From sharedlistboards Where sharedlistboards.User_id =\"" + id + "\");";

        try (
                Connection con = dataSource.createConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(str);
        ) {

            while (rs.next()) {
                ListBoard listBoard = new ListBoard(
                        rs.getLong("idListBoards"),
                        rs.getString("name"),
                        rs.getLong("User_id"),
                        rs.getString("text")

                );
                lstlistboard.add(listBoard);

            }

            return lstlistboard;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public void addUserInSharedListBoards(long User_id, long listboard_id) {
        DataSource dataSource = new DataSource();
        String str = "INSERT INTO `sharedlistboards` (`User_id`,`listBoard_id`) VALUES (\"" + User_id + "\",\"" + listboard_id + "\");";

        try (
                Connection con = dataSource.createConnection();
                Statement stmt2 = con.createStatement();
        ) {
            stmt2.executeUpdate(str);
            System.out.println("Congratulation add shared");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
