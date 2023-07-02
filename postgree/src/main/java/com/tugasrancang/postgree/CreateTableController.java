/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tugasrancang.postgree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ryo Aditya
 */
@RestController
public class CreateTableController {

    private final CreateTableService createTableService;
//    private final InsertDataService insertDataService;

    @Autowired
    public CreateTableController(CreateTableService createTableService) {
        this.createTableService = createTableService;
    }

    @PostMapping("/createTable")
    public String createTable() {
        String status = createTableService.createTables();
        return status;
    }
    
//    @PostMapping("/insertData")
//    public String insertDataString(@RequestBody InsertDataRequest){
//    }

}
