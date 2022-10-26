package assessment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author larsj
 */
public class Booking {

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final Date date = new Date();
    public String currentDate = formatter.format(date);
    private ArrayList<String> days = new ArrayList<String>();
    private final String file = "./resources/Week.txt";

    static String weekDay = "";
    //String status = "";
    static int limitedSpaces = 0; //default
    private static boolean userBooked = false; //default
    private static boolean full = false; //default

    //============== ### CONSTRUCTOR ### ============== 
    public Booking() {
        getAvailability(); //get data from file
        getDays(this.days); //display data 
    }

    //================ ### METHODS ### ================
    public void getAvailability() {
        try {
            BufferedReader data = new BufferedReader(new FileReader(this.file));
            String line = "";
            while ((line = data.readLine()) != null) { //read each line until the end of file
                this.days.add(line); //add to the list
            }
            data.close(); //close stream
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    //--------------------------------------------------------------------------
    //to output the list contents
    public void getDays(ArrayList<String> list) {
        System.out.println("      Day   | Booking Status | Remaining rooms | You have booked?");
        System.out.println("   --------------------------------------------------------------");
        int counter = 1;
        for (String line : list) {
            System.out.println(counter + ") " + line);
            counter++;
        }
    }

    //--------------------------------------------------------------------------
    //separate the whole string into its significant parts
    public void parseInfo(int index) {
        System.out.println("===================");
        String line = this.days.get(index - 1); //starts 1 less than displayed in options
        try {
            //StringTokenizer st = new StringTokenizer(this.days.get(index), " ):,", false);
            Scanner scan = new Scanner(line);
            weekDay = scan.next();
            StringTokenizer st = new StringTokenizer(weekDay, " :", false); //separate : character
            System.out.println("Weekday: " + st.nextToken());

            limitedSpaces = getNum(line);
            System.out.println("Spaces available: " + limitedSpaces);

            full = isFull(line);
            userBooked = userHasBooked(line);

            System.out.println("User has booked?: " + userBooked);
        } catch (InputMismatchException e) {
            e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            e.getMessage();
            System.out.println("########################");
            System.out.println("\"INDEX OUT OF BOUNDS!\"");
            System.out.println("########################");
            System.out.println("CONTINUING. . .");
        }
    }

    //--------------------------------------------------------------------------
    public int getNum(String s) {
        //clean up string
        StringTokenizer sTok = new StringTokenizer(s, " |");
        Iterator it = sTok.asIterator();

        //iterate through every value
        while (it.hasNext()) {
            String token = sTok.nextToken(); //this token
            if (tryNum(token) == true) {
                return Integer.parseInt(token); //return THIS token, NOT nextToken()
            }
        }
        return 0;
    }

    //This method tries to see if String num is an integer
    public boolean tryNum(String num) {
        try {
            Integer.parseInt(num);
            return true; //parsing to int worked by this point
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //--------------------------------------------------------------------------
    /*
    *String s provides e.g. Monday: Available, 5, false
    *This method will check if the last value is
    * "true" or "false"
     */
    public boolean userHasBooked(String s) {
        //clean up string
        StringTokenizer sTok = new StringTokenizer(s, " |");
        Iterator it = sTok.asIterator();

        //iterate through every value
        while (it.hasNext()) {
            String token = sTok.nextToken();
            if (token.equalsIgnoreCase("true")) {
                return true;
            }
        }
        return false;
    }

    /**
     * userBooked getter
     */
    public static boolean getIsUserBooked() {
        return userBooked;
    }

    //--------------------------------------------------------------------------
    /*
    *String s provides e.g. Monday: Available, 5, false
    *This method will check if the second value is
    * "Available" or "Booked"
     */
    public boolean isFull(String s) {
        StringTokenizer sTok = new StringTokenizer(s, " |");
        String[] stringAry = s.split("|");
        String[] temp = new String[3];

        Iterator it = sTok.asIterator();
        while (it.hasNext()) {
            String token = sTok.nextToken();
            if (token.equalsIgnoreCase("Booked")) {
                return true;
            }
        }
        //else
        return false;
    }

    /**
     * isFull getter
     */
    public static boolean getIsFull() {
        return full;
    }

    //--------------------------------------------------------------------------
    //Main testing of date functions
    /*
    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
    }
     */
}
