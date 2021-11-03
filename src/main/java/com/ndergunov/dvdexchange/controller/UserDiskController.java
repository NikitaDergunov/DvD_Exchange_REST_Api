package com.ndergunov.dvdexchange.controller;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.User;
import com.ndergunov.dvdexchange.model.DiskService;
import com.ndergunov.dvdexchange.model.DvdExchangeException;

import com.ndergunov.dvdexchange.model.jpa.UserRepository;
import com.ndergunov.dvdexchange.security.jwt.JwtFilter;
import com.ndergunov.dvdexchange.security.jwt.JwtProvider;
import com.ndergunov.dvdexchange.template.TakenItemTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserDiskController {
    Logger logger = LoggerFactory.getLogger(UserDiskController.class);
    @Autowired
    DiskService diskService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    UserRepository hibernateUserRepository;

    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest authRequest){
        String login = authRequest.getLogin();
        String password = authRequest.getPassword();

           if(hibernateUserRepository.existsByLoginAndPassword(login,password)){
        User user = hibernateUserRepository.findByLoginAndPassword(login,password);
        String token = jwtProvider.generateToken(login);
            System.out.println(token);
            logger.info("user " + login + " authenticated");
        return token;
           }
           else{
               logger.warn("wrong username and/or password for user "+login);
               throw new AccessDeniedException("wrong username and/or password");
           }
    }

    @GetMapping("/freedisks")
    public List<Disk> freedisks(){
        logger.info("somone requested free disks list");
        return diskService.findFreeDisks();
    }

    @GetMapping("user/disks")
    public List<Disk> userDisks(HttpServletRequest request){
        logger.info("user requested list of disks owned by him");
        return diskService.findUserDisks(getUserIdFromHttpServletRequest(request));
    }
    @GetMapping("user/disks/{diskid}/settakeable")
    public String setTakeble(@PathVariable(name ="diskid") int diskID, HttpServletRequest request){
        try {
            logger.info("user requests settin disk takeable");
            diskService.setTakeble(getUserIdFromHttpServletRequest(request),diskID);
        } catch (DvdExchangeException e) {

           return e.getMessage();
        }
        return String.format("disk %d has been set takeable",diskID);
    }
    @GetMapping("user/disks/{diskid}/take/")
    public TakenItemTemplate take(@PathVariable(name ="diskid") int diskID, HttpServletRequest request) throws DvdExchangeException {

        logger.info("user is trying to take disk "+diskID);
            return diskService.takeDisk(getUserIdFromHttpServletRequest(request), diskID);


    }
    @GetMapping("user/disks/takenby/")
    public List<TakenItemTemplate> takenby(HttpServletRequest request){
        logger.info("user requests list of disks taken by him");
        return diskService.findTakenDisks(getUserIdFromHttpServletRequest(request));
    }
    @GetMapping("user/disks/takenfrom/")
    public List<TakenItemTemplate> takenfrom(HttpServletRequest request){
        logger.info("user requests list of disks taken from him");
        return diskService.findGivenDisks(getUserIdFromHttpServletRequest(request));
    }

    public Integer getUserIdFromHttpServletRequest(HttpServletRequest request){
        String token = jwtFilter.getTokenFromRequest(request);
        String login = jwtProvider.getLoginFromToken(token);
        User user = hibernateUserRepository.findByLogin(login);
        return user.getUserID();
    }
}
