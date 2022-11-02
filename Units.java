package Assignment2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assigment1;

import Assignment1.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Dmitry Kirkov + Lars
 */
public class Units {

    public Map<String, String> units = new HashMap<String, String>();
    private Map<String, String> able_to_book_units = new HashMap<String, String>();
    private String file = "./resources/Units.txt";

    public void downloadUnitsInformation() {
        try {
            BufferedReader data = new BufferedReader(new FileReader(this.file));
            String line = "";
            while ((line = data.readLine()) != null) { //read each line up to end of file
                //add to hashmap i.e. units(key, values) syntax
                this.units.put(line.substring(0, line.lastIndexOf(":")),
                        line.substring(0, line.indexOf(".")));
            }
            //System.out.println(this.units);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong with Units data file");
        } catch (IOException e) {
            System.out.println("IOException in Units data.");
        }
    }
    
    //gets info about units
    public String showAllUnits() {
        String out = "";
        out += "Here is the list of all units";
        out +="\n-----------------------------";
        for (String   i : this.units.keySet()) {
            out +="\nUnit " + this.units.get(i);
        }
        return out;
    }

    public void selectedUnit(int unit) {
        System.out.println("Your selected unit: " + unit);
    }

    public String returnSelectedUnit(int unit) {
        return Price.unit_information;
    }

    public boolean recommendedUnit(int adult, int child) {//check what unit user able to book
        ArrayList<String> number_of_people = new ArrayList<String>();
        boolean finded = false;
        for (String i : this.units.keySet()) {
            String[] check = this.units.get(i).split(",");
            check[3] = check[3].replaceAll("\\.", "").trim();
            number_of_people.add(check[3].replaceAll("[a-z]", "").trim());
//            System.out.println(number_of_people.get(Integer.getInteger(i)-1));
        }
        //System.out.println(number_of_people.get(1).length());
        for (int i = 0; i < number_of_people.size(); i++) {
            if (child == 0) {
                if (Character.getNumericValue(number_of_people.get(i).charAt(0)) >= adult) {
                    this.able_to_book_units.put(String.valueOf(i + 1), this.units.get(String.valueOf(i + 1)));
                    finded = true;
                }
            } else if (number_of_people.get(i).length() > 2 && child != 0) {
                if (Character.getNumericValue(number_of_people.get(i).charAt(0)) >= adult && Character.getNumericValue(number_of_people.get(i).charAt(4)) >= child) {
                    this.able_to_book_units.put(String.valueOf(i + 1), this.units.get(String.valueOf(i + 1)));
                    finded = true;
                }
            }
        }
        if (finded == false) {
            System.out.println("Sorry. No suitable units for you.");
        }
        return finded;
    }

    public void showAbleToBookUnits() {
        System.out.println("\nHere is the list of all units you able to book.\n");
        System.out.println("Formatted as:");
        System.out.println("Unit number: number of bedrooms, beds, price range, number of people.");
        for (String i : this.able_to_book_units.keySet()) {
            System.out.println("Unit " + i + " include: " + this.able_to_book_units.get(i));
        }
    }

    public boolean checkSelectedUnit(String unit) {
        if (this.able_to_book_units.containsKey(unit)) {
            return true;
        } else {
            return false;
        }

    }
}
