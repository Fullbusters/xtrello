package com.xtrello.Dao.Card;

import com.xtrello.models.Card;

import java.util.List;

public interface CardDao {

    void deleteCardByCardId(long id);
    void createCard(String name,long idlistcard);
    List<Card> getCardByListCardId(long listcardid);
    void deleteCardByListCardId(long idlistcard);
    void updateComentar(String comentar,long idcard);
}
