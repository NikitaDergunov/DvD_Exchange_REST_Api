package com.ndergunov.dvdexchange.controller;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.model.DiskService;
import com.ndergunov.dvdexchange.model.DvdExchangeException;
import com.ndergunov.dvdexchange.template.TakenItemTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserDiskController {

    @Autowired
    DiskService diskService;

    @GetMapping("/freedisks")
    public List<Disk> freedisks(){
        return diskService.findFreeDisks();
    }

    @GetMapping("user/{userid}/disks")
    public List<Disk> userDisks(@PathVariable int userid){

        return diskService.findUserDisks(userid);
    }
    @GetMapping("user/{userid}/disks/{diskid}/settakeable")
    public String setTakeble(@PathVariable(name = "userid") int userID, @PathVariable(name ="diskid") int diskID){
        try {
            diskService.setTakeble(userID,diskID);
        } catch (DvdExchangeException e) {
           return e.getMessage();
        }
        return String.format("disk %d owned by %d user has been set takeable",diskID,userID);
    }
    @GetMapping("user/{userid}/disks/{diskid}/take/")
    public TakenItemTemplate take(@PathVariable(name = "userid") int userID, @PathVariable(name ="diskid") int diskID) throws DvdExchangeException {
        System.out.println("in rest: disk: " +diskID + " user: " + userID);

            return diskService.takeDisk(userID, diskID);


    }
    @GetMapping("user/{userid}/disks/takenby/")
    public List<TakenItemTemplate> takenby(@PathVariable int userid){
        return diskService.findTakenDisks(userid);
    }
    @GetMapping("user/{userid}/disks/takenfrom/")
    public List<TakenItemTemplate> takenfrom(@PathVariable int userid){
        return diskService.findGivenDisks(userid);
    }
}
