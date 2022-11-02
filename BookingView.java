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
    private JLabel welcomeTitle;
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
        welcomeTitle = new JLabel("## Welcome to Dreamland Hotels! ##");
        welcomeTitle.setLocation(35, 15);
        welcomeTitle.setSize(250, 20);
        add(welcomeTitle);
    }

}
