package assessment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assigment1;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author Dmitry Kirkov + Lars
 */
public class Units {

    private Map<String, String> units = new HashMap<>();
    private Map<String, String> able_to_book_units = new HashMap<>();
    private ArrayList<String> showUnits = new ArrayList<>();
    private final String file = "./resources/Units.txt";
    Files files = new Files();
//    UserInterface inter = new UserInterface();
    private Statement stat;
    private ResultSet rs;

    public Units() {
        checkIfUnitsTableExist();//Creating the table with variables if not exist
//        showAllUnits();
//        showRecommendedUnits(this.inter.returnAdults(), this.inter.returnChilds());
    }

    //Checking if UNITS table exist. If not creating table and insert values
    private void checkIfUnitsTableExist() {
        DatabaseMetaData dm;
        try {
            dm = this.files.getConnection().getMetaData();
            this.rs = dm.getTables(null, null, "UNITS", null);
            if (this.rs.next()) {//table exist
                System.out.println("Table Units exists");
            } else {
                downloadUnitsInformation();
                this.stat = this.files.getConnection().createStatement();
                this.stat.executeUpdate("CREATE TABLE UNITS(UNIT_NUM INT, BEDROOMS INT, BADS VARCHAR(50), QUALITY VARCHAR(10), ADULTS INT, CHILDS INT)");
                for (String i : this.units.keySet()) {
                    ArrayList<String> temperary = new ArrayList<>(Arrays.asList(this.units.get(i).split(",")));
                    String aaa = temperary.get(3).trim();
                    ArrayList<String> temp2 = new ArrayList<>(Arrays.asList(aaa.split(" ")));
//                    int a = Integer.valueOf(temperary.get(0).replaceAll("[^0-9]", ""));

                    if (temp2.contains("childs")) {
                        this.stat.executeUpdate("INSERT INTO UNITS VALUES(" + Integer.valueOf(i) + ", "
                                + Integer.valueOf(temperary.get(0).replaceAll("[^0-9]", "")) + ", "
                                + "\'" + temperary.get(1).trim() + "\', \'" + temperary.get(2).trim() + "\', " + Integer.valueOf(temp2.get(0))
                                + ", " + Integer.valueOf(temp2.get(3)) + ")");

                    } else if (temp2.contains("adults")) {
                        this.stat.executeUpdate("INSERT INTO UNITS VALUES(" + Integer.valueOf(i) + ", "
                                + Integer.valueOf(temperary.get(0).replaceAll("[^0-9]", "")) + ", "
                                + "\'" + temperary.get(1).trim() + "\', \'" + temperary.get(2).trim() + "\', " + Integer.valueOf(temp2.get(0))
                                + ", " + 0 + ")");
                    }
                }
                rs.close();
                this.stat.close();
                System.out.println("Table UNITS created in data base.");
            }
        } catch (SQLException e) {
            System.out.println("SQL exseptions Units checkIfUnitTableExist " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception: " + e.getMessage());
        }

    }

    //Getting values from Units.txt file for UNITS table
    private void downloadUnitsInformation() {//Make it private when it's done!!!!!!!!!!!!!!!!!@@@@@@@
        try {
            BufferedReader data = new BufferedReader(new FileReader(this.file));
            String line = "";
            while ((line = data.readLine()) != null) { //read each line up to end of file
                //add to hashmap i.e. units(key, values) syntax
                this.units.put(line.substring(0, line.lastIndexOf(":")),
                        line.substring(2, line.indexOf(".")));
            }
            //System.out.println(this.units);
            System.out.println("Units information downloaded");

        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong with Units data file");
        } catch (IOException e) {
            System.out.println("IOException in Units data.");
        }
    }

    public ArrayList<String> showAllUnits() {
        System.out.println();
        System.out.println("Here is the list of all units");
        System.out.println("---------------------------------");
        System.out.println("Unit num: number of bedrooms, beds, qulity price range, people allowed.");
        try {
            this.stat = this.files.getConnection().createStatement();

            this.rs = this.stat.executeQuery("SELECT * FROM UNITS");
            while (rs.next()) {
                if (rs.getInt(2) == 1) {
                    System.out.println("Unit " + this.rs.getInt(1) + ": include " + this.rs.getInt(2) + " bedroom, " + this.rs.getString(3)
                            + ", " + this.rs.getString(4) + ", " + this.rs.getInt(5) + " adults and " + this.rs.getInt(6) + " childs. ");

                    this.showUnits.add("Unit " + this.rs.getInt(1) + ": include " + this.rs.getInt(2) + " bedroom, " + this.rs.getString(3)
                            + ", " + this.rs.getString(4) + ", " + this.rs.getInt(5) + " adults and " + this.rs.getInt(6) + " childs. ");
                } else if (rs.getInt(2) > 1) {
                    System.out.println("Unit " + this.rs.getInt(1) + ": include " + this.rs.getInt(2) + " bedrooms, " + this.rs.getString(3)
                            + ", " + this.rs.getString(4) + ", " + this.rs.getInt(5) + " adults and " + this.rs.getInt(6) + " childs. ");
                    
                    this.showUnits.add("Unit " + this.rs.getInt(1) + ": include " + this.rs.getInt(2) + " bedrooms, " + this.rs.getString(3)
                            + ", " + this.rs.getString(4) + ", " + this.rs.getInt(5) + " adults and " + this.rs.getInt(6) + " childs. ");
                }
            }
            rs.close();
            stat.close();
            
//            this.inter.showAllUnitsInterface(this.showUnits);
        } catch (SQLException e) {
            System.out.println("SQL Exception Units showAllUnits " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception: " + e.getMessage());
        }
        return this.showUnits;
    }

    public void showSelectedUnit(int unit) {//show selected room by user
        boolean check = false;
        System.out.println();
        System.out.println("Your selected unit");
        System.out.println("Unit num: number of bedrooms, beds, qulity price range, people allowed.");

        try {
            this.stat = files.getConnection().createStatement();
            this.rs = this.stat.executeQuery("SELECT * FROM UNITS WHERE UNIT_NUM = " + unit);
            while (this.rs.next()) {
                if (this.rs.getInt(1) == unit) {
                    System.out.println("Unit " + rs.getInt(1) + ": " + rs.getInt(2) + " bedrooms, " + rs.getString(3)
                            + ", " + rs.getString(4) + ", " + rs.getInt(5) + " adults and " + rs.getInt(6) + " childs.");
                    check = true;
                }
            }
            if (check == false) {
                System.out.println("The selected unit is not exist");
            }
            rs.close();
            stat.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception in Units selectedUnit " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception: " + e.getMessage());
        }

    }

    //CHECK IF method returning information with required format
    public String returnSelectedUnit(int unit) {
        /*        Set eSet = units.entrySet(); //create a set for all entries
        Iterator it = eSet.iterator(); //iterator for the set
        Map.Entry entry = (Map.Entry) it.next();
        String key = (String) entry.getKey(); //numbers
        String value = (String) entry.getValue(); //units info
         */
        String value = "";
        try {
            this.stat = files.getConnection().createStatement();
            this.rs = this.stat.executeQuery("SELECT * FROM UNITS WHERE UNIT_NUM = " + unit);
            if (rs.next()) {
                value +="Unit "+rs.getInt(1)+" include: "+ String.valueOf(rs.getInt(2)) + " bedrooms, "
                        + rs.getString(3)//rs.getString(3) string about quantity and types of beds
                        + ", " + rs.getString(4) + ", " + String.valueOf(rs.getInt(5))
                        + " adults and " + String.valueOf(rs.getInt(6)) + " childs.";
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception in Units returnSelectedUnit " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception: " + e.getMessage());
        }

        return value;
    }

    //finding units for customer based on how number of adults and childs
    public Map<String, String> showRecommendedUnits(int adult, int child) {
        boolean finded = false;

        try {
            this.stat = files.getConnection().createStatement();
            this.rs = this.stat.executeQuery("SELECT * FROM UNITS");
            while (rs.next()) {
                if (this.rs.getInt(5) >= adult && this.rs.getInt(6) >= child) {
                    finded = true;
                    this.able_to_book_units.put(String.valueOf(this.rs.getInt(1)), String.valueOf(this.rs.getInt(2)) + " bedrooms, "
                            + this.rs.getString(3) + ", " + rs.getString(4) + ", " + String.valueOf(this.rs.getInt(5)) + " adults and "
                            + String.valueOf(this.rs.getInt(6)) + " childs.");
                }
            }
            if (finded == false) {
                System.out.println("Sorry. No suitable units for you.");
            } else if (finded == true) {
                showAbleToBookUnits();
//                this.inter.showRecomendedUnitsIterface(this.able_to_book_units);
            } else {
                System.out.println("Something wrong in Units recommendedUnit()");
            }
            rs.close();
            stat.close();
        } catch (SQLException e) {
            System.out.println("SQL Exceotion Units recommendedUnit() " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception Exceotion Units recommendedUnit() " + e.getMessage());
        }
        return this.able_to_book_units;
    }

    //showing all collected information fromt recommendedUnit(int adult, int child) method
    private void showAbleToBookUnits() {
        System.out.println("\nHere is the list of all units you are able to book.");
        System.out.println("Formatted as");
        System.out.println("Unit number: number of bedrooms, beds, price range, number of people.");
        for (String i : this.able_to_book_units.keySet()) {
            System.out.println("Unit " + i + " include: " + this.able_to_book_units.get(i));
        }
    }

    public boolean checkAbleToBookUnit(String unit) {
        if (this.able_to_book_units.containsKey(unit)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUnit(String unit) {//should chek from database
        boolean check = false;
        try {

            this.stat = this.files.getConnection().createStatement();
            this.rs = stat.executeQuery("SELECT * FROM UNITS");
            while (this.rs.next()) {
                if (this.rs.getInt(1) == Integer.valueOf(unit)) {
                    check = true;
                    break;
                } else {
                    check = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception Units checkUnit " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException Units checkUnit " + e.getMessage());
        }
        /*        if (this.units.containsKey(unit)) {
            return true;
        } else {
            return false;
        }
         */
        return check;
    }
}
