package com.ndergunov.dvdexchange.model.jpa;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.TakenItem;
import com.ndergunov.dvdexchange.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface TakenItemRepository extends CrudRepository<TakenItem,Integer> {
    List<TakenItem> findAllByCurrent(User current);
    List<TakenItem> findAllByPrevious(User previous);
    boolean existsByCurrentAndDisk(User currnt,Disk disk);
    TakenItem findByDisk(Disk disk);
}
