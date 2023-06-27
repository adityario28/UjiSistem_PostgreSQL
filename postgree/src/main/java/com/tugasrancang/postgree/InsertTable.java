/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tugasrancang.postgree;

import static com.tugasrancang.postgree.CreateTable.CreateTable;
import static com.tugasrancang.postgree.CreateTable.line_map_path;
import static com.tugasrancang.postgree.CreateTable.table_map_path;
import static com.tugasrancang.postgree.Mapping.HashMapFromTextFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

/**
 *
 * @author miche
 */
public class InsertTable {
    
    final static String line_map_val = "D:\\Tugas\\Semester9\\Pengujian Sistem\\Data-5\\Data-5\\Full\\";
    private static JdbcTemplate jdbcTemplate;
    
    public static String InsertAllTable(String url, String user, String pass) throws Exception{
        String status =null;
        //Convert txt to Hashmap
        try {
//            InsertIntoTable(table_map,"TABLESTOCK",url,user,pass);
            InsertIntoTable(line_map_val,url,user,pass);
            status = "Table Inserted Succesfully";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public static void InsertIntoTable(String data, String url, String user, String pass){
        Map<String, String> tipe_l = HashMapFromTextFile ("map/line_map.txt");
        Map<String, String> tipe_t = HashMapFromTextFile ("map/table_map.txt"); 
        BufferedReader br = null;
        
       
        
        
        String[] files = new File(data).list();
        List<String> listrequest = new ArrayList<>();
        try {
            for (String filename : files) {
                String header = "";
                int i = 0;
                FileReader reader = new FileReader(data + filename);
                BufferedReader bfr = new BufferedReader(reader);
                String line = bfr.readLine();  
                boolean firstline = true;

                while(line != null){
                    String h = "";
                    String v = "";
                   
                    if (!firstline) {
                        String replace = line.replace("\"", "");
                        String[] headerr = header.split(",");
                        String[] value = replace.split(",");
                    for (i = 0; i < headerr.length; i++) {
                        String head = headerr[i].trim();
                        String val = value[i].trim();
                        String tipel = tipe_l.get(headerr[i].trim());
                        String tipet = tipe_t.get(headerr[i].trim());
                        
                        if (val.equals("")) {
                            String b = null;
                            if (i != headerr.length - 1 ) {
                                v = v + b + ",";
                            } else {
                                v = v + b;
                            }
                            
                            if (i != headerr.length - 1 ) {
                                h = h + head + ",";
                            } else {
                                h = h + head;
                            }
                            
                        }
                        //Menentukan INT
                        else if ((tipel != null && tipel.equals("int")) || (tipet != null && tipet.equals("int"))) {
                            String b = val;
                            if (i != headerr.length - 1 ) {
                                v = v + b + ",";
                            } else {
                                v = v + b;
                            }
                            
                            if (i != headerr.length - 1 ) {
                                h = h + head + ",";
                            } else {
                                h = h + head;
                            }
                            
                        }
                        //Menentukan Date
                        else if ((tipel != null && tipel.equals("Date")) || (tipet != null && tipet.equals("Date"))) {
                            String b = "\'" + val.substring(0, 4) + "-" + val.substring(4, 6) + "-" + val.substring(6, 8) + "\'";
                            if (i != headerr.length - 1 ) {
                                v = v + b + ",";
                            } else {
                                v = v + b;
                            }
                            
                            if (i != headerr.length - 1 ) {
                                h = h + head + ",";
                            } else {
                                h = h + head;
                            }
                            
                        }
                        //Menentukan Time
                        else if (head.equals("RCVTIME")) {
                            String b = null;
                            if (i != headerr.length - 1 ) {
                                v = v + b + ",";
                            } else {
                                v = v + b;
                            }
                            
                            if (i != headerr.length - 1 ) {
                                h = h + head + ",";
                            } else {
                                h = h + head;
                            }
                            
                        }
                        //Membedakan nama table ganda
                        else if (head.equals("RCVNO")) {
                            if (h.contains("RCVNO")) {
                                String b = val;
                                if (i != headerr.length - 1 ) {
                                    v = v + b + ",";
                                } else {
                                    v = v + b;
                                }

                                if (i != headerr.length - 1 ) {
                                    h = h + head + "_1" + ",";
                                } else {
                                    h = h + head + "_1";
                                }

                            } else {
                                String b = val;
                                if (i != headerr.length - 1 ) {
                                    v = v + b + ",";
                                } else {
                                    v = v + b;
                                }

                                if (i != headerr.length - 1 ) {
                                    h = h + head + ",";
                                } else {
                                    h = h + head;
                                }

                            }
                        }
                        else {
                            if (i != headerr.length - 1 ) {
                                v = v + "\'" + val + "\'" + ",";
                            } else {
                                v = v + val;
                            }
                            
                            if (i != headerr.length - 1 ) {
                                h = h + head + ",";
                            } else {
                                h = h + head;
                            }
                            
                        }
                    }
                    } else {
                        header = line;
                        h = null;
                        firstline = false;
                    }
                    
                    if (h != null) {
                        try {
                             SingleConnectionDataSource ds = new SingleConnectionDataSource();
                            ds.setDriverClassName("org.postgresql.Driver");
                            ds.setUrl(url);
                            ds.setUsername(user);
                            ds.setPassword(pass);

                            JdbcTemplate jdbcTemplate = new JdbcTemplate( ds);
                            if (h != null) {
                                if(h.contains("HoyaItemType")){
                                        jdbcTemplate.execute("INSERT INTO TABLESTOCK (" + h + ") VALUES (" + v + ")");
                                } else {
                                        jdbcTemplate.execute("INSERT INTO LINESTOCK (" + h + ") VALUES (" + v + ")");
                                }
                            }

                            ds.destroy();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    line = bfr.readLine();
                }
                bfr.close();
                reader.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
    }
}
