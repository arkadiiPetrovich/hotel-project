package Assignment2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Dmitry Kirov
 */
public class Files {
    private final String unitFile = "./resources/Units.txt";
    private final String qualityFile = "./resources/Quality.txt";
    
    public String returnQualiltyFileLocation(){
        return this.qualityFile;
    }
    public void downloadQualityInformation(Map<String,String> map) {
        try {
            BufferedReader data = new BufferedReader(new FileReader(this.qualityFile));
            String line = "";
            while ((line = data.readLine()) != null) {
                map.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":")));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something wrong with Units data file");
        } catch (IOException e) {
            System.out.println("IOException in Units data.");
        }
    }
    

    public void qualityFileWriter(Map map) {
        try {
            FileWriter a = new FileWriter(this.qualityFile);
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
