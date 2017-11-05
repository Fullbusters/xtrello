package com.xtrello.models;

public class Card {
    /*
клас  відповідає за картку яка розташована  на дошці
*/
    private long id;
    private String name;
    private String comentar;
    /** foreign key on listCard table  */
    private long listCard_id;
    /** card creator */
    private long user_id;
    private String dateCreate;

    public Card() {
    }

    public Card(long id, String name, String comentar, long listCard_id, long user_id, String dateCreate) {
        this.id = id;
        this.name = name;
        this.comentar = comentar;
        this.listCard_id = listCard_id;
        this.user_id = user_id;
        this.dateCreate = dateCreate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComentar() {
        return comentar;
    }

    public void setComentar(String comentar) {
        this.comentar = comentar;
    }

    public long getListCard_id() {
        return listCard_id;
    }

    public void setListCard_id(long listCard_id) {
        this.listCard_id = listCard_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}
