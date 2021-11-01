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
    List<TakenItem> findAllByCurrent_user(User current_user);
    List<TakenItem> findAllByPrevious_user(User previous_user);
}
