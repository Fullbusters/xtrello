package com.xtrello.dao.user;

import com.xtrello.models.User;

/**
 * Клас в якому описаны методи роботи з таблицею баз даних user
 */
public interface UserDao {
    /**
     * Метод який находить користувача за його email
     * @param email користувача (унікальне поле )
     * @return всі дані про користувача з бази даних
     */
    User findUserByEmail(String email);

    /**
     *
     * Метод дадає користувача до бази даних (реєстрація)
     * @param email email користувача
     * @param name ім'я користувача
     * @param password пароль користувача
     */
    void addUser(String email,String name, String password);
}
