package com.xtrello.Dao.Board;

import com.xtrello.models.Board;

public interface BoardDao {
    Board createBoard(String name, long user_id);
    Board printBoard(long user_id);
}
