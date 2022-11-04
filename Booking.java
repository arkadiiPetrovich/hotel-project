package Assignment2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
    import static Assignment2.CUIMain.createBooking;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import static Assignment2.CUIMain.setPayChoice;

/**
 *
 * @author Lars Bosales
 */
public class Booking {

    /**
     * @return the theDays
     */
    public String getTheDays() {
        return daysList;
    }

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final Date date = new Date();
    public String currentDate = formatter.format(date);
    private ArrayList<String> days = new ArrayList<String>();
    private final String file = "./resources/Week.txt";

    static String weekDay = "";
    private String daysList;

    private static String available = ""; //default
    private static int limitedSpaces = 0; //default
    private static boolean userBooked = false; //default
    private static boolean full = false; //default

    //============== ### CONSTRUCTOR ### ============== 
    public Booking(int option) {
        if (option == 1) {
            startBooking(); //proceed with booking
        } else if (option == 2) {
            this.daysList = "";
            getAvailabilityBooking(); //show availability
        }
    }
    public Booking(){
        //default
    }

    //================ ### METHODS ### ================
    public void startBooking() {
        try {
            createBooking(); //booking
            setPayChoice(); //transaction
        } catch (InputMismatchException e) {
            e.getMessage();
            System.out.println("Invalid input!");
        }
    }

    public void getAvailabilityBooking() {
        getAvailabilityFile(); //get data from file
        this.daysList = getDays(this.days); //data to display
    }

    public void getAvailabilityFile() {
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

    public void updateAvailability(int index) {
        String toEditLine = this.days.get(index - 1); //this is the line that will be updated

        PrintWriter pw = null; //printWriter object
        try {
            BufferedReader data = new BufferedReader(new FileReader(this.file)); //create a input stream
            String txtLine = "";
            while ((txtLine = data.readLine()) != null) { //read each line until the end of file
                if (txtLine.equalsIgnoreCase(toEditLine)) { //look for the matching line
                    pw = new PrintWriter(new FileOutputStream(txtLine)); //new printWriter output stream for THIS line
                    pw.print(getWeekDay(toEditLine) + " | " + getAvailable() + "                | "
                            + getLimitedSpaces() + " |            " + getIsUserBooked()); //update THIS LINE in file

                }
            }
            data.close(); //close reader 
            pw.close(); //close writer 
        } catch (FileNotFoundException ex) {
            System.out.println("File not Found!");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    //--------------------------------------------------------------------------
    //to output the list contents
    public void getDays1(ArrayList<String> list) {
        System.out.println("      Day   | Booking Status | Remaining rooms | You have booked?");
        System.out.println("   --------------------------------------------------------------");
        int counter = 1;
        for (String line : list) {
            System.out.println(counter + ") " + line);
            counter++;
        }
        System.out.println("   --------------------------------------------------------------");
    }
    public String getDays(ArrayList<String> list) {
        String out = "";
        int counter = 1;
        out += "      Day   | Booking Status | Remaining rooms | You have booked?";
        out += "\n   --------------------------"
                + "--------------------------------------------------------------";

        for (String line : list) {
            out += "\n     " + counter + ") " + line;
            counter++;
        }
        out += " \n  --------------------------"
                + "--------------------------------------------------------------";
        return out;
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

            setLimitedSpaces(getNum(line));
            System.out.println("Spaces available: " + getLimitedSpaces());

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
        //Iterator it = sTok.asIterator();

        //iterate through every value
        while (sTok.hasMoreTokens()) {
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
        //Iterator it = sTok.asIterator();

        //iterate through every value
        while (sTok.hasMoreTokens()) {
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
        //String[] temp = new String[3];

        //Iterator it = sTok.asIterator();
        while (sTok.hasMoreTokens()) {
            String token = sTok.nextToken();
            if (token.equalsIgnoreCase("Booked")) {
                setAvailable("Booked");
                return true;
            }
        }
        //else
        setAvailable("Available");
        return false;
    }

    /**
     * isFull getter
     */
    public static boolean getIsFull() {
        return full;
    }

    //--------------------------------------------------------------------------
    enum weekDays {
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday"),
        SUNDAY("Sunday");

        public String weekDay = "";

        private weekDays(String day) {
            this.weekDay = day;
        }
    }

    //gets weekday value
    public static String getWeekDay(String s) {
        //clean up string
        StringTokenizer sTok = new StringTokenizer(s, " |");
        //Iterator it = sTok.asIterator();
        String day = "";

        //iterate through every value
        while (sTok.hasMoreTokens()) {
            String token = sTok.nextToken(); //this token
            for (weekDays x : weekDays.values()) {
                if (token.equalsIgnoreCase(x.name())) { //check if days are matching strings
                    day = token;
                }
            }
        }
        return day;
    }

    //--------------------------------------------------------------------------
    /**
     * limitedSpaces getter
     */
    public static int getLimitedSpaces() {
        return limitedSpaces;
    }

    /**
     * @param aLimitedSpaces the limitedSpaces to set
     */
    public static void setLimitedSpaces(int aLimitedSpaces) {
        limitedSpaces = aLimitedSpaces;
    }

    /**
     * available getter
     */
    public static String getAvailable() {
        return available;
    }

    /**
     * @param aAvailable the available to set
     */
    public static void setAvailable(String aAvailable) {
        available = aAvailable;
    }
}
