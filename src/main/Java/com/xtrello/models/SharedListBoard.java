package com.xtrello.models;

public class SharedListBoard {
    private long id;
    private long User_id;
    private long listBoard_id;

    public SharedListBoard() {
    }

    public SharedListBoard(long id, long user_id, long listBoard_id) {
        this.id = id;
        User_id = user_id;
        this.listBoard_id = listBoard_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return User_id;
    }

    public void setUser_id(long user_id) {
        User_id = user_id;
    }

    public long getListBoard_id() {
        return listBoard_id;
    }

    public void setListBoard_id(long listBoard_id) {
        this.listBoard_id = listBoard_id;
    }

    @Override
    public String toString() {
        return "SharedListBoard{" +
                "id=" + id +
                ", User_id=" + User_id +
                ", listBoard_id=" + listBoard_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SharedListBoard that = (SharedListBoard) o;

        if (id != that.id) return false;
        if (User_id != that.User_id) return false;
        return listBoard_id == that.listBoard_id;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (User_id ^ (User_id >>> 32));
        result = 31 * result + (int) (listBoard_id ^ (listBoard_id >>> 32));
        return result;
    }
}
