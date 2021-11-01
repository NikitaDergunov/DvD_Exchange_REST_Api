package com.ndergunov.dvdexchange.controller;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.User;
import com.ndergunov.dvdexchange.model.DiskService;
import com.ndergunov.dvdexchange.model.DvdExchangeException;
import com.ndergunov.dvdexchange.model.UserRepository;
import com.ndergunov.dvdexchange.security.jwt.JwtProvider;
import com.ndergunov.dvdexchange.template.TakenItemTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserDiskController {

    @Autowired
    DiskService diskService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest authRequest){
        String login = authRequest.getLogin();
        String password = authRequest.getPassword();
        try{
        User user = userRepository.loadUserByUsernameAndPassword(login,password);
        String token = jwtProvider.generateToken(login);
            System.out.println(token);
        return token; /*TODO: add jwt*/}
        catch (UsernameNotFoundException ex){
            return ex.getMessage();
        }
    }

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
