package com.xtrello.Dao;

import com.xtrello.models.User;

public interface UserDao {
    User findUserByEmail(String email);
}
