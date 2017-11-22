package com.xtrello.Dao.User;

import com.xtrello.Dao.DataSource;
import com.xtrello.Dao.User.UserDao;
import com.xtrello.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByEmail(String email) {
        DataSource dataSource = new DataSource();
        try (Connection con = dataSource.createConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE users.email=\"" + email + "\";");) {
            if(rs.next()){
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getInt("role")
                );
                return user;
            }

        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User addUser(String email, String password, String name) {
        DataSource dataSource=new DataSource();
        String str = "INSERT INTO `users`  (`email`, `password`, `name`, `date`, `role`) VALUES (\""+email+"\", \""+password+"\", \""+name+"\", \"2017-11-15\", 2 );";
        try (
                Connection con =dataSource.createConnection();
             Statement stmt2=con.createStatement();
             ){

             stmt2.executeUpdate(str);
        System.out.println("Congratulation");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    public User findUserById(String id) {
        DataSource dataSource = new DataSource();
        try (Connection con = dataSource.createConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM boards WHERE boards.User_id=\"" + id + "\";");) {
            if(rs.next()){
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getInt("role")
                );
                return user;
            }

        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
