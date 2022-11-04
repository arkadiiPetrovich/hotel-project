/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import static Assignment2.Payment.hotelEmail;
import static Assignment2.Payment.hotelPhone;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

/**
 *
 * @author Lars Bosales, 20112844
 */
public class BookingController extends JFrame {

    //========= Fields =========
    private static final int MENU_WIDTH = 300;
    private static final int MENU_LENGTH = 350;

    //Based on MVC model
    private Booking model; //M
    private BookingView view; //V

    //other fields
    //========= Constructor =========
    public BookingController() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); //stop runtime on exit
        setSize(MENU_WIDTH, MENU_LENGTH); //frame size
        this.view = new BookingView(this.model); //instantiate the panel
        add(view); //add to the Frame
        showMenu(); //display menu components

        //========= Action Listeners =========
        //bookingButton
        view.getBookingButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerBookingButton();
            }
        });
        //availabilityButton
        view.getAvailabilityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerAvailabilityButton();
            }
        });
        //currencyButton
        view.getCurrencyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerCurrencyButton();
            }
        });
        //reviewButton
        view.getReviewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerReviewButton();
            }
        });
        //contactButton
        view.getContactButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerContactButton();
            }
        });
        //backButton
        view.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerBackButton();
            }
        });
        //checkReviewButton
        view.getCheckReviewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerCheckReviewButton();
            }
        });
        //backButton
        view.getWriteReviewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerWriteReviewButton();
            }
        });

        //payDetailsButton
        view.getPayDetailsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerPayDetailsButton();
            }
        });

        //recieptButton
        view.getRecieptButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventHandlerRecieptButton();
            }
        });

        //## RadioButtons ##
        //rButton1 (Cash)
        view.getrButton1().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                eventHandlerRButton1();
            }
        });
        //rButton2 (Card)
        view.getrButton2().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                eventHandlerRButton2();
            }
        });
        //rButton3 (Bank Transfer/bt)
        view.getrButton3().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                eventHandlerRButton3();
            }
        });
    }
    //========= Event Handler Methods =========

    //bookingButton
    private void eventHandlerBookingButton() {
        System.out.println("System Message: Proceeding to Booking. . .");
        //Booking frame selection to be cleared 
        try {
            /*
            * Error pane will pop up if either the radio buttons or list are 
            * Null, so both need to be cleared at the same time
             */
            if (view.getRbGroup().getSelection().isSelected()) { //if radio buttons are pressed prior
                view.getRbGroup().clearSelection(); //clear selection
                view.getDayList().clearSelection(); //clear list selection
            }
        } catch (NullPointerException e) {
//            System.out.println("System Message: NullPointerExeption caught");
//            System.out.println("RadioButton selection cleared");
//            System.out.println("Proceeding...");
            e.getMessage();
        }

        //this.model = new Booking(1); //initialise and run methods to proceed with booking
        resetAllComponents(); //hide everything to begin reshaping frame
        setSize(900, 500); //redefine frame size

        //== reveal needed components ==
        view.getBackButton().setVisible(true);
        view.getBackButton().setLocation(30, 400);

        view.getPayDetailsButton().setVisible(true);
        view.getPayDetailsButton().setText("Proceed to Payment");
        view.getPayDetailsButton().setLocation(525, 400);

        //show the radio buttons
        view.getrButton1().setVisible(true);
        view.getrButton1().setLocation(555, 235);

        view.getrButton2().setVisible(true);
        view.getrButton2().setLocation(555, 285);

        view.getrButton3().setVisible(true);
        view.getrButton3().setLocation(555, 335);

        dayAndUnitFrame(); //the rest of the GUI components for booking option
    }

    //availabilityButton
    private void eventHandlerAvailabilityButton() {
        System.out.println("System Message: Viewing Availability...");
        resetAllComponents();
        setSize(450, 350); //resize frame

        //reveal needed components
        view.getLabel2().setVisible(true); //display a different title
        view.getTextArea1().setVisible(true); //text area where list will be
        view.getTextArea1().setEditable(false); //make area uneditable
        view.getBackButton().setVisible(true);
        view.getBackButton().setLocation(130, 250);

        this.model = new Booking(2); //get days list to be output
        view.getTextArea1().setText(model.getTheDays()); //put into text area
    }

    //currencyButton
    private void eventHandlerCurrencyButton() {
        System.out.println("System Message: Proceeding to Currency Converter...");
    }

    //reviewButton
    private void eventHandlerReviewButton() {
        System.out.println("System Message: Proceeding to Reviews...");
        resetAllComponents(); //hide all components
        setSize(300, 200); //resize frame
        //reveal only needed components
        view.getBackButton().setVisible(true); //display back button
        view.getBackButton().setLocation(70, 110);
        view.getCheckReviewButton().setVisible(true);
        view.getWriteReviewButton().setVisible(true);
    }

    //contactButton
    private void eventHandlerContactButton() {
        System.out.println("System Message: Proceeding to Contact Details...");
        resetAllComponents();
        view.getBackButton().setVisible(true); //display back button
        view.getBackButton().setLocation(130, 150);

        //display the titles for Contact details
        view.getLabel3().setVisible(true);
        String contactDetails = "";
        contactDetails += "==========================================";
        contactDetails += "\n                        ### CONTACT US ###";
        contactDetails += "\nEmail: " + hotelEmail;
        contactDetails += "\nPhone: " + hotelPhone;
        contactDetails += "\n==========================================";

        setSize(450, 250); //resize frame
        view.getTextArea2().setVisible(true);
        view.getTextArea2().setText(contactDetails);

    }

    //backButton
    private void eventHandlerBackButton() {
        System.out.println("System Message: Returning to Menu...");
        resetAllComponents(); //reset frame component layout
        showMenu(); //show menu components
        setSize(MENU_WIDTH, MENU_LENGTH); //resize frame back to original
        showMenu();
    }

    //checkReviewButton
    private void eventHandlerCheckReviewButton() {
        System.out.println("System Message: Proceeding to \"Checking Reviews\"...");
        resetAllComponents(); //hide all components
        setSize(530, 350); //resize frame
        //reveal only needed components
        view.getLabel5().setVisible(true); //display title "Current Reviews"
        view.getBackButton().setVisible(true); //display back button
        view.getBackButton().setLocation(170, 250);
        view.getTextArea1().setVisible(true); //text area where reviews will be
        view.getTextArea1().setLocation(25, 30);
        view.getTextArea1().setSize(455, 175);
        Quality reviews = new Quality();
        reviews.getQualityInformation();
        String unitReviews = "";
//        for(int i = 0; i < reviews.Units.size(); i++){
//            unitReviews += reviews.showUnitComments(i +"");
//        }
        view.getTextArea1().setText(unitReviews);

    }

    //writeReviewButton
    private void eventHandlerWriteReviewButton() {
        System.out.println("System Message: Proceeding to \"Write a Review\"...");
    }

    //payDetailsButton
    private void eventHandlerPayDetailsButton() {
        System.out.println("System Message: Proceeding to Payment Details...");
        //userHasInputBooking(); //check all fields are filled/selected correctly
        resetAllComponents();
        setSize(350, 400);

        // == reveal needed components ==
        //reciept button to progress
        view.getRecieptButton().setVisible(true);
        view.getRecieptButton().setLocation(190, 300);

        //return to booking page button
        view.getBookingButton().setText("Back to Booking");
        view.getBookingButton().setVisible(true);
        view.getBookingButton().setLocation(20, 300);
        //First name
        view.getLabel7().setVisible(true);
        view.getLabel7().setText("First Name:");
        view.getLabel7().setLocation(20, 20);
        view.getField4().setVisible(true);
        view.getField4().setLocation(90, 20);
        //last name
        view.getLabel8().setVisible(true);
        view.getLabel8().setText("Last Name:");
        view.getLabel8().setLocation(20, 70);
        view.getField5().setVisible(true);
        view.getField5().setLocation(90, 70);
        //email
        view.getLabel9().setVisible(true);
        view.getLabel9().setText("Email:");
        view.getLabel9().setLocation(50, 120);
        view.getField6().setVisible(true);
        view.getField6().setLocation(90, 120);
        //phone number
        view.getLabel10().setVisible(true);
        view.getLabel10().setText("Phone#");
        view.getLabel10().setLocation(40, 170);
        view.getField7().setVisible(true);
        view.getField7().setLocation(90, 170);

        //bank account number
        view.getLabel1().setVisible(true);
        view.getLabel1().setText("Bank Account Number: (If doing Bank Transfer)");
        view.getLabel1().setSize(300, 30);
        view.getLabel1().setLocation(20, 200);

        //layout as |field| - |field| - |field| for bank acc num
        view.getField1().setVisible(true);
        view.getField1().setSize(60, 30);
        view.getField1().setLocation(30, 240);

        view.getLabel2().setVisible(true);
        view.getLabel2().setText("--");
        view.getLabel2().setLocation(100, 240);

        view.getField2().setVisible(true);
        view.getField2().setSize(60, 30);
        view.getField2().setLocation(120, 240);

        view.getLabel3().setVisible(true);
        view.getLabel3().setText("--");
        view.getLabel3().setLocation(190, 240);

        view.getField3().setVisible(true);
        view.getField3().setSize(60, 30);
        view.getField3().setLocation(210, 240);

    }

    //recieptButton
    private void eventHandlerRecieptButton() {
        System.out.println("System Message: Proceeding to Reciept...");
        resetAllComponents();
        setSize(465, 550);

        //return to menu button
        view.getBackButton().setVisible(true);
        view.getBackButton().setText("Return to Menu");
        view.getBackButton().setLocation(30, 450);

        //return to details button
        view.getPayDetailsButton().setVisible(true);
        view.getPayDetailsButton().setSize(200, 30);
        view.getPayDetailsButton().setText("Return to Payment Details");
        view.getPayDetailsButton().setLocation(200, 450);

        //reciept title
        view.getLabel1().setVisible(true);
        view.getLabel1().setText("## RECIEPT ##");
        view.getLabel1().setLocation(180, 0);

        //textbox
        view.getTextArea1().setVisible(true);
        view.getTextArea1().setLocation(20, 30);
        view.getTextArea1().setSize(400, 400);
        Payment p = new Payment();
        //from previous GUI entry
        Payment.fname = view.getField4().getText();
        Payment.lname = view.getField5().getText();
        Payment.email = view.getField6().getText();
        Payment.phoneNum = view.getField7().getText();
        Payment.accountNum = view.getField1().getText() + "-"
                + view.getField2().getText() + "-"
                + view.getField3().getText();
        view.getTextArea1().setText(p.reciept(menuOptions.payChoice, Payment.email));
    }

    //== Radio Buttons (payment types) ==
    //Cash
    private void eventHandlerRButton1() {
        System.out.println("System Message: \"Cash\" Button selected");
        menuOptions.payChoice = "Cash";
        menuOptions.setPayChoice(); //set pay choice
    }

    //Card
    private void eventHandlerRButton2() {
        System.out.println("System Message: \"Card\" Button selected");
        menuOptions.payChoice = "Card";
        menuOptions.setPayChoice(); //set pay choice
    }

    //BankTranser/bt
    private void eventHandlerRButton3() {
        System.out.println("System Message: \"Bank Transfer\" Button selected");
        menuOptions.payChoice = "Bank Transfer";
        menuOptions.setPayChoice(); //set pay choice
    }
    //========= Methods =========

    private void dayAndUnitFrame() {
        // # left side #
        view.getLabel2().setVisible(true); //display a different title
        view.getTextArea1().setVisible(true); //text area where list will be
        view.getTextArea1().setEditable(false); //make area uneditable

        //Day selection list
        view.getLabel6().setVisible(true);
        view.getLabel6().setText("Select Day: ");
        view.getLabel6().setLocation(30, 250);
        view.getDayList().setVisible(true);

        //get data to display available days
        this.model = new Booking(2); //get days list to be output
        view.getTextArea1().setText(model.getTheDays()); //put into text area

        // # right side #
        view.getLabel4().setVisible(true);
        view.getLabel4().setLocation(600, 0);
        view.getLabel4().setText("## Room/Unit Types ##");
        view.getTextArea2().setVisible(true);
        view.getTextArea2().setSize(400, 175);
        view.getTextArea2().setLocation(460, 30);
        view.getTextArea2().setEditable(false);

        //Unit, # of people selection
        view.getLabel7().setText("Unit# :");
        view.getLabel7().setVisible(true);
        view.getLabel7().setLocation(200, 250);
        view.getField1().setVisible(true);
        view.getField1().setSize(100, 30);
        view.getField1().setLocation(240, 250);

        view.getLabel8().setText("Adults# :");
        view.getLabel8().setVisible(true);
        view.getLabel8().setLocation(190, 300);
        view.getField2().setVisible(true);
        view.getField2().setSize(100, 30);
        view.getField2().setLocation(240, 300);

        view.getLabel9().setText("Children# :");
        view.getLabel9().setVisible(true);
        view.getLabel9().setLocation(180, 350);
        view.getField3().setVisible(true);
        view.getField3().setSize(100, 30);
        view.getField3().setLocation(240, 350);

        //get data to display units info
        Units u = new Units();
        u.downloadUnitsInformation();
        String unitReviews = u.showAllUnits();
        view.getTextArea2().setText(unitReviews);

        //Payment Type selection
        view.getLabel10().setVisible(true);
        view.getLabel10().setLocation(600, 215);
        view.getLabel10().setText("Select Payment Type:");

        view.getLabel1().setVisible(true);
        view.getLabel1().setLocation(590, 245);
        view.getLabel1().setText(" = = = = = Cash = = = = = =");

        view.getLabel3().setVisible(true);
        view.getLabel3().setLocation(590, 295);
        view.getLabel3().setText(" = = = = = Card = = = = = =");

        view.getLabel5().setVisible(true);
        view.getLabel5().setLocation(590, 345);
        view.getLabel5().setText("= = = Bank Transfer = = =");
    }

    /*
    * Hide all components in order to reset frame and 
    * proceed with enabling only the necessary components
     */
    public void resetAllComponents() {

        //Buttons
        view.getBookingButton().setVisible(false);
        view.getAvailabilityButton().setVisible(false);
        view.getCurrencyButton().setVisible(false);
        view.getReviewButton().setVisible(false);
        view.getContactButton().setVisible(false);
        view.getBackButton().setVisible(false);
        view.getCheckReviewButton().setVisible(false);
        view.getWriteReviewButton().setVisible(false);
        view.getPayDetailsButton().setVisible(false);
        view.getRecieptButton().setVisible(false);

        //Labels
        view.getLabel1().setVisible(false);
        view.getLabel2().setVisible(false);
        view.getLabel3().setVisible(false);
        view.getLabel4().setVisible(false);
        view.getLabel5().setVisible(false);
        view.getLabel6().setVisible(false);
        view.getLabel7().setVisible(false);
        view.getLabel8().setVisible(false);
        view.getLabel9().setVisible(false);
        view.getLabel10().setVisible(false);

        //Text areas
        view.getTextArea1().setVisible(false);
        view.getTextArea2().setVisible(false);

        //Text fields
        view.getField1().setVisible(false);
        view.getField2().setVisible(false);
        view.getField3().setVisible(false);
        view.getField4().setVisible(false);
        view.getField5().setVisible(false);
        view.getField6().setVisible(false);
        view.getField7().setVisible(false);

        //JList
        view.getDayList().setVisible(false);

        //Radio buttons
        view.getrButton1().setVisible(false);
        view.getrButton2().setVisible(false);
        view.getrButton3().setVisible(false);
    }

    //show menu components 
    public void showMenu() {
        //welcome title
        view.getLabel1().setVisible(true);
        view.getLabel1().setText("## Welcome to Dreamland Hotels! ##");
        view.getLabel1().setLocation(35, 15);
        view.getLabel1().setSize(250, 20);
        //booking
        view.getBookingButton().setVisible(true);
        view.getBookingButton().setText("Create Booking");
        view.getBookingButton().setLocation(65, 50);
        view.getBookingButton().setSize(150, 30);
        //availability
        view.getAvailabilityButton().setVisible(true);
        view.getAvailabilityButton().setText("View Availability");
        view.getAvailabilityButton().setLocation(65, 90);
        view.getAvailabilityButton().setSize(150, 30);
        //currency
        view.getCurrencyButton().setVisible(true);
        view.getCurrencyButton().setText("Currency Converter");
        view.getCurrencyButton().setLocation(65, 130);
        view.getCurrencyButton().setSize(150, 30);
        //review
        view.getReviewButton().setVisible(true);
        view.getReviewButton().setText("Reviews");
        view.getReviewButton().setLocation(65, 170);
        view.getReviewButton().setSize(150, 30);
        //contacts
        view.getContactButton().setVisible(true);
        view.getContactButton().setText("Contact Details");
        view.getContactButton().setLocation(65, 210);
        view.getContactButton().setSize(150, 30);
    }

    //check day input from user in Booking GUI
    public void userHasInputBooking() throws NullPointerException {
        boolean fieldIsEmpty;
        fieldIsEmpty = checkTextFieldBooking();
        try {
            Booking.weekDay = Booking.getWeekDay((String) view.getDayList().getSelectedValue());
            //check that both fields and day are selected/input for booking
            if (fieldIsEmpty == true || Booking.weekDay == null) {
                throw new NullPointerException(); //generate null error
            }
        } catch (ClassCastException e) {
            System.out.println("System Message: ClassCastException caught");
            System.out.println("Coninuing. . .");
            e.getMessage();
        } catch (NullPointerException e) {
            if (fieldIsEmpty == false) { //text fields are NOT empty, but day selection IS empty
                JOptionPane.showMessageDialog(null, "Error! Please select a day!"); //display error message pane
                System.out.println("System Message: Error! Please select a day!");
                view.getRbGroup().clearSelection(); //reset selection
            } else { //a field is empty
                JOptionPane.showMessageDialog(null, "Error! Please select a day and\n fill in text fields with INTEGERS ONLY!"); //display error message pane
                System.out.println("System Message: Error! Please select a day and  fill in text fields with INTEGERS ONLY!");
                view.getRbGroup().clearSelection(); //reset selection
            }
            e.getMessage();
        }
    }

    public boolean checkTextFieldBooking() {
        //All three textfields MUST be given input in GUI
        if (view.getField1().getText().isEmpty() == false
                && view.getField2().getText().isEmpty() == false
                && view.getField3().getText().isEmpty() == false) {
            //check if the inputs are integers
            if (model.tryNum(view.getField1().getText()) == true
                    && model.tryNum(view.getField2().getText()) == true
                    && model.tryNum(view.getField3().getText()) == true) {
                return false; //all fields are filled AND are integers
            } else {
                return true; //fields are filled, BUT one/more are NOT integers
            }
        } else {
            return true; //one or more of the three fields are empty
        }
    }

    //========= Main =========
    public static void main(String[] args) {
        JFrame myFrame = new BookingController(); //create the frame
        myFrame.setVisible(true);
    }
}
