package com.ndergunov.dvdexchange.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "dvd_exchange")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Integer userID;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private byte[] password;

    public User(){
        super();
    }

    public User(Integer userID, String login, byte[] password) {
        this.userID = userID;
        this.login = login;
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
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

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID) &&
                Objects.equals(login, user.login) &&
                Arrays.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userID, login);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }
}
