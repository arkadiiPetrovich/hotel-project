package Assignment2;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assigment1;
/**
 *
 * @author Dmitry Kirov + Lars Bosales
 */
public class CUIMain {

    static Scanner scan = new Scanner(System.in);
    static int choice = 0; //default
    //variables for booking + view booking
    static int unitChoice;
    static int dayChoice;
    static int adults;
    static int children;
    static BookingView bkView = new BookingView(new Booking());

    //variables for payment
    static String payChoice;

    //============== ### MAIN ### ============== 
    public static void main(String[] args) {

        //User Input Stage
        int flag = 0;
        do {
            menu(); //Display menu to get user's choice            
            flag = options(choice);
            /*
            *The options() method returns 1 when proceeding past the loop is necessary
            *else, 0 is returned and the options() will repeat itself
             */
        } while (flag == 0);

        //Payment Stage + includes reciept stage
        Price price = new Price();
    }

    private static void createUnits() {
        Units units = new Units();
        units.downloadUnitsInformation();
        units.showAllUnits();
    }

    //==========================================================================
    public static int menu() {
        System.out.println("\n==================================");
        System.out.println("### WELCOME TO DREAMLAND HOTELS ###");
        System.out.println("1) Create a Booking");
        System.out.println("2) View Availability");
        System.out.println("3) Currency Conversion");
        System.out.println("4) Leave a Review!");
        System.out.println("5) Contact Us!");
        System.out.println("-----------------------------------");
        System.out.println("What would you like to do? (Type a number between 1-5)");
        try {
            choice = getInput();
        } catch (InputMismatchException e) {
            e.getMessage();
        }
        return choice;
    }

    /*
    *This method returns 1 to move past loop but returns 0 when recursive
    *calling, via do-while loop, of options() is neccessary
     */
    public static int options(int choice) {
        switch (choice) {
            case 1: { //Create a booking
                try {
                    createBooking();
                    makePayment();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input!");
                }
                return 1; //continue 
            }
            //==================================================================
            case 2: { //View availability
                //needs to check if booking exists (under a user's name?)
                System.out.println("\n## You have chosen to view availabilities ##");
                //System.out.print("Your name: ");
                //userName = scan.next();

                //get current bookings
                Booking book = new Booking(2); //get info and display it
                return 0; //repeat
            }
            //==================================================================
            case 3: { //Currency Conversion
                System.out.println("\n## You have chosen currency conversion ##");
                Convert();
                return 0; //repeat 
            }
            //==================================================================
            case 4: { //Leave a review
                System.out.println("\n## You have chosen to leave a review! ##");
                qualityMethod();
                return 0; //repeat 
            }
            //==================================================================
            case 5: { //Contact us
                //print out contact details
                Payment.hotelContactDetails();
                return 0; //repeat 
            }
            //==================================================================
            default: { // choice = 0 || choice > 5
                System.out.println("Did you make choice? between 1 - 6?");
                System.out.println("Try Again");
                return 0; //repeat 
            }
        }
    }

    //==========================================================================
    public static void createBooking() {
        Units u = new Units();
        createUnits(); //get unit info then show display it

        //Getting unit number from user in GUI
        try {
            String unitNum = bkView.getField1().getText(); //get from user in GUI
            unitNum = unitNum.trim(); //take out whitespace if any
            unitChoice = Integer.parseInt(unitNum);
        } catch (NumberFormatException e) {
            e.getMessage();
        }

        u.selectedUnit(unitChoice); //display the selected unit in CUI

        //Getting # of Adults and Children from GUI
        //adult
        try {
            String adultNum = bkView.getField2().getText(); //get from user in GUI
            adultNum = adultNum.trim(); //take out whitespace if any
            unitChoice = Integer.parseInt(adultNum);
        } catch (NumberFormatException e) {
            e.getMessage();
        }
        //children
        try {
            String childNum = bkView.getField3().getText(); //get from user in GUI
            childNum = childNum.trim(); //take out whitespace if any
            unitChoice = Integer.parseInt(childNum);
        } catch (NumberFormatException e) {
            e.getMessage();
        }

        System.out.println("Getting prices . . .");
        Price.chosenUnitPrice(unitChoice, adults, children);
    }

    //==========================================================================
    public static void makePayment() {
        System.out.println("==========================");
        System.out.println("How would you like to pay?");
        System.out.println("Cash, Card, or Bank Transfer? (type bt for Bank Transfer)");
        //scan.nextLine(); //flush buffer as changing from int to String input
        payChoice = (String) bkView.getDayList().getSelectedValue(); //get from GUI
        Payment p = new Payment(payChoice); //initialise payment type value
    }

    //==========================================================================
    //getInput()makes sure input is a number, but makes exception for 'x'
    public static int getInput() {
        int answer = 0;
        String temp = "";
        System.out.println("Enter a number (or type x to quit): ");
        try {
            temp = scan.nextLine();
            answer = Integer.parseInt(temp);
            //answer = scan.nextInt();
        } catch (InputMismatchException | NumberFormatException e) {
            //x is the quit key
            if ((temp.equalsIgnoreCase("x"))) {
                System.out.println("Thank you, come again!");
                System.exit(0); //terminate program
            }
            System.out.println("Something went wrong, invalid input");
            e.getMessage();
        }
        return answer;
    }

    //==========================================================================
    private static void qualityMethod() {
        Quality q = new Quality();
        q.getQualityInformation();
        Units units = new Units();
        units.downloadUnitsInformation();

        while (true) {
            units.showAllUnits();
            System.out.println("Do you need check or write review?");
            System.out.println("Enter check to see review and write to create own or x to exit.");
            System.out.print("check or write: ");
            String write_or_check = scan.nextLine();
            String unit_num = "";

            if (write_or_check.equals("x")) {
                return;
            } else if (write_or_check.equals("check")) {
                System.out.print("Enter unit number or x to exit: ");
                unit_num = scan.nextLine();
                unit_num = unit_num.trim();
                if (unit_num.equals("x")) {
                    return;
                } else if (units.checkSelectedUnit(unit_num) == true) {
                    q.showUnitComments(unit_num);
                } else {
                    System.out.println("Something wrong. Try again.");
                }
            } else if (write_or_check.equals("write")) {
                System.out.print("Enter unit number or x to exit: ");
                unit_num = scan.nextLine();
                unit_num = unit_num.trim();
                if (unit_num.equals("x")) {
                    return;
                } else if (units.checkSelectedUnit(unit_num) == true) {
                    units.selectedUnit(Integer.parseInt(unit_num));
                    System.out.print("Enter your fullname:");
                    String user_name = scan.nextLine();
                    int rating = 0;
                    try {
                        System.out.print("Enter reting 1 to 5 or 0 to exit:");
                        rating = scan.nextInt();
                        scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Wrong input or format");
                    }
                    if (rating == 0) {
                        return;
                    } else if (rating > 0 && rating <= 5) {

                        System.out.print("Enter your comment: ");
                        String comment = scan.nextLine();
                        comment = comment.replaceAll("@", "");
                        comment = comment.replaceAll("\\(", "");
                        comment = comment.replaceAll("\\)", "");
                        comment = comment.trim() + " (" + user_name.trim() + ")" + String.valueOf(rating).trim();
                        q.addComment(unit_num, comment);
                        break;
                    } else {
                        System.out.println("Wrong rating input.");
                    }

                } else {
                    System.out.println("Unit not exist.");
                }
            } else {
                System.out.println("Wrong inpur. Enter check, write or x.");
            }
        }
    }

    //==========================================================================
    public static void Convert() {
        Units unit = new Units();
        unit.downloadUnitsInformation();
        unit.showAllUnits();
        while (true) {
            try {
                System.out.println("Enter number of adults: ");
                int adults = scan.nextInt();//add exit option
                System.out.println("How many childs: ");
                int childs = scan.nextInt();//add exit option
                boolean find = unit.recommendedUnit(adults, childs);
                if (find == true) {
                    unit.showAbleToBookUnits();
                    System.out.println("----------------------------");
                    System.out.println("Which unit you like to book?");

                    int unit_number = scan.nextInt();//add exit option

                    boolean check = unit.checkSelectedUnit(String.valueOf(unit_number));
                    if (check == true) {
                        System.out.println("\nYou have chose unitÃ‘Å½");

                        Price.chosenUnitPrice(unit_number, adults, childs);
                        scan.nextLine();
                        return;
                    } else {
                        scan.nextLine();
                        System.out.println("You are not able to choose that unit.");
                        return;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a whole number.");
                scan.next();
            }
        }
    }
}
