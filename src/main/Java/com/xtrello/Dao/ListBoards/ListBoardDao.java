package com.xtrello.Dao.ListBoards;

import com.xtrello.models.ListBoard;

public interface ListBoardDao {
    ListBoard createListBoard(String name, long User_id);
    ListBoard printListBoard(long User_id);
}
