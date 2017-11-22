package com.xtrello.Dao.User;

import com.xtrello.models.User;

public interface UserDao {
    User findUserByEmail(String email);
    User addUser(String email,String name, String password);
}
