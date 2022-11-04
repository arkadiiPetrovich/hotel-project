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
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

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
    private JButton payDetailsButton;

    //Labels
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;

    //TextArea
    private JTextArea textArea1;
    private JTextArea textArea2;

    //TextField
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;

    //JList
    private final String[] availableDays = {"Monday", "Thursday", "Sunday"};
    private JList<String> dayList;

    //radio buttons + button group
    private ButtonGroup rbGroup;
    private JRadioButton rButton1;
    private JRadioButton rButton2;
    private JRadioButton rButton3;

    //option pane
    private JOptionPane errorPane;

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
        checkReviewButton.setLocation(70, 30);
        checkReviewButton.setSize(150, 30);
        checkReviewButton.setVisible(false);
        add(checkReviewButton);

        //writeReviewButton Attributes
        writeReviewButton = new JButton("Write Reviews");
        writeReviewButton.setLocation(70, 70);
        writeReviewButton.setSize(150, 30);
        writeReviewButton.setVisible(false);
        add(writeReviewButton);
        
        //payDetailsButton
        payDetailsButton = new JButton("Proceed to Payment");
        payDetailsButton.setSize(250, 30);
        payDetailsButton.setVisible(false);
        add(payDetailsButton);
        
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

        //## Extra Labels ##
        //label6 attributes
        label6 = new JLabel("Day");
        label6.setVisible(false);
        label6.setSize(300, 20);
        add(label6);
        //label7 attributes
        label7 = new JLabel();
        label7.setVisible(false);
        label7.setSize(300, 20);
        add(label7);
        //label8 attributes
        label8 = new JLabel();
        label8.setVisible(false);
        label8.setSize(300, 20);
        add(label8);
        //label9 attributes
        label9 = new JLabel();
        label9.setVisible(false);
        label9.setSize(300, 20);
        add(label9);
        //label10 attributes
        label10 = new JLabel();
        label10.setVisible(false);
        label10.setSize(300, 20);
        add(label10);

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

        //===== TextField =====
        //field1 attributes
        field1 = new JTextField();
        field1.setVisible(false);
        add(field1);

        //field2 attributes
        field2 = new JTextField();
        field2.setVisible(false);
        add(field2);

        //field3 attributes
        field3 = new JTextField();
        field3.setVisible(false);
        add(field3);

        //field4 attributes
        field4 = new JTextField();
        field4.setSize(100, 30);
        field4.setLocation(600, 340);
        field4.setVisible(false);
        add(field4);

        //===== JList =====
        dayList = new JList<String>(availableDays);
        dayList.setLocation(100, 250);
        dayList.setSize(75, 60);
        dayList.setVisible(false);
        add(dayList);

        //===== RadioButton + ButtonGroup =====
        //rb1
        rButton1 = new JRadioButton("Cash");
        rButton1.setSize(30, 40);
        rButton1.setVisible(false);
        add(rButton1);

        //rb2
        rButton2 = new JRadioButton("Card");
        rButton2.setSize(30, 40);
        rButton2.setVisible(false);
        add(rButton2);

        //rb3
        rButton3 = new JRadioButton("Bank Transfer");
        rButton3.setSize(30, 40);
        rButton3.setVisible(false);
        add(rButton3);

        /*
        * Button group makes sure only one button
        * at a time may be selected
         */
        rbGroup = new ButtonGroup();
        rbGroup.add(rButton1);
        rbGroup.add(rButton2);
        rbGroup.add(rButton3);

        //===== Option Pane =====
        errorPane = new JOptionPane("Error");
        add(errorPane);

    } //## END OF CONSTRUCTOR ##

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
        return getMenuButton();
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

    /**
     * @return the menuButton
     */
    public JButton getMenuButton() {
        return menuButton;
    }

    /**
     * @return the field1
     */
    public JTextField getField1() {
        return field1;
    }

    /**
     * @return the field2
     */
    public JTextField getField2() {
        return field2;
    }

    /**
     * @return the field3
     */
    public JTextField getField3() {
        return field3;
    }

    /**
     * @return the field4
     */
    public JTextField getField4() {
        return field4;
    }

    /**
     * @return the label6
     */
    public JLabel getLabel6() {
        return label6;
    }

    /**
     * @return the label7
     */
    public JLabel getLabel7() {
        return label7;
    }

    /**
     * @return the label8
     */
    public JLabel getLabel8() {
        return label8;
    }

    /**
     * @return the label9
     */
    public JLabel getLabel9() {
        return label9;
    }

    /**
     * @return the dayList
     */
    public JList getDayList() {
        return dayList;
    }

    /**
     * @return the label10
     */
    public JLabel getLabel10() {
        return label10;
    }

    /**
     * @return the errorPane
     */
    public JOptionPane getErrorPane() {
        return errorPane;
    }

    /**
     * @return the rButton1
     */
    public JRadioButton getrButton1() {
        return rButton1;
    }

    /**
     * @return the rButton2
     */
    public JRadioButton getrButton2() {
        return rButton2;
    }

    /**
     * @return the rButton3
     */
    public JRadioButton getrButton3() {
        return rButton3;
    }

    /**
     * @return the rbGroup
     */
    public ButtonGroup getRbGroup() {
        return rbGroup;
    }

    /**
     * @return the payDetailsButton
     */
    public JButton getPayDetailsButton() {
        return payDetailsButton;
    }

}
