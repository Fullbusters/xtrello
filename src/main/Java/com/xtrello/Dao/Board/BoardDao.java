package com.xtrello.Dao.Board;

import com.xtrello.models.Board;

import java.util.List;

public interface BoardDao {
    Board createBoard(String name, long user_id);
    List<Board> printBoard(long user_id);
}
