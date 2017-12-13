package com.xtrello.dao.sharedBoard;

/**
 * Клас до таблиці в базі даних sharedboard користувачі яким дозволений доступ до дошки
 */
public interface SharedBoardDao {
    /**
     *  Метод надає користувачу доступ до дошки
     * @param User_id id користувача якому надаємо доступ
     * @param board_id Дошка над якою надаємо доступ
     */
    void addUserInSharedBoards(long User_id,long board_id);
}
