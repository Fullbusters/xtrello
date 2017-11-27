package com.xtrello.Dao.Board;

import com.xtrello.models.Board;

import java.util.List;

public interface BoardDao {
    Board createBoard(String name,long listboard_id, long user_id);
    List<Board> getBoardByListBoardId(long user_id);
}
