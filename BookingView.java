/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Lars Bosales, 20112844
 */
public class BookingView extends JPanel {

    //========= Fields =========
    private Booking model;

    //Buttons
    private JButton bookingButton;
    private JButton availabilityButton;
    private JButton currencyButton;
    private JButton reviewButton;
    private JButton contactButton;

    //Labels
    private JLabel label1;
    private JLabel label2;
    //TextFields

    //========= Constructor =========
    public BookingView(Booking model) { //will initialise the components
        //initialise
        this.model = model;
        setLayout(null);

        //## Create the components and then add to the panel ##
        
        //===== Buttons =====
        //bookingButton attributes
        bookingButton = new JButton("Create Booking");
        bookingButton.setLocation(65, 50);
        bookingButton.setSize(150, 30);
        add(bookingButton);

        //availabilityButton attributes
        availabilityButton = new JButton("View Availability");
        availabilityButton.setLocation(65, 90);
        availabilityButton.setSize(150, 30);
        add(availabilityButton);
        
        //currencyButton attributes
        currencyButton = new JButton("Currency Converter");
        currencyButton.setLocation(65, 130);
        currencyButton.setSize(150, 30);
        add(currencyButton);
        
        //reviewButton attributes
        reviewButton = new JButton("Reviews");
        reviewButton.setLocation(65, 170);
        reviewButton.setSize(150, 30);
        add(reviewButton);
        
        //contactButton attributes
        contactButton = new JButton("Contact Details");
        contactButton.setLocation(65, 210);
        contactButton.setSize(150, 30);
        add(contactButton);
        
        //===== Labels =====
        label1 = new JLabel("## Welcome to Dreamland Hotels! ##");
        label1.setLocation(35, 15);
        label1.setSize(250, 20);
        add(label1);
        
        label2 = new JLabel(" ## Refer to CUI after clicking an option ##");
        label2.setLocation(25, 250);
        label2.setSize(300, 20);
        add(label2);
    }
    
    //## Get and Set methods ##
    /**
     * @return the model
     */
    public Booking getModel() {
        return model;
    }

    /**
     * @return the bookingButton
     */
    public JButton getBookingButton() {
        return bookingButton;
    }

    /**
     * @return the availabilityButton
     */
    public JButton getAvailabilityButton() {
        return availabilityButton;
    }

    /**
     * @return the currencyButton
     */
    public JButton getCurrencyButton() {
        return currencyButton;
    }

    /**
     * @return the reviewButton
     */
    public JButton getReviewButton() {
        return reviewButton;
    }

    /**
     * @return the contactButton
     */
    public JButton getContactButton() {
        return contactButton;
    }

    /**
     * @return the welcomeTitle
     */
    public JLabel getWelcomeTitle() {
        return label1;
    }

}
