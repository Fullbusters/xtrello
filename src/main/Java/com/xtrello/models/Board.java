package com.xtrello.models;

        /**
        клас відповідає за дошки
        */

public class Board {

    private long id;
    private String name;
    /** board creator */
    private long user_id;

            public Board(long id, String name, long user_id) {
                this.id = id;
                this.name = name;
                this.user_id = user_id;
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

            public long getUser_id() {
                return user_id;
            }

            public void setUser_id(long user_id) {
                this.user_id = user_id;
            }
        }
