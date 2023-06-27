/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tugasrancang.postgree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
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
//            DataSource ds =  new DataSource();
//            jdbcTemplate = new JdbcTemplate(ds);
//            jdbcTemplate = dbcr.jdbcTemplate(dbcr.dataSource());
//            JdbcTemplate jdbcTemplate = new JdbcTemplate();
//            jdbcTemplate.setDataSource(dbcr.dataSource());
//            DriverManagerDataSource ds = new DriverManagerDataSource();
//            SingleConnectionDataSource ds = new SingleConnectionDataSource();
//            DriverManagerDataSource ds = new DriverManagerDataSource();
//            ds.setDriverClassName(driverClass);
//            ds.setUrl(DBURL);
//            ds.setUsername(DB_USERNAME);
//            ds.setPassword(DB_PWD);
//            DBCredential dbcr = new DBCredential();
//            DataSource db_credential = dbcr.dataSource();
//            jdbcTemplate = new JdbcTemplate(ds);
            //jdbcTemplate.execute("DROP TABLE IF EXIST"+ tablename);            
//            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + tablename + "(id bigint)");
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

//oracle 
//     public static String CreateTable(String DB_URL, String DB_USER, String DB_PASSWORD) {
//        String status = "Success";
//        List<String> fileNames = new ArrayList<>();
//        fileNames.add("D:\\Ujisistem_tugas\\github_ujisistem\\UjiSistem_PostgreSQL\\postgree\\src\\main\\resources\\map\\line_map_1.txt"); // Provide the path to your first text file here
//        fileNames.add("D:\\Ujisistem_tugas\\github_ujisistem\\UjiSistem_PostgreSQL\\postgree\\src\\main\\resources\\map\\table_map_1.txt"); // Provide the path to your second text file here
//        // Add more file names if necessary
//        
//        try {
//            // Establish database connection
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//
//            for (String fileName : fileNames) {
//                // Read the text file
//                List<String> tableData = readTableDataFromFile(fileName);
//
//                // Split table name and column data
//                String tableName = tableData.get(0);
//                List<String> columnData = tableData.subList(1, tableData.size());
//
//                // Check if the table already exists
//                if (tableExists(connection, tableName)) {
//                    System.out.println("Table " + tableName + " already exists. Skipping creation.");
//                    continue;
//                }
//
//                // Generate the CREATE TABLE statement
//                String createTableQuery = generateCreateTableQuery(tableName, columnData);
//
//                // Execute the CREATE TABLE statement
//                Statement statement = connection.createStatement();
//                statement.executeUpdate(createTableQuery);
//
//                System.out.println("Table created: " + tableName);
//            }
//
//            // Close resources
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return status;
//    }
//
//    private static List<String> readTableDataFromFile(String fileName) throws IOException {
//        List<String> tableData = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new FileReader(fileName));
//
//        String line;
//        while ((line = reader.readLine()) != null) {
//            tableData.add(line.trim());
//        }
//
//        reader.close();
//
//        return tableData;
//    }
//
//    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
//        Statement statement = connection.createStatement();
//        String sql = "SELECT * FROM ALL_TABLES WHERE TABLE_NAME = '" + tableName + "'";
//        ResultSet resultSet = statement.executeQuery(sql);
//
//        boolean tableExists = resultSet.next();
//
//        resultSet.close();
//        statement.close();
//
//        return tableExists;
//    }
//
//    private static String generateCreateTableQuery(String tableName, List<String> columnData) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("CREATE TABLE ").append(tableName).append(" (");
//
//        for (int i = 0; i < columnData.size(); i++) {
//            String[] parts = columnData.get(i).split(":");
//            String columnName = parts[0].trim();
//            String dataType = parts[1].trim();
//
//            sb.append(columnName).append(" ").append(dataType);
//
//            if (i < columnData.size() - 1) {
//                sb.append(", ");
//            }
//        }
//
//        sb.append(")");
//
//        return sb.toString();
//    }
//
//}
