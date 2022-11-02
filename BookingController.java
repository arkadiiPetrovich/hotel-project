/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

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
        System.out.println("Booking initialised, executing. . .");
        this.model = new Booking(1); //initialise and run methods to proceed with booking
    }

    //availabilityButton
    private void eventHandlerAvailabilityButton() {
        hideMenuButtons();
        view.getLabel1().setVisible(false); //hide welcome title
        view.getLabel2().setVisible(true); //display a different title
        
        view.getTextArea1().setVisible(true); //text area where list will be
        view.getTextArea1().setEditable(false);
        
        view.getBackButton().setVisible(true);
        setSize(450, 350); //resize frame
        this.model = new Booking(2); //get days list to be output
        view.getTextArea1().setText(model.getTheDays()); //put into text area
    }

    //currencyButton
    private void eventHandlerCurrencyButton() {

    }

    //reviewButton
    private void eventHandlerReviewButton() {

    }

    //contactButton
    private void eventHandlerContactButton() {

    }
    
    //backButton
    private void eventHandlerBackButton(){
        view.getLabel1().setVisible(true); //show welcome title
        view.getLabel2().setVisible(false); //hide a different title
        
        view.getTextArea1().setVisible(false); //text area where list will be
        
        view.getBackButton().setVisible(false);
        setSize(MENU_WIDTH, MENU_LENGTH); //resize frame back to original
        showMenu();
    }
    //========= Methods =========
    //hide menu buttons to proceed with other functions
    public void hideMenuButtons() {
        view.getBookingButton().setVisible(false);
        view.getAvailabilityButton().setVisible(false);
        view.getCurrencyButton().setVisible(false);
        view.getReviewButton().setVisible(false);
        view.getContactButton().setVisible(false);
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
