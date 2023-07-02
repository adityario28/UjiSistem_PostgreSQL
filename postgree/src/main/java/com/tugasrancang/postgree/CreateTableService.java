/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tugasrancang.postgree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryo Aditya
 */
@Service
public class CreateTableService {
    private final CreateTable createTable;

    @Autowired
    public CreateTableService(CreateTable createTable) {
        this.createTable = createTable;
    }

    public String createTables() {
        String status = createTable.CreateAllTable();
        return status;
    }
    
}
