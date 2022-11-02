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
interface Transaction{
    
    public void paymentTypes();
    public void setPaymentType(String choice);
    public String getPaymentType();
    public void pay();
    public void reciept(String paymentType, String email);
}
