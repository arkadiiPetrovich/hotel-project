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
    private JButton menuButton;
    private JButton checkReviewButton;
    private JButton writeReviewButton;

    //Labels
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    
    //TextArea
    private JTextArea textArea1;
    private JTextArea textArea2;

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
        menuButton = new JButton("Go Back");
        menuButton.setLocation(130, 250);
        menuButton.setSize(150, 30);
        menuButton.setVisible(false);
        add(menuButton);
        
        //checkReviewButton Attributes
        checkReviewButton = new JButton("Check Reviews");
        checkReviewButton.setLocation(70,30);
        checkReviewButton.setSize(150, 30);
        checkReviewButton.setVisible(false);
        add(checkReviewButton);
        
        //writeReviewButton Attributes
        writeReviewButton = new JButton("Write Reviews");
        writeReviewButton.setLocation(70, 70);
        writeReviewButton.setSize(150, 30);
        writeReviewButton.setVisible(false);
        add(writeReviewButton);
        
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
        
        //label3 attributes
        label3 = new JLabel(" ## Contact Details ##");
        label3.setLocation(150, 0);
        label3.setSize(300, 20);
        label3.setVisible(false);
        add(label3);
        
        //label4 attributes
        label4 = new JLabel();
        label4.setLocation(150, 50);
        label4.setSize(300, 20);
        label4.setVisible(false);
        add(label4);
        
        //label5 attributes
        label5 = new JLabel("## Current Reviews ##");
        label5.setLocation(190, 0);
        label5.setSize(300, 20);
        label5.setVisible(false);
        add(label5);
        
        //===== TextArea =====
        //textArea1 attributes
        textArea1 = new JTextArea();
        textArea1.setLocation(25, 30);
        textArea1.setSize(375, 175);
        textArea1.setVisible(false);
        add(textArea1);
        
        //textArea2 attributes
        textArea2 = new JTextArea();
        textArea2.setLocation(65, 30);
        textArea2.setSize(300, 100);
        textArea2.setVisible(false);
        add(textArea2);
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
        return menuButton;
    }

    /**
     * @return the label3
     */
    public JLabel getLabel3() {
        return label3;
    }

    /**
     * @return the label4
     */
    public JLabel getLabel4() {
        return label4;
    }

    /**
     * @return the textArea2
     */
    public JTextArea getTextArea2() {
        return textArea2;
    }

    /**
     * @return the checkReviewButton
     */
    public JButton getCheckReviewButton() {
        return checkReviewButton;
    }

    /**
     * @return the writeReviewButton
     */
    public JButton getWriteReviewButton() {
        return writeReviewButton;
    }

    /**
     * @return the label5
     */
    public JLabel getLabel5() {
        return label5;
    }

}
