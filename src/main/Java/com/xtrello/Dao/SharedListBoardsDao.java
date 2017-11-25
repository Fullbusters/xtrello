package com.xtrello.Dao;

import com.xtrello.models.ListBoard;

import java.util.List;

public interface SharedListBoardsDao {

    List<ListBoard> getListBoardsByUserId(long id);
    //List<Long> getIdListBoardByUserId(long id);
    void addUserInSharedListBoards(long User_id,long listboard_id);
}
