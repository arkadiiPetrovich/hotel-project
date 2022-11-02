package Assignment2;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Lars Bosales
 */
public class Payment implements Transaction {

    //================ ### FIELDS ### =================
    //Constants
    protected final int MIN = 100000000;
    protected final int MAX = 999999999;
    public static final int PREFIX_SIZE = 6;
    public static final int BODY_SIZE = 7;
    public static final int SUFFIX_SIZE = 3;

    //Objects + Declarations
    ArrayList<String> paymentTypes;
    Units u = new Units();
    Booking book = new Booking(2);
    Random rand = new Random();
    //int confirmationID = rand.nextInt(MIN, MAX); //confirmation ID
    int confirmationID = rand.nextInt(MAX); //confirmation ID
    static Scanner scan = new Scanner(System.in);

    //General fields
    static String fname, lname, email, phoneNum;
    String payChoice = "";
    static String hotelEmail = "dreamlandhotels@goldenterprises.com";
    static String hotelPhone = "022 123 4567";

    //Fields for "Card"
    static String bank, accountNum;
    static int expMonth, expYear;

    //Fields for "Cash"
    static double total = Price.getCustomerBill(); //get calculated price from Price class
    static double cashToPay;
    boolean needTransport;

    //Fields for "Bank Transfer"
    protected static int prefix = 0; //6 digits
    protected static int body = 0; //7 digits
    protected static int suffix = 0; //2-3 digits

    //============== ### CONSTRUCTOR ### ============== 
    public Payment(String userPaymentChoice) {
        paymentTypes(); //initialise the possible payment types
        setPaymentType(userPaymentChoice);

    }

    //================ ### METHODS ### ================
    @Override
    public final void paymentTypes() {
        //initialise array list
        paymentTypes = new ArrayList<String>();
        paymentTypes.add("Card");
        paymentTypes.add("Cash");
        paymentTypes.add("Bank Transfer");
        paymentTypes.add("bt"); //abbreviation for Bank Transfer as an extra option
    }

    //================================================
    @Override
    public final void setPaymentType(String choice) {
        for (int i = 0; i < paymentTypes.size(); i++) {
            //look for the matching payment type from the list
            if (paymentTypes.get(i).equalsIgnoreCase(choice)) {
                this.payChoice = paymentTypes.get(i); //set the user's payment choice to it
                break; //exit loop
            }
        }
    }

    //================================================
    @Override
    public final String getPaymentType() {
        return this.payChoice;
    }

    //================================================
    @Override
    public final void pay() {
        switch (getPaymentType()) {
            case "Card": { //Card
                System.out.println("## You have chosen Card ##");
                try {
                    askUserDetails();
                    askTransport();
                    System.out.println("(Press enter to continue)");
                    scan.nextLine(); //'flush' scanner to move from char to String input
                    askBankDetails();
                    scan.nextLine(); //flush again
                } catch (InputMismatchException e) {
                    e.getMessage();
                    System.out.println("Somethiing went wrong! (Card)");
                }
                //Once all details have been recieved, create the "Card" object
                Card card;
                card = new Card(fname, lname, accountNum, bank, expMonth, expYear);
                book.updateAvailability(CUIMain.dayChoice); //update txt file
                
                //Transaction summary
                reciept("Card", email);
                card.getCardDetails(); //display card details
                thankYouMsg();
                hotelContactDetails();
                break;
            }

            //------------------------------------------------------------------
            case "Cash": { //Cash
                System.out.println("## You have chosen Cash ##");
                try {
                    System.out.println("Are you a tourist from the Airport? Type y for yes or n for no");
                    char isTourist = scan.next().charAt(0);
                    System.out.println("(Press enter to continue)");
                    scan.nextLine(); //'flush' scanner to move from char to String input
                    if (isTourist == 'y' || isTourist == 'Y') {
                        askTransport();
                        System.out.println("Enter the amount you will be paying: ");
                        cashToPay = scan.nextDouble();
                        System.out.println("(Press enter to continue)");
                        scan.nextLine(); //'flush' scanner to move from double to String input
                        askUserDetails();
                    } else if (isTourist == 'n' || isTourist == 'N') {
                        askUserDetails();
                    }
                } catch (InputMismatchException e) {
                    e.getMessage();
                    System.out.println("Something went wrong! (Cash)");
                }
                //Create the cash object
                Cash cash = new Cash(fname, lname, cashToPay);
                book.updateAvailability(CUIMain.dayChoice); //update txt file

                //Transaction summary
                reciept("Cash", email); //display the general summary of the transaction
                cash.getCashDetails();
                thankYouMsg();
                hotelContactDetails();
                break;
            }

            //------------------------------------------------------------------
            case "Bank Transfer": { //Bank Transer
                System.out.println("## You have chosen Bank Transfer ##");
                try {
                    askUserDetails();
                    askTransport();
                    System.out.println("(Press enter to continue)");
                    scan.nextLine(); //'flush' scanner to move from char to String input
                    askBankDetails();
                    scan.nextLine(); //flush again
                } catch (InputMismatchException e) {
                    e.getMessage();
                    System.out.println("Something went wrong (BT)");
                }
                //Create Bank Transfer object
                BankTransfer bt = new BankTransfer(fname, lname, accountNum, bank);
                book.updateAvailability(CUIMain.dayChoice); //update txt file

                //Transaction summary
                reciept("Bank Transfer", email);
                bt.getTransferDetails();
                thankYouMsg();
                hotelContactDetails();
                break;
            }
            //==================================================================
            case "bt": { //Bank Transer (alias)
                System.out.println("## You have chosen Bank Transfer ##");
                try {
                    askUserDetails();
                    askTransport();
                    System.out.println("(Press enter to continue)");
                    scan.nextLine(); //'flush' scanner to move from char to String input
                    askBankDetails();
                    scan.nextLine();// flush again
                } catch (InputMismatchException e) {
                    e.getMessage();
                    System.out.println("Something went wrong (BT)");
                }
                //Create Bank Transfer object
                BankTransfer bt = new BankTransfer(fname, lname, accountNum, bank);
                book.updateAvailability(CUIMain.dayChoice); //update txt file
                
                //Transaction summary
                reciept("Bank Transfer", email);
                bt.getTransferDetails();
                thankYouMsg();
                hotelContactDetails();
                break;
            }
            default: {
                System.out.println("## An error occured with payment type ##");
            }
        }
    }

    //================================================
    /*
    * Summary of the GENERAL transaction details
     */
    @Override
    public void reciept(String paymentType, String email) {
        System.out.println("\n### R E C E I P T ###");
        System.out.println("DATE: " + book.currentDate);
        System.out.println("-- CUSTOMER DETAILS --");
        System.out.println("Name: " + fname + " " + lname);
        System.out.println("Email: " + email);
        System.out.println("Phone No# " + phoneNum);
        System.out.println("Transport required? " + needTransport);

        //------------------------------------------
        //need reference for what was booked
        System.out.println("# BOOKING DETAILS #");
        System.out.println(u.returnSelectedUnit(CUIMain.unitChoice));
        //need reference for unit prices with Dmitry
        System.out.println("TOTAL: $" + String.format("%.2f", total) + " NZD");
        //------------------------------------------
        System.out.println("# CONFIRMATION ID #");
        System.out.println(confirmationID);
        System.out.println("PAYMENT TYPE: " + paymentType);
        System.out.println("Bank: " + bank);
        System.out.println("Account no. " + accountNum);
        System.out.println("Hotel Bank no. " + BankTransfer.getHotelBankNum());
    }

    //================================================
    /*
    *These methods ask the user for specific types of input
     */
    public boolean askTransport() {
        System.out.println("Do you need a shuttle/transportation?");
        System.out.println("Type y for \"Yes\" and n for \"No\"");
        char choice = scan.next().charAt(0);
        if (choice == 'y' || choice == 'Y') {
            System.out.println("Answer recieved.");
            System.out.println("We will arrange transport for you and contact you soon.");
            needTransport = true;
            return false; //they don't need transportation
        } else if (choice == 'n' || choice == 'N') {
            System.out.println("Answer recieved.");
            needTransport = false;
            return true; //they need transportation
        }
        needTransport = false;
        return false; //else
    }

    //================================================
    public static void askUserDetails() {
        System.out.println("First Name: ");
        fname = scan.nextLine();
        System.out.println("Last Name Initial(s): ");
        lname = scan.nextLine();
        System.out.println("Email: ");
        email = scan.nextLine();
        System.out.println("Phone No# ");
        phoneNum = scan.nextLine();
    }

    //================================================
    public static void askBankDetails() {
        System.out.println("Bank: ");
        bank = scan.nextLine();
        System.out.println("Account Number: ");
        askAccountNum();
        while (expMonth > 12) {
            System.out.println("Expiry Month: ");
            expMonth = scan.nextInt();
        }
        while (expYear > 99) {
            System.out.println("Expiry Year: ");
            expYear = scan.nextInt();
        }
    }

    //================================================
    public static void askAccountNum() {
        String userAccPrefix = "";
        String userAccBody = "";
        String userAccSuffix = "";
        /*
        *Continue looping through each question until the right amount of digits 
        *have been input for each value
         */
        //6 digits
        while (userAccPrefix.length() < PREFIX_SIZE
                || userAccPrefix.length() > PREFIX_SIZE) {
            System.out.println("Enter the Bank code (6 digits)");
            userAccPrefix = scan.nextLine();
        }
        //7 digits
        while (userAccBody.length() < BODY_SIZE
                || userAccBody.length() > BODY_SIZE) {
            System.out.println("Enter the body of the account number (7 digits)");
            userAccBody = scan.nextLine();
        }
        //2-3 digits
        while (userAccSuffix.length() < 2
                || userAccSuffix.length() > SUFFIX_SIZE) {
            System.out.println("Enter the suffix code (2 or 3 digits)");
            userAccSuffix = scan.nextLine();
        }
        //combine all Strings to create complete account number
        Payment.accountNum = userAccPrefix + "-" + userAccBody + "-" + userAccSuffix;
        System.out.println("User Account Number:");
        System.out.println(Payment.accountNum);
    }

    //================================================
    /*
    *Default Thank you message at the end of a transaction
     */
    public void thankYouMsg() {
        System.out.println("Details recieved, Booking created!\n");
        System.out.println("====================================\n");
        System.out.println("Confirmation email being sent to ---> " + email);
        System.out.println("Please bring the sent email (printed or not) to\n the front desk"
                + " upon your arrival so we can confirm\n your placement,"
                + " we look forward to seeing you soon!");
        System.out.println(" - Dreamland Hotels\n");
    }

    //================================================
    //Simple contact details to display at end of transactions
    public static void hotelContactDetails() {
        System.out.println("==========================================");
        System.out.println("\n          ### CONTACT US ###");
        System.out.println("Email: " + hotelEmail);
        System.out.println("Phone: " + hotelPhone);
        System.out.println("\n==========================================");
    }
}
