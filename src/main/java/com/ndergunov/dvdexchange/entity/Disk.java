package com.ndergunov.dvdexchange.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity

@Table(name = "disks", schema = "dvd_exchange")
public class Disk {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diskID")
    private Integer id;
    @Column(name = "NAME")
    private String name;

    @Column(name = "TAKEABLE")
    private Boolean takeable;

    public Disk() {
        super();
    }


    public Disk(Integer id, String name, Boolean takeable) {
        super();
        this.id = id;
        this.name = name;
        this.takeable = takeable;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isTakeable() {
        return takeable;
    }

    public void setTakeable(Boolean takeable) {
        this.takeable = takeable;
    }

    @Override
    public String toString() {
        return "Disk{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", takeable=" + takeable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disk disk = (Disk) o;
        return Objects.equals(id, disk.id) &&
                Objects.equals(name, disk.name) &&
                Objects.equals(takeable, disk.takeable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, takeable);
    }
}
