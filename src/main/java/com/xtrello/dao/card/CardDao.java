package com.xtrello.dao.card;

import com.xtrello.models.Card;

import java.util.List;

/**
 * Клас відповідає таблиці в базі даних cards (карточки які знаходяться в списку )
 */
public interface CardDao {
    /**
     * Видалення карточки за її id
     * @param id карточки
     */
    void deleteCardByCardId(long id);

    /**
     *  Створення карточки
     * @param name назва карточки
     * @param idlistcard id списку в якому карточка створюється
     */
    void createCard(String name,long idlistcard);

    /**
     *  Метод повертає колекцію карточок за даним id списка карточок
     * @param listcardid id списка карточок
     * @return колекцію карточок
     */
    List<Card> getCardByListCardId(long listcardid);

    /**
     * Видалення всіх карточок які знаходяться в списку
     * @param idlistcard id списку який видаляємо
     */
    void deleteCardByListCardId(long idlistcard);

    /**
     *  Метод який добавляє коментар до карточки
     * @param comentar коментар який ми хочемо добавити до карточки
     * @param idcard id карточки до якої ми цей коментар добавляємо
     */
    void updateComentar(String comentar,long idcard);

    /**
     *  метод повертає дані з бази даних про карточку за заданим id
     * @param id карточки яку ми хочем отримати
     * @return карточка яка выдповідає id
     */
    Card getCardByCardId(long id);
}
