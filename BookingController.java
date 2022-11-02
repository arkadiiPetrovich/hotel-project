/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Lars Bosales, 20112844
 */
public class BookingController extends JFrame{

    //========= Fields =========
    //Based on MVC model
    private Booking model; //M
    private BookingView view; //V
   
    
    //========= Constructor =========
    public BookingController() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); //stop runtime on exit
        setSize(300, 300); //frame size
        this.view = new BookingView(this.model); //instantiate the panel
        add(view); //add to the Frame
        
        //========= Action Listeners =========
    }
    
        //========= Main =========
    public static void main(String[] args) {
        JFrame myFrame = new BookingController(); //create the frame
        myFrame.setVisible(true);
    }
}
