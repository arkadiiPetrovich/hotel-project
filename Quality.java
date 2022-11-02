package Assignment2;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dmitry Kirov
 */
public class Quality {

    private Files file = new Files();
    private Map<String, String> units = new HashMap<String, String>();

    public void getQualityInformation() {
        file.downloadQualityInformation(this.units);
/*        try {
            BufferedReader data = new BufferedReader(new FileReader(this.file.returnQualiltyFileLocation()));
            String line = "";
            while ((line = data.readLine()) != null) {
                this.units.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":")));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something wrong with Units data file");
        } catch (IOException e) {
            System.out.println("IOException in Units data.");
        }*/
        System.out.println(this.units);
    }

    public void addComment(String unit_number, String comment) {
        try {
            BufferedReader data = new BufferedReader(new FileReader(this.file.returnQualiltyFileLocation()));
            String new_information = String.valueOf(this.units.get(unit_number));
            if (this.units.containsKey(unit_number)) {
                if (!new_information.contains("null")) {

                    new_information += "@" + comment;
                    this.units.replace(unit_number.trim(), new_information.trim());
                } else if (new_information.contains("null")) {
                    new_information = ":" + comment;
                    this.units.replace(unit_number.trim(), new_information.trim());
                } else {
                    System.out.println("Something wrong with new comment.");
                }
                file.qualityFileWriter(this.units);
            } else {
                System.out.println("Unit not exist");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }
    }

    public String showUnitComments(String unit_number) {
        ArrayList<String> comments = new ArrayList<>();
        String string = this.units.get(unit_number);
        String out = "";
        String[] spliter = string.replaceAll(":", "").split("@");
//        System.out.println(spliter[1]);
        for (int i = 0; i < spliter.length; i++) {
            comments.add(spliter[i].substring(0, spliter[i].lastIndexOf(')') + 1) + " Rating: " + spliter[i].substring(spliter[i].lastIndexOf(')') + 1));
        }
        for(String a:comments){
            out += a;
        }
        return out;
    }

    
}
