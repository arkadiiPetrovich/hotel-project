/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Lars Bosales, 20112844
 */
public class BookingView{
    
    //========= Fields =========
    private Booking model; 
    private JPanel panel;
    
    //Buttons
    private JButton bookingButton;
    private JButton viewAvailabilityButton;
    //Labels
    
    //TextFields
    
    //========= Constructor =========
    public BookingView(Booking model){ //will initialise the components
        //initialise
        panel = new JPanel();
        this.model = model;
        panel.setLayout(null);
        
        //== Create the components and then add to the panel ==
        //===== Buttons =====
    }
    
}
