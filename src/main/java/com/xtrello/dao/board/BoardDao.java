package com.xtrello.dao.board;

import com.xtrello.models.Board;

import java.util.List;

/**
 *  клас выдповыдає таблиці бази даних
 */
public interface BoardDao {
    /**
     *  Створення дошки
     * @param name назва дошки яку створюємо
     * @param listboard_id id команди в якій ми створюємо (1-приватна )
     * @param user_id id користувача який створив цю дошку
     */
    void createBoard(String name,long listboard_id, long user_id);

    /**
     *  Метод який повертає всі дошки які є в команді
     * @param listboard_id id команди в якій знаходяться дошки
     * @return  колекцію дошок які знаходяться в команді
     */
    List<Board> getBoardByListBoardId(long listboard_id);

    /**
     *  Метод який повертає всі приватні дошки які є в користувача
     * @param user_id id користувача
     * @return колекцію дошок
     */
    List<Board> getBoardByUserId(long user_id);

    /**
     *  Видалення дошки
     * @param idboard id дошки яку ми видаляємо
     */
    void deleteBoard(long idboard);

    /**
     *  Метод знаходить дошку за її id і повертає її
     * @param id id дошки
     * @return дошку за даним id
     */
    Board getBoardByBoardId(long id);
}
