package com.xtrello.models;

public class Log {
    /*
клас відповідає за збереження активності
*/

    private long id;
    /** foreign key on board table  */
    private long board_id;
    /** foreign key on user table */
    private long user_id;
    /** foreign key on card table */
    private long card_id;
    private String userAction;
    private  int dateActive;

    public Log() {
    }

    public Log(long id, long board_id, long user_id, long card_id, String userAction) {
        this.id = id;
        this.board_id = board_id;
        this.user_id = user_id;
        this.card_id = card_id;
        this.userAction = userAction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(long board_id) {
        this.board_id = board_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getCard_id() {
        return card_id;
    }

    public void setCard_id(long card_id) {
        this.card_id = card_id;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }
}
