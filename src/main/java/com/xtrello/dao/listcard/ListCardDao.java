package com.xtrello.dao.listcard;

import com.xtrello.models.ListCard;

import java.util.List;

/**
 * Клас відповідає за таблицю бази даних listcards список карточок які знаходяться всередині дошок
 */
public interface ListCardDao {
    /**
     *  Метод повертає список карточок за даним id дошки
     * @param board_id id дошки
     * @return колекцію списків карточок
     */
    List<ListCard> getListCardByBoardId(long board_id);

    /**
     *  Метод створює список карточок
     * @param name назва списка
     * @param board_id id дошки в якому список створюється
     */
    void createListCard(String name,long board_id);

    /**
     *  Метод який видаляє список карточок за даним id списка
     * @param idlistcard id списка
     */
    void deleteListCardByListCard_Id(long idlistcard);

    /**
     *  Метод видаляє всі списки карточок які розташовані в дошці
     * @param board_id id дошки в якій видаляються списки
     */
    void deleteListCardByBoard_Id(long board_id);

    /**
     * Знаходить та повертає список карточок за його id
     * @param idlistcard id списку карточок
     * @return о'бєкт типу listcard з даними з тиблиці бази даних
     */
    ListCard getListCardByListCardId(long idlistcard);
}
