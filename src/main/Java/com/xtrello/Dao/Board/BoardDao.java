package com.xtrello.Dao.Board;

import com.xtrello.models.Board;
import com.xtrello.models.ListBoard;

import java.util.List;

public interface BoardDao {
    void createBoard(String name,long listboard_id, long user_id);
    void createBoardNotListBoard(String name,long user_id);
    List<Board> getBoardByListBoardId(long listboar_id);
    List<Board> getBoardByUserId(long user_id);
    void deleteBoard(long idboard);
    ListBoard getListBoardByBoard_ListBoardId(long id );
    Board getBoardByBoardId(long id);
}
