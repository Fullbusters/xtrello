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

    public Card() {
    }

    public Card(long id, String name, String comentar, long listCard_id) {
        this.id = id;
        this.name = name;
        this.comentar = comentar;
        this.listCard_id = listCard_id;
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

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comentar='" + comentar + '\'' +
                ", listCard_id=" + listCard_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (id != card.id) return false;
        if (listCard_id != card.listCard_id) return false;
        if (name != null ? !name.equals(card.name) : card.name != null) return false;
        return comentar != null ? comentar.equals(card.comentar) : card.comentar == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comentar != null ? comentar.hashCode() : 0);
        result = 31 * result + (int) (listCard_id ^ (listCard_id >>> 32));
        return result;
    }
}
