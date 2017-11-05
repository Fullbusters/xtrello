package com.xtrello.models;
/**
 клас відповідає за користувачів програми
 */

public class User {

    private long id;
    /** email унікальне */
    private String username;
    private String password;
    private String name;
    private Boolean policyPrivacy;
    /** 1 =admin, 2 = user, 3 = banned user */
    private int role;

    public User(long id, String username, String password, String name, Boolean policyPrivacy, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.policyPrivacy = policyPrivacy;
        this.role = role;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPolicyPrivacy() {
        return policyPrivacy;
    }

    public void setPolicyPrivacy(Boolean policyPrivacy) {
        this.policyPrivacy = policyPrivacy;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
