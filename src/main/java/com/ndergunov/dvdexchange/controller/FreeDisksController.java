package com.ndergunov.dvdexchange.controller;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.model.DiskExchangeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class FreeDisksController {
    @Autowired
    DiskExchangeDAO diskExchangeDAO;
    @GetMapping("/freedisks")
    public List<Disk> freedisks(){
        return diskExchangeDAO.findFreeDisks();
    }
}
