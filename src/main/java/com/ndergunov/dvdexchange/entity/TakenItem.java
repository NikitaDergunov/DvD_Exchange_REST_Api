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
    @JoinColumn(name = "current", referencedColumnName = "userID")
    private User current;
    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "previous", referencedColumnName = "userID")
    private User previous;
    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "disk", referencedColumnName = "diskID")
    private Disk disk;
    public TakenItem(){
        super();
    }

    public TakenItem(Integer id, User current, User previous, Disk disk) {
        this.id = id;
        this.current = current;
        this.previous = previous;
        this.disk = disk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current_user) {
        this.current = current_user;
    }

    public User getPrevious() {
        return previous;
    }

    public void setPrevious(User previous_user) {
        this.previous = previous_user;
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
                ", current_user=" + current.toString() +
                ", previous_user=" + previous.toString() +
                ", disk=" + disk.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakenItem takenItem = (TakenItem) o;
        return Objects.equals(id, takenItem.id) &&
                Objects.equals(current, takenItem.current) &&
                Objects.equals(previous, takenItem.previous) &&
                Objects.equals(disk, takenItem.disk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, current, previous, disk);
    }
}
