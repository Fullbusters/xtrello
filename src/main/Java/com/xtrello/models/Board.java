package com.xtrello.models;

        /**
        клас відповідає за дошки
        */

public class Board {

    private long id;
    private String name;
    /** board creator */
    private long ListBoard_id;

            public Board(long id, String name, long user_id) {
                this.id = id;
                this.name = name;
                this.ListBoard_id = user_id;
            }

            public Board() {
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

            public long getListBoard_id() {
                return ListBoard_id;
            }

            public void setListBoard_id(long listBoard_id) {
                this.ListBoard_id = listBoard_id;
            }

            @Override
            public String toString() {
                return "Board{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", ListBoard_id=" + ListBoard_id +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Board board = (Board) o;

                if (id != board.id) return false;
                if (ListBoard_id != board.ListBoard_id) return false;
                return name != null ? name.equals(board.name) : board.name == null;
            }

            @Override
            public int hashCode() {
                int result = (int) (id ^ (id >>> 32));
                result = 31 * result + (name != null ? name.hashCode() : 0);
                result = 31 * result + (int) (ListBoard_id ^ (ListBoard_id >>> 32));
                return result;
            }
        }
