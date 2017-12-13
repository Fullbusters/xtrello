package com.xtrello.models;

    /**
    *клас відповідає за списки карток на дошці
    */

public class ListCard {
        private long id;
        private String name;
        /** listcard creator */
        private long board_id;

        public ListCard() {
        }

        public ListCard(long id, String name, long board_id) {
            this.id = id;
            this.name = name;
            this.board_id = board_id;
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

        public long getBoard_id() {
            return board_id;
        }

        public void setBoard_id(long board_id) {
            this.board_id = board_id;
        }

        @Override
        public String toString() {
            return "ListCard{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", board_id=" + board_id +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ListCard listCard = (ListCard) o;

            if (id != listCard.id) return false;
            if (board_id != listCard.board_id) return false;
            return name != null ? name.equals(listCard.name) : listCard.name == null;
        }

        @Override
        public int hashCode() {
            int result = (int) (id ^ (id >>> 32));
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (int) (board_id ^ (board_id >>> 32));
            return result;
        }
    }
