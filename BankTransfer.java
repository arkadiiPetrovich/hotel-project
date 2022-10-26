package assessment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Random;
/**
 *
 * @author Lars Bosales
 */
public final class BankTransfer extends Payment{
    //================ ### FIELDS ### =================
    
    private static String hotelBankNum = "";
    Card c;
    
    //============== ### CONSTRUCTOR ### ============== 
    public BankTransfer(String fname, String lname, String accNo, String bank){
        super("Bank Transfer");
        // bank transfer as a 'card' to get relative fields
        c = new Card(fname, lname, accNo, bank, 0, 0);
        c.setFullName(fname, lname);
        c.setAccountNo(accNo);
        c.setBank(bank);
        generateBankNum();
    }
    
    //=============== ### GET & SET ### ===============

    public static String getHotelBankNum() {
        return BankTransfer.hotelBankNum;
    }

    private static void setHotelBankNum(String aHotelBankNum) {
        hotelBankNum = aHotelBankNum;
    }
    
    //================ ### METHODS ### ================
    
    private static void generateBankNum(){
        Random rand = new Random();
        Card.prefix = rand.nextInt(999999-100000)+100000; //6 digits
        Card.body = rand.nextInt(9999999-1000000)+1000000; //7 digits
        Card.suffix = rand.nextInt(999-10)+10; //2-3 digits

        setHotelBankNum(Card.prefix + "-" + Card.body + "-" + Card.suffix);
    }
    
     //-------------------------------------------------
    
    public String getTransferDetails(){
        return "\n### BANK TRANSFER DETAILS ###"
                + "\nBank: " + c.getBank() 
                + "\nUser Bank Number: " + c.getAccountNo()
                + "Will be transferring to account. . ."
                + "\nHotel Bank Number: " + BankTransfer.getHotelBankNum() + "\n";           
    }   
}
