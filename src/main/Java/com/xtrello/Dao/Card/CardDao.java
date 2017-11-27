package com.xtrello.Dao.Card;

import com.xtrello.models.Card;

import java.util.List;

public interface CardDao {
    List<Card> getCardByListCardId(long listcardid);
}
