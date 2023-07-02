/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tugasrancang.postgree.controller;

import com.tugasrancang.postgree.service.PostgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ryo Aditya
 */
@RestController
public class PostgreeController {
    private final PostgreeService postgreeService;
    
    @Autowired
    public PostgreeController(PostgreeService postgreeService){
        this.postgreeService = postgreeService;
    }
    
    @PostMapping("/insertData")
    public String insertDataFromCSV(){
        String status = postgreeService.insertDataFromCSV();
        return status;
    }
    
}
