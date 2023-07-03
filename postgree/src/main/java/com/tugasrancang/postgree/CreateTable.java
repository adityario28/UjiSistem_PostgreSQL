/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tugasrancang.postgree;


import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author miche
 */
@Component
public class CreateTable {

    final static String line_map_path = "map/line_map.txt";
    final static String table_map_path = "map/table_map.txt";
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public CreateTable(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //postgresql
    public static String CreateAllTable() {
        String status = null;
        //Convert txt to Hashmap
        Map<String, String> line_map = Mapping.HashMapFromTextFile(line_map_path);
        Map<String, String> table_map = Mapping.HashMapFromTextFile(table_map_path);
        try {
            CreateTable(table_map, "TABLESTOCK");
            CreateTable(line_map, "LINESTOCK");

            status = "Table Created Succesfully";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void CreateTable(Map<String, String> map, String tablename) {
        try {
            String createQuery = "CREATE TABLE IF NOT EXISTS " + tablename + " (";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                createQuery += entry.getKey() + " " + entry.getValue() + ", ";
                System.out.println(entry.getKey() + ";" + entry.getValue());
            }
            if (!map.isEmpty()) {
            createQuery = createQuery.substring(0, createQuery.length() - 2);
        }
            createQuery += ") ";
            jdbcTemplate.execute(createQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
