package assessment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication2;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dmitry Kirkov
 */
public class Quality {
//!!! Make sure no unwanted signs in username!!!

    private Files file = new Files();
    private Map<String, String> units = new HashMap<String, String>();
    private ArrayList<String> comments = new ArrayList<>();
    private Statement stat;
    private ResultSet rs;

    public Quality() {
        checkIfQualityTableExists();
    }

    //If Quality table not exists in data base creates new one
    private void checkIfQualityTableExists() {//fix getting null from txt file
        DatabaseMetaData dm;
        try {
            dm = this.file.getConnection().getMetaData();
            this.rs = dm.getTables(null, null, "QUALITY", null);
            if (this.rs.next()) {//table exist
                System.out.println("Table QUALITY exists");
            } else {
                getQualityInformation();
                this.stat = this.file.getConnection().createStatement();
                this.stat.executeUpdate("CREATE TABLE QUALITY(UNIT_NUM INT, FULLNAME VARCHAR(50), COMMENT VARCHAR(1000), RATING INT)");

                //Add information to the table
                for (String i : this.units.keySet()) {
                    ArrayList<String> temporary = new ArrayList<>();
                    String[] spliter = this.units.get(i).replace(":", "").split("@");
                    for (String a : spliter) {
                        temporary.add(a);
                    }

                    for (String a : temporary) {
                        if (!a.equals("null")) {
                            String name = a.substring(a.indexOf("[") + 1, a.indexOf("]")).trim();
                            String comment = a.substring(0, a.lastIndexOf("[")).trim();
                            int rating = Integer.valueOf(a.substring(a.indexOf("]") + 1));
                            PreparedStatement pstat = this.file.getConnection().prepareStatement("INSERT INTO QUALITY(UNIT_NUM, FULLNAME, COMMENT, RATING) VALUES(?, ?, ?, ?)");
                            pstat.setInt(1, Integer.valueOf(i));
                            pstat.setString(2, name);
                            pstat.setString(3, comment);
                            pstat.setInt(4, rating);
                            pstat.executeUpdate();
                            pstat.close();
//                        this.stat.executeUpdate("INSERT INTO QUALITY VALUES(" + Integer.valueOf(i) + ", \'" + name + "\', \'" 
//                                +comment+"\', "+rating+")");
                        }
                    }
                }

                this.rs.close();
                this.stat.close();
                System.out.println("Table QUALITY created in data base.");
            }
        } catch (SQLException e) {
            System.out.println("SQL exseptions QUALITY checkIfUnitTableExist " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception in QUALITY checkIfUnitTableExist(): " + e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getQualityInformation() {
        this.file.downloadQualityInformation(this.units);
        //System.out.println(this.units);
    }

    public void addComment(int unit_number, String name, String comment, int rating) {//too long comment shouldn't be able to add
        try {
            //DatabaseMetaData dm = file.getConnection().getMetaData();
            Units unit = new Units();
            if (unit.checkUnit(String.valueOf(unit_number)) == true) {
                System.out.println("\nThe unit number " + unit_number + " exists");
                PreparedStatement pstat = this.file.getConnection().prepareStatement("INSERT INTO QUALITY(UNIT_NUM, FULLNAME, COMMENT, RATING) VALUES(?, ?, ?, ?)");
                pstat.setInt(1, unit_number);
                pstat.setString(2, name);
                pstat.setString(3, comment);
                pstat.setInt(4, rating);
                pstat.executeUpdate();
                pstat.close();
                System.out.println("New comment saved.\n");
            } else {
                System.out.println("\nThe unit number " + unit_number + " not exists");
                System.out.println("The comment not added.\n");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception Quality addComment() " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception Quality addComment() " + e.getMessage());
            System.out.println("Enter a whole number\n");
        }
    }

    public ArrayList<String> showUnitsComments() {
        try {
            this.stat = this.file.getConnection().createStatement();
            this.rs = this.stat.executeQuery("SELECT * FROM QUALITY");
            System.out.println("Comments of Units ");
            while (this.rs.next()) {
                System.out.println("Unit " + this.rs.getInt(1) + ": " + this.rs.getString(3) + " (" + this.rs.getString(2) + "). RATING " + this.rs.getInt(4));
                this.comments.add("Unit " + this.rs.getInt(1) + ": " + this.rs.getString(3) + " (" + this.rs.getString(2) + "). RATING " + this.rs.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception Quality showUnitComment() " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\nNumber Format Exception Quality showUnitComment() " + e.getMessage());
            System.out.println("Eneter a whole number.");
        }
        return this.comments;
    }
}
