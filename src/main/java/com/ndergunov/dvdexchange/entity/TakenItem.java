package com.ndergunov.dvdexchange.entity;

import javax.persistence.*;

@Entity
@Table(name = "takenitem", schema = "dvd_exchange")
public class TakenItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "userTook")
    private int userTook;
    @Column(name = "userGave")
    private int userGave;
    @Column(name = "disk")
    private int disk;
    public TakenItem(){
        super();
    }

    public TakenItem(int id, int userTook, int userGave, int disk) {
        this.id = id;
        this.userTook = userTook;
        this.userGave = userGave;
        this.disk = disk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserTook() {
        return userTook;
    }

    public void setUserTook(int userTook) {
        this.userTook = userTook;
    }

    public int getUserGave() {
        return userGave;
    }

    public void setUserGave(int userGave) {
        this.userGave = userGave;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }
}
