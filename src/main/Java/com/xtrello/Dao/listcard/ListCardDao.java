package com.xtrello.Dao.listcard;

import com.xtrello.models.ListCard;

import java.util.List;

public interface ListCardDao {

    List<ListCard> getListCardByBoardId(long board_id);
    void createListCard(String name,long board_id);
    void deleteListCardByListCard_Id(long idlistcard);
    void deleteListCardByBoard_Id(long board_id);
}
