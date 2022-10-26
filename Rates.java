package assessment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

/**
 *
 * @author larsj
 */
public enum Rates {
    JPY(0.012),
    CNY(0.24),
    EUR(1.62),
    USD(1.63),
    AUD(1.12);

    //fields
    public final double rate;
    //constructor

    private Rates(double rate) {
        this.rate = rate;
    }
}
