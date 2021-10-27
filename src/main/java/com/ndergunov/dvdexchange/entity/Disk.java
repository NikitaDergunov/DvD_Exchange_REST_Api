package com.ndergunov.dvdexchange.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity

@Table(name = "disks", schema = "dvd_exchange")
public class Disk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diskID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "owner")
    private int owner;
    @Column(name = "TAKEABLE")
    private boolean takeable;

    public Disk() {
        super();
    }


    public Disk(int id, String name, int owner, boolean takeable) {
        super();
        this.id = id;
        this.name = name;
        this.takeable = takeable;
    }
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTakeable() {
        return takeable;
    }

    public void setTakeable(boolean takeable) {
        this.takeable = takeable;
    }
}
