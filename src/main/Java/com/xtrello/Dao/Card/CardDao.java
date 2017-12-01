package com.xtrello.Dao.Card;

import com.xtrello.models.Card;

import java.util.List;

public interface CardDao {

    void deleteCard(long id);
    void createCard(String name,long idlistcard);
    List<Card> getCardByListCardId(long listcardid);
}
