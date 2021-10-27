package com.ndergunov.dvdexchange.controller;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.TakenItem;
import com.ndergunov.dvdexchange.model.DiskExchangeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class UserDiskController {

    @Autowired
    DiskExchangeDAO diskExchangeDAO;

    @GetMapping("user/disks/{userid}")
    public List<Disk> userDisks(@PathVariable int userid){

        return diskExchangeDAO.findUserDisks(userid);
    }
    @GetMapping("user/disks/{userid}/{diskid}/settakeable/")
    public void setTakeble(@PathVariable(name = "userid") int userID, @PathVariable(name ="diskid") int diskID){
         diskExchangeDAO.setTakeble(userID,diskID);
    }
    @GetMapping("user/disks/{userid}/{diskid}/take/")
    public void take(@PathVariable(name = "userid") long userID, @PathVariable(name ="diskid") long diskID){
    }
    @GetMapping("user/disks/takenby/{userid}")
    public List<TakenItem> takenby(@PathVariable String userid){
        return Collections.emptyList();
    }
    @GetMapping("user/disks/takenfrom/{userid}")
    public List<TakenItem> takenfrom(@PathVariable String userid){
        return Collections.emptyList();
    }
}
