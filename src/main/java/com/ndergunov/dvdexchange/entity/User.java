package com.ndergunov.dvdexchange.entity;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "dvd_exchange")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userID")
    private int userID;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private byte[] password;

    public User(){
        super();
    }

    public User(int userID, String login, byte[] password) {
        this.userID = userID;
        this.login = login;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }
}
