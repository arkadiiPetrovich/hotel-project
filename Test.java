/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessment;

/**
 *
 * @author User
 */
public class Test {

    public static void main(String[] args) {
        
    }

    private void unitsClass() {//cooperate with DB
        Units unit = new Units();
//        unit.downloadUnitsInformation();
//        unit.showAllUnits();
//        unit.showSelectedUnit(5);//fix not exist unit
//        System.out.println(unit.returnSelectedUnit(1));
//        unit.recommendedUnit(2,3);
//        System.out.println(unit.checkUnit("2"));
    }

    private void qualityClass() {
        Quality qu = new Quality();
//        qu.addComment(6, "AAA AAA", "Heelo can't get \"AAA\"", 0);
//        qu.showUnitComments("1");
    }

    private void priceClass() {
        Price price = new Price();
        price.chosenUnitPrice(1, 2, 0);
        System.out.println(price.getCustomerBill());
    }
}
