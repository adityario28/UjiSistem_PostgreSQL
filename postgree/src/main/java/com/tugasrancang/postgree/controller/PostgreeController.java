/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tugasrancang.postgree.controller;

import com.tugasrancang.postgree.service.PostgreeService;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
/**
 *
 * @author Ryo Aditya
 */
@RestController
public class PostgreeController {

    private final PostgreeService postgreeService;

    @Autowired
    public PostgreeController(PostgreeService postgreeService) {
        this.postgreeService = postgreeService;
    }

    @PostMapping("/insertData")
    public String insertDataFromCSV() {
        String status = postgreeService.insertDataFromCSV();
        return status;
    }
    
    @PostMapping("insertByte")
    public double insertDataFromByte(@RequestBody String requestData){
        JSONObject object = new JSONObject(requestData);
        String[] keys = JSONObject.getNames(object);
        double waktu = 0;
        Date awal = new Date(System.currentTimeMillis());
        for (String key : keys) {
            Object value = object.get(key);
//            System.out.println(value.toString());
//            System.out.println(key);
            waktu = postgreeService.insertDataFromByte(key, value.toString());
            // Determine type of value and do something with it...
        }
//        Date akhir = new Date(System.currentTimeMillis());
//        long waktu = (akhir.getTime() - awal.getTime());
//        String data = new String(requestData);
//        String[] parts = data.split(":");
//        String header = new String(parts[0]);
//        String value = new String(parts[1]);

//        System.out.println("Header: " + header);
//        System.out.println("Value: " + value);
        
        System.out.println("Table Inserted Succesfully // Time " + String.format("%.9f", waktu) + " second");
        return waktu;
    }

    @GetMapping("/allData")
//    public String readAllData(){
//        postgreeService.readAllData();
//        
//        return "read All Data";
//    }
    public ResponseEntity<List<Map<String, Object>>> readAllData() {
        List<Map<String, Object>> data = postgreeService.readAllData("LINESTOCK", "TABLESTOCK");
        return ResponseEntity.ok(data);
    }
}
