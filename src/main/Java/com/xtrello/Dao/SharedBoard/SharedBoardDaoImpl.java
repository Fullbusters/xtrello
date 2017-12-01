package com.xtrello.Dao.SharedBoard;

import com.xtrello.Dao.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SharedBoardDaoImpl implements SharedBoardDao {
    @Override
    public void addUserInSharedBoards(long User_id, long board_id) {

        DataSource dataSource = new DataSource();
        String str = "INSERT INTO `sharedboard` (`User_id`,`Board_id`) VALUES (\"" + User_id + "\",\"" + board_id + "\");";

        try (
                Connection con = dataSource.createConnection();
                Statement stmt2 = con.createStatement();
        ) {
            stmt2.executeUpdate(str);
            System.out.println("Congratulation add shared board");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
