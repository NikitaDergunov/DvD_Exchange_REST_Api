package com.ndergunov.dvdexchange.controller;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.User;
import com.ndergunov.dvdexchange.model.DiskService;
import com.ndergunov.dvdexchange.model.DvdExchangeException;
import com.ndergunov.dvdexchange.model.hibernate.HibernateUserRepository;
import com.ndergunov.dvdexchange.security.jwt.JwtFilter;
import com.ndergunov.dvdexchange.security.jwt.JwtProvider;
import com.ndergunov.dvdexchange.template.TakenItemTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserDiskController {

    @Autowired
    DiskService diskService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    HibernateUserRepository hibernateUserRepository;

    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest authRequest){
        String login = authRequest.getLogin();
        String password = authRequest.getPassword();
        try{
        User user = hibernateUserRepository.loadUserByUsernameAndPassword(login,password);
        String token = jwtProvider.generateToken(login);
            System.out.println(token);
        return token; }
        catch (UsernameNotFoundException ex){
            return ex.getMessage();
        }
    }

    @GetMapping("/freedisks")
    public List<Disk> freedisks(){
        return diskService.findFreeDisks();
    }

    @GetMapping("user/disks")
    public List<Disk> userDisks(HttpServletRequest request){


        return diskService.findUserDisks(getUserIdFromHttpServletRequest(request));
    }
    @GetMapping("user/disks/{diskid}/settakeable")
    public String setTakeble(@PathVariable(name ="diskid") int diskID, HttpServletRequest request){
        try {
            diskService.setTakeble(getUserIdFromHttpServletRequest(request),diskID);
        } catch (DvdExchangeException e) {
           return e.getMessage();
        }
        return String.format("disk %d has been set takeable",diskID);
    }
    @GetMapping("user/disks/{diskid}/take/")
    public TakenItemTemplate take(@PathVariable(name ="diskid") int diskID, HttpServletRequest request) throws DvdExchangeException {


            return diskService.takeDisk(getUserIdFromHttpServletRequest(request), diskID);


    }
    @GetMapping("user/disks/takenby/")
    public List<TakenItemTemplate> takenby(HttpServletRequest request){
        return diskService.findTakenDisks(getUserIdFromHttpServletRequest(request));
    }
    @GetMapping("user/disks/takenfrom/")
    public List<TakenItemTemplate> takenfrom(HttpServletRequest request){
        return diskService.findGivenDisks(getUserIdFromHttpServletRequest(request));
    }

    public Integer getUserIdFromHttpServletRequest(HttpServletRequest request){
        String token = jwtFilter.getTokenFromRequest(request);
        String login = jwtProvider.getLoginFromToken(token);
        User user = hibernateUserRepository.loadUserByUsername(login);
        return user.getUserID();
    }
}
