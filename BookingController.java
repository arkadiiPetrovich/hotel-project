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
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

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

    //========= Constructor =========
    public BookingController() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); //stop runtime on exit
        setSize(MENU_WIDTH, MENU_LENGTH); //frame size
        this.view = new BookingView(this.model); //instantiate the panel
        add(view); //add to the Frame

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
    }
    //========= Event Handler Methods =========

    //bookingButton
    private void eventHandlerBookingButton() {
        System.out.println("System Message: Booking initialised, executing. . .");
        this.model = new Booking(1); //initialise and run methods to proceed with booking
    }

    //availabilityButton
    private void eventHandlerAvailabilityButton() {
        System.out.println("System Message: Viewing Availability...");
        hideAllComponents();
        view.getLabel1().setVisible(false); //hide welcome title
        view.getLabel2().setVisible(true); //display a different title

        view.getTextArea1().setVisible(true); //text area where list will be
        view.getTextArea1().setEditable(false);

        view.getBackButton().setVisible(true);
        view.getBackButton().setLocation(130, 250);
        setSize(450, 350); //resize frame
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
    }

    //contactButton
    private void eventHandlerContactButton() {
        System.out.println("System Message: Proceeding to Contact Details...");
        hideAllComponents();
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
        hideAllComponents(); //reset frame component layout
        showMenu(); //show menu components
        setSize(MENU_WIDTH, MENU_LENGTH); //resize frame back to original
        showMenu();
    }
    //========= Methods =========

    /*
    * Hide all components in order to reset frame and 
    * enable only the necessary components
     */
    public void hideAllComponents() {
        view.getBookingButton().setVisible(false);
        view.getAvailabilityButton().setVisible(false);
        view.getCurrencyButton().setVisible(false);
        view.getReviewButton().setVisible(false);
        view.getContactButton().setVisible(false);
        view.getBackButton().setVisible(false);
        view.getLabel1().setVisible(false);
        view.getLabel2().setVisible(false);
        view.getLabel3().setVisible(false);
        view.getLabel4().setVisible(false);
        view.getTextArea1().setVisible(false);
        view.getTextArea2().setVisible(false);

    }

    //show menu buttons 
    public void showMenu() {
        view.getLabel1().setVisible(true);
        view.getBookingButton().setVisible(true);
        view.getAvailabilityButton().setVisible(true);
        view.getCurrencyButton().setVisible(true);
        view.getReviewButton().setVisible(true);
        view.getContactButton().setVisible(true);
    }

    //========= Main =========
    public static void main(String[] args) {
        JFrame myFrame = new BookingController(); //create the frame
        myFrame.setVisible(true);
    }
}
