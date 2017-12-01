package com.xtrello.Dao.ListBoards;



public interface ListBoardDao {
   void  createListBoard(String name, long User_id,String text);
   void deleteListBoard(long idlistcard);

}
