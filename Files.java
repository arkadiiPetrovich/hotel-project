package assessment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Dmitry Kirkov
 */
public class Files {

    private final String unit_file = "./resources/Units.txt";
    private final String quality_file = "./resources/Quality.txt";
    private final String URL = "jdbc:derby:HotelDB; create=true";
    private final String connectToDB = "jdbc:derby://localhost:1527/HotelDB";
    private final String passwordAndUser = "qwe";
    private Connection conn;

    public Files() {
        connectToDataBase();
    }

    public Connection getConnection() {
        return this.conn;
    }


    public String getUserAndPassword() {
        return this.passwordAndUser;
    }

    public void connectToDataBase() {
        try {
            //this.conn = DriverManager.getConnection(this.connectToDB, "qwe", "qwe");
            if (conn == null) {
                this.conn = DriverManager.getConnection(this.URL, "qwe", "qwe");
                System.out.println("Connected to " + this.URL);
            } else if (conn != null) {
                System.out.println("Alredy connected to database");
            } else {
                System.out.println("Something wrong with connection to database");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        }
    }

    public String returnQualiltyFileLocation() {
        return this.quality_file;
    }

    public void downloadQualityInformation(Map<String, String> map) {
        try {
            BufferedReader data = new BufferedReader(new FileReader(this.quality_file));
            String line = "";
            while ((line = data.readLine()) != null) {
                map.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":")));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something wrong with Units text file");
        } catch (IOException e) {
            System.out.println("IOException in Units data.");
        }
//        System.out.println(map);
    }

    public void qualityFileWriter(Map map) {
        try {
            FileWriter a = new FileWriter(this.quality_file);
            String write = "";

            int i = 0;
            for (Object key : map.keySet()) {
                write = write + key + map.get(key);
                if (i < map.size() - 1) {
                    write = write + "\n";
                }
//            System.out.println(key+" "+ map.get(key));
//            key+" "+ map.get(key)
            }
//        System.out.println(write);
            a.write(write);
            a.close();
        } catch (IOException e) {
            System.out.println("IO Exception in fileWriter");
        }
    }
}
