/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tugasrancang.postgree;

import static com.tugasrancang.postgree.InsertTable.InsertIntoTable;
//import static com.tugasrancang.postgree.InsertTable.line_map_val;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ryo Aditya
 */
@Component
public class ReadTable {

    static private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReadTable(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    public readAllTable(){
////        String status = null;
//        try {
//            readTable("LINESTOCK");
////            ReadTable("TABLESTOCK");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        return status;
//    }
    public List<Map<String, Object>> readAllTable(String tableName, String tableName2) {
        try {
            String readQuery = "SELECT * FROM " + tableName + "," + tableName2;
//            String readQuery = "SELECT * FROM " + tableName
//                    + " UNION ALL SELECT * FROM " + tableName2;
            return jdbcTemplate.queryForList(readQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    public List<Map<String, Object>> readDataByRcvNo(String tableName, String rcvno) {
        try {
            String readQuery = "SELECT * FROM " + tableName + " WHERE rcvno = ?";
            return jdbcTemplate.queryForList(readQuery, rcvno);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    
}
