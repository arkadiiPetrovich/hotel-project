/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.DefaultStyledDocument;

/**
 *
 * @author User
 */
public class UnitsInterface extends JFrame {

    private int adults = 0;
    private int childs = 0;

    private Units units = new Units();

//    private JFrame frame;
    private JLabel label;
    private JPanel textPanel;
    private JTextArea textArea;
    private JPanel buttonPanel;
    private JButton findUnit = new JButton("Find unit");
    private JButton backToMenu = new JButton("Menu");
    private static Object lock = new Object();
    private boolean wait;

    private DefaultStyledDocument doc;
    private Border blackline = BorderFactory.createLineBorder(Color.black);

    public int returnAdults() {
        wait=true;
        return this.adults;
    }

    public int returnChilds() {
        return this.childs;
    }
    public boolean returnHold(){
        return this.wait;
    }

    public UnitsInterface() {
        showAllUnitsInterface() ;
    }

    private boolean showAllUnitsInterface() {
        this.wait= true;
        ArrayList<String> unitsInformation = this.units.showAllUnits();
        JFrame frame = new JFrame("Hotel Units");
        frame.setTitle("Units lists");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.textPanel = new JPanel();

        this.label = new JLabel("<html>Units Information<br>");
        for (String i : unitsInformation) {
            this.label.setText(this.label.getText() + i + "<br>");
        }
        this.label.setText(this.label.getText() + "</html>");
        textPanel.add(this.label, BorderLayout.NORTH);
        frame.add(textPanel, BorderLayout.NORTH);
        this.label.setBounds(50, 0, 500, 100);
        this.textPanel.setBounds(50, 0, 500, 100);
        this.textPanel.setBorder(blackline);

        this.buttonPanel = new JPanel();
        buttonPanel.add(findUnit);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        this.buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(backToMenu, BorderLayout.SOUTH);
//        this.buttonPanel.setBorder(blackline);//to see border of buttons
        this.buttonPanel.setBounds(200, 220, 200, 25);

        JTextField adultsInput = new JTextField("0");
        JTextField childsInput = new JTextField("0");
        JLabel adultsLabel = new JLabel("Enter number of adults");
        JLabel childsLabel = new JLabel("Enter Number of childs");

        adultsInput.setBounds(185, 190, 100, 20);
        childsInput.setBounds(435, 190, 100, 20);
        adultsLabel.setBounds(50, 190, 200, 20);
        childsLabel.setBounds(300, 190, 200, 20);
        frame.add(adultsInput);
        frame.add(childsInput);
        frame.add(adultsLabel);
        frame.add(childsLabel);

//        frame.pack();
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);

        frame.setLayout(null);
        frame.setVisible(true);

        findUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adults = Integer.valueOf(adultsInput.getText());
                    childs = Integer.valueOf(childsInput.getText());
                    if (adults == 0) {
                        errorMessageFrame("Need at least 1 adult to book a unit");
                    } else if (adults < 0 || childs < 0) {
                        errorMessageFrame("You can't input negative number");
                    } else if (adults > 0) {
                        frame.dispose();
                        wait = showRecomendedUnitsIterface(adults, childs);
                    }
                } catch (NumberFormatException a) {
                    System.out.println("Number Format Exception " + a.getMessage());
                    errorMessageFrame("Please enter a whole numbers");
                }
            }
        });
        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        return wait;
    }

    public boolean showRecomendedUnitsIterface(int adults, int childs) {
        Map<String, String> recommendedUnits = this.units.showRecommendedUnits(adults, childs);
        JFrame frame = new JFrame("Recommended Units");

        this.textPanel = new JPanel();
        this.label = new JLabel("<html>Recommended units for you<br>");
        for (String i : recommendedUnits.keySet()) {
            this.label.setText(this.label.getText() + "Unit " + i + ": inclide " + recommendedUnits.get(i) + "<br>");
        }
        this.label.setText(this.label.getText() + "</html>");
        textPanel.add(this.label, BorderLayout.NORTH);
        frame.add(textPanel, BorderLayout.NORTH);
        this.label.setBounds(50, 0, 500, 100);
        this.textPanel.setBounds(50, 0, 500, 100);
        this.textPanel.setBorder(blackline);

        JLabel enterMessage = new JLabel("Select booking unit: ");
        JTextArea selectUnit = new JTextArea();
        enterMessage.setBounds(50, 190, 200, 20);
        selectUnit.setBounds(275, 190, 20, 20);
        frame.add(enterMessage);
        frame.add(selectUnit);

        
        
        JPanel buttons = new JPanel();
        JButton select = new JButton("Select");
//        this.buttonPanel= new JPanel();
        buttons.setLayout(new GridLayout(1, 2));
        buttons.add(select);
        buttons.add(this.backToMenu, BorderLayout.SOUTH);
        frame.add(buttons, BorderLayout.SOUTH);
        buttons.setBounds(185, 220, 200, 25);
        buttons.setBorder(blackline);//to see border of buttons

        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);
        frame.setVisible(true);

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (selectUnit == null) {
                    errorMessageFrame("Nothing entered");
                } else if (selectUnit != null) {
                    if (units.checkAbleToBookUnit(String.valueOf(selectUnit.getText()).trim()) == true) {
                        frame.dispose();
                        System.out.println(units.returnSelectedUnit(Integer.valueOf(selectUnit.getText())));
                        wait=false;
                        //!!!!!!!!!!Continue order!!!!!!!!!!!!!!
                    } else if (units.checkAbleToBookUnit(String.valueOf(selectUnit.getText()).trim()) == false) {
                        errorMessageFrame("You can't choose that unit becaus you have too much people or unit doesn't exists");
                    }
                }
                }catch(NumberFormatException b){
                    errorMessageFrame("Please enter a whole Number");
                }
                
            }
        });
        return wait;
    }

    

    public void errorMessageFrame(String string) {
        JFrame frame = new JFrame("Error");
        frame.setSize(250, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel errorLabel = new JLabel("<html>Error Message<br>");
        errorLabel.setText(errorLabel.getText() + string + "</html>");
        errorLabel.setBounds(25, 0, 175, 120);
        errorLabel.setBorder(blackline);
        frame.add(errorLabel);

        JButton ok = new JButton("ok");
        frame.add(ok);
        ok.setBounds(90, 130, 50, 25);

        frame.setLayout(null);
        frame.setVisible(true);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }

        });
    }
}
