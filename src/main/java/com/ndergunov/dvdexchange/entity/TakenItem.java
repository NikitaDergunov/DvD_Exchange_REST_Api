package com.ndergunov.dvdexchange.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "takenitem", schema = "dvd_exchange")
public class TakenItem {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "current_", referencedColumnName = "userID")
    private User current_user;
    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "previous_", referencedColumnName = "userID")
    private User previous_user;
    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "disk", referencedColumnName = "diskID")
    private Disk disk;
    public TakenItem(){
        super();
    }

    public TakenItem(Integer id, User current_user, User previous_user, Disk disk) {
        this.id = id;
        this.current_user = current_user;
        this.previous_user = previous_user;
        this.disk = disk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(User current_user) {
        this.current_user = current_user;
    }

    public User getPrevious_user() {
        return previous_user;
    }

    public void setPrevious_user(User previous_user) {
        this.previous_user = previous_user;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    @Override
    public String toString() {
        return "TakenItem{" +
                "id=" + id +
                ", current_user=" + current_user.toString() +
                ", previous_user=" + previous_user.toString() +
                ", disk=" + disk.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakenItem takenItem = (TakenItem) o;
        return Objects.equals(id, takenItem.id) &&
                Objects.equals(current_user, takenItem.current_user) &&
                Objects.equals(previous_user, takenItem.previous_user) &&
                Objects.equals(disk, takenItem.disk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, current_user, previous_user, disk);
    }
}
