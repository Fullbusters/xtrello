package com.xtrello.Dao.listcard;

import com.xtrello.models.ListCard;

import java.util.List;

public interface ListCardDao {

    List<ListCard> getListCardByBoardId(long board_id);
}
