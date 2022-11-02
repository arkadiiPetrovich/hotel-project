package Assignment2;

import Assignment1.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assigment1;

/**
 *
 * @author Dmitry Kirov
 */
public class Price {

    private static final double adult = 30.00;
    private static final double child = 15.00;
    private static double room = 40.00;
    static double lowPrice = 0;
    static double medPrice = 0;
    static double highPrice = 0;
    private static double customerBill = 0;
    static int rooms;
    static String unit_information = "";
    static Units units = new Units();

    public static void chosenUnitPrice(int unit_num, int adults, int childs) {

        units.downloadUnitsInformation();
        unit_information = units.returnSelectedUnit(unit_num);

        System.out.println("Unit info: " + unit_information);
        if (unit_information.contains("low")) {
            Price.lowPrice = lowUnitPrice(unit_information, adults, childs);
            setCustomerBill(lowPrice);
            System.out.println("Total: $" + lowPrice +" NZD");
        } else if (unit_information.contains("med")) {
            Price.medPrice = medUnitPrice(unit_information, adults, childs);
            setCustomerBill(medPrice);
            System.out.println("Total: $" + medPrice +" NZD");
        } else if (unit_information.contains("high")) {
            Price.highPrice = highUnitPrice(unit_information, adults, childs);
            setCustomerBill(highPrice);
            System.out.println("Total: $" + highPrice +" NZD");
        } else {
            System.out.println("Something went wrong in unit price calculation");
            //System.exit(0);
            //return -0.0001;
        }
    }

    private static double lowUnitPrice(String unit_info, int adults, int childs) {
        final double low_fee = 20.00;
        rooms = numOfBedrooms(unit_info);

        return ((Price.adult * adults) + (Price.child * childs) + (Price.room * rooms) + low_fee);
    }

    private static double medUnitPrice(String unit_info, int adults, int childs) {
        final double med_fee = 35.00;
        rooms = numOfBedrooms(unit_info);

        return ((Price.adult * adults) + (Price.child * childs) + (rooms * med_fee));
    }

    private static double highUnitPrice(String unit_info, int adults, int childs) {
        final double high_fee = 50.00;
        rooms = numOfBedrooms(unit_info);

        return ((Price.adult * adults) + (Price.child * childs) + (rooms * high_fee));
    }

    private static int numOfBedrooms(String unit_info) {

        int number = 0;
        String number_of_unit = "";
        for (String c : unit_info.split(",")) {
//         System.out.println(c);
            c = c.trim();
            if (c.contains("bedroom") || c.contains("bedrooms")) {
                char[] chars = c.toCharArray();
                for (char a : chars) {
                    if (Character.isDigit(a)) {
                        number_of_unit += a;
//                        number=Character.getNumericValue(a);
                    } else {
                        break;
                    }
                }
                break;
            }
        }
        number = Integer.parseInt(number_of_unit);
        return number;

    }

    /**
     * @return the customerBill
     */
    public static double getCustomerBill() {
        return customerBill;
    }

    /**
     * @param aCustomerBill the customerBill to set
     */
    public static void setCustomerBill(double aCustomerBill) {
        customerBill = aCustomerBill;
    }
}
