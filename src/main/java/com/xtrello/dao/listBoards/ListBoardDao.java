package com.xtrello.dao.listBoards;


/**
 * Клас відповідає за таблицю listboards в базі даних (команди )
 */
public interface ListBoardDao {
    /**
     *  Створення команди
     * @param name назва команди
     * @param User_id користувач який створив команду
     * @param text опис команди
     */
   void  createListBoard(String name, long User_id,String text);

    /**
     *  Видалення команди
     * @param idlistcard id команди яка буде видалена
     */
   void deleteListBoard(long idlistcard);

}
