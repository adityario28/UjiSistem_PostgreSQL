package com.tugasrancang.postgree;

import static com.tugasrancang.postgree.CreateTable.CreateAllTable;
import static com.tugasrancang.postgree.CreateTable.CreateTable;
import static com.tugasrancang.postgree.InsertTable.InsertAllTable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostgreeApplication implements CommandLineRunner{
//        static final String DB_URL = "jdbc:postgresql://localhost:5433/ujisistemc";
//        static final String USER = "postgres";
//        static final String PASS = "Mahesario28.";
        
    //Ryan
       // static final String DB_URL = "jdbc:postgresql://localhost:5432/ujisistemc";
       // static final String USER = "postgres";
        //static final String PASS = "agung2002";
    
    //Nadya
       static final String DB_URL = "jdbc:postgresql://localhost:5432/ujisistemc";
       static final String USER = "postgres";
        static final String PASS = "admin";
        
	public static void main(String[] args) {
		SpringApplication.run(PostgreeApplication.class, args);
	}

        @Override
        public void run(String... args) throws Exception {
            System.out.println(CreateAllTable());
//            System.out.println(InsertAllTable(DB_URL,USER,PASS));
        }
}
