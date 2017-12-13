package com.xtrello.dao.sharedlistboards;

import com.xtrello.models.ListBoard;

import java.util.List;

/**
 * Клас відповідно до таблиці sharedlistboard  коричтувачі яким дозволено доступ до команди
 */
public interface SharedListBoardsDao {

    /**
     * Метод який повертає всі команди користувача за його id
     * @param id  користувача
     * @return всі команди які є в користувача
     */
    List<ListBoard> getListBoardsByUserId(long id);

    /**
     *  Метод який додає доступ користувачу до команди
     * @param User_id id користувача якому ми надаємо доступ
     * @param listboard_id id команди над якою ми цей доступ дозволяємо
     */
    void addUserInSharedListBoards(long User_id,long listboard_id);
}
