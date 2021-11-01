package com.ndergunov.dvdexchange.model.jpa;

import com.ndergunov.dvdexchange.entity.Disk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiskRepository extends CrudRepository<Disk,Integer> {
    List<Disk> findAllByTakeableTrue();
}
