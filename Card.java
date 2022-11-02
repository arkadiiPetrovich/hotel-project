package Assignment2;

import Assignment1.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lars Bosales
 */
public class Card extends Payment{

    //================ ### FIELDS ### =================

    private String accountNo;
    private String expDate = "";
    private String fullName = "";
    
    
    
    //============== ### CONSTRUCTOR ### ============== 
    public Card(String fname, String lname, String accNo, String bank, int expMonth, int expYear) {
        super("Card");
        this.setFullName(fname, lname);
        this.setAccountNo(accNo);
        this.setBank(bank);
        this.setExpiryDate(expMonth, expYear);
    }

    //=============== ### GET & SET ### ===============
    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    private void setFname(String fname) {
        Card.fname = fname;
    }

    //-------------------------------------------------
    public String getLname() {
        return lname;
    }

    private void setLname(String lname) {
        Card.lname = lname;
    }

    //-------------------------------------------------
    public String getAccountNo() {
        return accountNo;
    }

    public final void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    //-------------------------------------------------
    public String getBank() {
        return bank;
    }

    public final void setBank(String bank) {
        Card.bank = bank;
    }

    //-------------------------------------------------
    public int getExpMonth() {
        return expMonth;
    }

    private void setExpMonth(int expMonth) {
        Card.expMonth = expMonth;
    }

    //-------------------------------------------------
    public int getExpYear() {
        return expYear;
    }

    private void setExpYear(int expYear) {
        Card.expYear = expYear;
    }

    //-------------------------------------------------
    public final void setFullName(String fname, String lname) {
        this.setFname(fname);
        this.setLname(lname);
        this.fullName = fname + " " + lname;
    }
    
    //-------------------------------------------------
    public final void setExpiryDate(int expMonth, int expYear) {
        this.setExpMonth(expMonth);
        this.setExpYear(expYear);
        this.expDate = this.getExpMonth() + "/" + this.getExpYear();
    }

    public String getExpiryDate() {
        return this.expDate;
    }
    //================ ### METHODS ### ================

    //-------------------------------------------------
    public String getCardDetails() {
        return "\n### CARD DETAILS ###"
                + "\nRegistered Owner: " + this.getFname() + " " + this.getLname()
                + "\nBank: " + this.getBank()
                + "\nAccount Number: " + this.getAccountNo()
                + "\nCard expiry date: " + this.getExpiryDate() + "\n";
    }
}
