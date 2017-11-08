package com.xtrello.models;

    /**
    *клас відповідає за користувачів яким дозволений доступ до дошки
    */

public class AddedUser {
        /**
         * дані повинні бути в дазі даних
         *
         *
         *
         */

        private long id;
        /** foreign key on board table  */
        private long board_id;
        /** foreign key on user table */
        private long user_id;

        public AddedUser(long id, long board_id, long user_id) {
            this.id = id;
            this.board_id = board_id;
            this.user_id = user_id;
        }

        public AddedUser() {
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

    }
