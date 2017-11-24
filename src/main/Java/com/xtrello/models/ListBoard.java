package com.xtrello.models;

/*
клас відповідає списку дошок користувача
*/

public class ListBoard {
    private long id;
    private String name;
    /** team creator */
    private long user_id;

    private String text;

    public ListBoard() {
    }

    public ListBoard(long id, String name, long user_id, String text) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.text = text;
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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ListBoard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_id=" + user_id +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListBoard listBoard = (ListBoard) o;

        if (id != listBoard.id) return false;
        if (user_id != listBoard.user_id) return false;
        if (name != null ? !name.equals(listBoard.name) : listBoard.name != null) return false;
        return text != null ? text.equals(listBoard.text) : listBoard.text == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (user_id ^ (user_id >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
