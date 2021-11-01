package com.ndergunov.dvdexchange.model.jpa;

import com.ndergunov.dvdexchange.entity.Disk;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiskRepository extends CrudRepository<Disk,Integer> {
    List<Disk> findAllByTakeableTrue();
    @Modifying
    @Query("update Disk d set d.takeable =?1 where d.id=?2")
    void setDiskTakable(Boolean takeable, Integer diskId );
}
