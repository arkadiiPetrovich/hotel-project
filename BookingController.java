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
    //Based on MVC model
    private Booking model; //M
    private BookingView view; //V

    //========= Constructor =========
    public BookingController() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); //stop runtime on exit
        setSize(300, 350); //frame size
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
    }
    //========= Event Handler Methods =========

    //bookingButton
    private void eventHandlerBookingButton() {
        System.out.println("Booking initialised, executing. . .");
        this.model = new Booking(1); //initialise and run methods to proceed with booking
    }

    //availabilityButton
    private void eventHandlerAvailabilityButton() {
        this.model = new Booking(2);
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

    //========= Main =========
    public static void main(String[] args) {
        JFrame myFrame = new BookingController(); //create the frame
        myFrame.setVisible(true);
    }
}
