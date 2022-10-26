package assessment;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * 
 * This code is mainly for when the user has chosen cash
 * as their payment method --> leading to the possibility
 * where they may be a tourist from the airport and have
 * cash on them as their primary payment source.
 * 
 * @author Lars Bosales
 */
public final class Cash extends Payment{
    // may need to extend a list, ENUM of units and their prices,
    //or connect the prices to the Payment program somehow
    
    //================ ### FIELDS ### =================
    private double userCash, price, convertedPrice;
    private String userCurrency;
    
    //============== ### CONSTRUCTOR ### ============== 
    public Cash(String fname, String lname, double userCash){
        super("Cash");
        this.setPrice(total); //price of the room booking
        this.setUserCash(userCash); //amount user will be paying
    }
    
    //=============== ### GET & SET ### ===============

    /**
     * @return the userCash
     */
    public double getUserCash() {
        return userCash;
    }
    
    //-------------------------------------------------
    /**
     * @param userCash the userCash to set
     */
    public void setUserCash(double userCash) {
        this.userCash = userCash;
    }
    
    //-------------------------------------------------
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    
    //-------------------------------------------------
    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
     //-------------------------------------------------
    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        Cash.fname = fname;
    }
    
    //-------------------------------------------------
    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }
    //-------------------------------------------------
    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        Cash.lname = lname;
    }
    
    //================ ### METHODS ### ================
    public String getCashDetails(){
        return "### CASH DETAILS ###\n" +
               "Total after conversion: "+ convertedPrice + "\n" +
               "Customer's Balance: " + userCash + "\n" +
               "There are ATM machines available on the premises if needed.\n";
    }
    
    //-------------------------------------------------
    
    public void convertTotal(){
        System.out.println("Available currencies: ");
        for(Rates r: Rates.values()){
            System.out.println(r);
        }
        System.out.println("Which currency do you have?");
        userCurrency = scan.nextLine();    
        userCurrency = userCurrency.toUpperCase(); //capitalise for the enum
        
        //Converted using values from Rates enum
        switch(userCurrency){
            case "JPY" : {
                convertedPrice = total * Rates.JPY.rate;
            }
            case "CNY" : {
                convertedPrice = total * Rates.CNY.rate;
            }
            case "EUR" : {
                convertedPrice = total * Rates.EUR.rate;
            }
            case "USD" : {
                convertedPrice = total * Rates.USD.rate;
            }
            case "AUD" : {
                convertedPrice = total * Rates.AUD.rate;
            }    
        }
    }

    
}
