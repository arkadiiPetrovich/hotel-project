/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    private JButton backButton;

    //Labels
    private JLabel label1;
    private JLabel label2;
    
    //TextArea
    private JTextArea textArea1;

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
        
        //backButton Attributes
        backButton = new JButton("Go Back");
        backButton.setLocation(130, 250);
        backButton.setSize(150, 30);
        backButton.setVisible(false);
        add(backButton);
        
        //===== Labels =====
        //label1 attributes
        label1 = new JLabel("## Welcome to Dreamland Hotels! ##");
        label1.setLocation(35, 15);
        label1.setSize(250, 20);
        add(label1);
        
        //label2 attributes
        label2 = new JLabel(" ## Availability List ##");
        label2.setLocation(150, 0);
        label2.setSize(300, 20);
        label2.setVisible(false);
        add(label2);
        
        //===== TextArea =====
        //textArea1 attributes
        textArea1 = new JTextArea();
        textArea1.setLocation(25, 30);
        textArea1.setSize(375, 175);
        textArea1.setVisible(false);
        add(textArea1);
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
        return getLabel1();
    }

    /**
     * @return the label1
     */
    public JLabel getLabel1() {
        return label1;
    }

    /**
     * @return the label2
     */
    public JLabel getLabel2() {
        return label2;
    }

    /**
     * @return the field1
     */
    public JTextArea getTextArea1() {
        return textArea1;
    }

    /**
     * @return the backButton
     */
    public JButton getBackButton() {
        return backButton;
    }

}
