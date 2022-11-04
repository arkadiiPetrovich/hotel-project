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
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author User
 */
public class ConversionInterface extends JFrame {

    Rates[] rates = Rates.values();
    private Border blackline = BorderFactory.createLineBorder(Color.black);
    private ButtonGroup checkBoxGroup;
    private JButton backToMenu = new JButton("Menu");
    private JLabel converted = new JLabel();

    public ConversionInterface() {
        super("conversionFrame");
        JFrame frame = new JFrame("Currency converted");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel header = new JLabel("<html>Choose currency to convert.</html>");
        header.setBounds(35, 0, 190, 20);
        frame.add(header);

        JPanel checkBox = new JPanel();
        JCheckBox JPY = new JCheckBox("1 Japanese Yen = " + rates[0].returnRates() + "NZD", false);
        JCheckBox CNY = new JCheckBox("1 Chinese Yuan = " + rates[1].returnRates() + "NZD", false);
        JCheckBox EUR = new JCheckBox("1 Euro = " + rates[2].returnRates() + "NZD", false);
        JCheckBox USD = new JCheckBox("1 United States Dollar = " + rates[3].returnRates() + "NZD", false);
        JCheckBox AUD = new JCheckBox("1 Australian Dollar = " + rates[4].returnRates() + "NZD", false);

        checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(JPY);
        checkBoxGroup.add(CNY);
        checkBoxGroup.add(EUR);
        checkBoxGroup.add(USD);
        checkBoxGroup.add(AUD);

        checkBox.add(JPY);
        checkBox.add(CNY);
        checkBox.add(EUR);
        checkBox.add(USD);
        checkBox.add(AUD);
        checkBox.setLayout(new GridLayout(5, 1));
        checkBox.setBorder(blackline);
        checkBox.setBounds(35, 25, 210, 100);
        frame.add(checkBox, BorderLayout.NORTH);

        JLabel amountLabel = new JLabel("Enter amount to convert");
        JTextArea amountInput = new JTextArea();
        amountLabel.setBounds(35, 125, 150, 20);
        amountInput.setBounds(35, 145, 150, 20);
        frame.add(amountLabel);
        frame.add(amountInput);

        JPanel buttons = new JPanel();
        JButton convert = new JButton("Convert");
        buttons.add(convert);
        buttons.add(this.backToMenu);
        buttons.setLayout(new GridLayout(1, 2));
        buttons.setBounds(35, 205, 210, 20);
        frame.add(buttons);
        frame.add(converted);
        converted.setBounds(35, 165, 210, 40);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    converted.removeAll();
                    converted.setText(" converted to ");
                    String notNZDs = "";
                    String NZDs = "";
                    if (!JPY.isSelected() && !CNY.isSelected() && !EUR.isSelected() && !USD.isSelected() && !AUD.isSelected()) {
                        errorMessageFrame("Select currency to convert");
                    } else if (JPY.isSelected() || CNY.isSelected() || EUR.isSelected() || USD.isSelected() || AUD.isSelected()) {
                        if (JPY.isSelected()) {
//                            System.out.println("Japan");
                            notNZDs = String.format("%.2f", Double.valueOf(amountInput.getText()))+" Japanese Yens";
                            NZDs = String.format("%.2f", Double.valueOf(rates[0].returnRates()) * Double.valueOf(amountInput.getText())) + " NZDs";
                            converted.setText("<html>"+notNZDs + converted.getText() + NZDs+"</html>");

//                            frame.revalidate();
//                            frame.repaint();
                        }else if(CNY.isSelected()){
                            notNZDs = String.format("%.2f", Double.valueOf(amountInput.getText()))+" Chinese Yuans";
                            NZDs = String.format("%.2f", Double.valueOf(rates[1].returnRates()) * Double.valueOf(amountInput.getText())) + " NZDs";
                            converted.setText("<html>"+notNZDs + converted.getText() + NZDs+"</html>");
                            converted.setBounds(35, 165, 210, 40);
                        }else if(EUR.isSelected()){
                            notNZDs = String.format("%.2f", Double.valueOf(amountInput.getText()))+" Euros";
                            NZDs = String.format("%.2f", Double.valueOf(rates[2].returnRates()) * Double.valueOf(amountInput.getText())) + " NZDs";
                            converted.setText("<html>"+notNZDs + converted.getText() + NZDs+"</html>");
                            converted.setBounds(35, 165, 210, 40);
                        }else if(USD.isSelected()){
                            notNZDs = String.format("%.2f", Double.valueOf(amountInput.getText()))+" US Dollars";
                            NZDs = String.format("%.2f", Double.valueOf(rates[3].returnRates()) * Double.valueOf(amountInput.getText())) + " NZDs";
                            converted.setText("<html>"+notNZDs + converted.getText() + NZDs+"</html>");
                            converted.setBounds(35, 165, 210, 40);
                        }else if(AUD.isSelected()){
                            notNZDs = String.format("%.2f", Double.valueOf(amountInput.getText()))+" Australian Dollars";
                            NZDs = String.format("%.2f", Double.valueOf(rates[4].returnRates()) * Double.valueOf(amountInput.getText())) + " NZDs";
                            converted.setText("<html>"+notNZDs + converted.getText() + NZDs+"</html>");
                            converted.setBounds(35, 165, 210, 40);
                        }
                    }
                } catch (NumberFormatException b) {
                    errorMessageFrame("Please use a whole or real number");
                }

            }
        });
        this.backToMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BookingControllerClass menu = new BookingControllerClass();
                menu.eventHandlerBackButton();
            }
            
        });
    }

    private void errorMessageFrame(String string) {
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
