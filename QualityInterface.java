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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;

/**
 *
 * @author User
 */
public class QualityInterface extends JFrame {

    private Quality quality = new Quality();
    private Units units = new Units();

    private DefaultStyledDocument doc;
    private JLabel label;
    private JLabel remaningLabel = new JLabel();
    private JPanel textPanel;
    private JTextArea textArea;
    private JPanel buttonPanel;
    private JButton findUnit = new JButton("Find unit");
    private JButton backToMenu = new JButton("Menu");
    private static Object lock = new Object();
    private boolean wait;

    private Border blackline = BorderFactory.createLineBorder(Color.black);

    public QualityInterface() {
        showQualityInterface(this.quality.showUnitsComments());
    }

    private void showQualityInterface(ArrayList<String> string) {
        JFrame frame = new JFrame("Comments");
        frame.setTitle("Unit\'s comments");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.textPanel = new JPanel();
        this.label = new JLabel("<html>Comments<br>");
//        JScrollPane scroller = new JScrollPane(this.label, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        for (String i : string) {
            if (i.length() <= 100) {
                this.label.setText(this.label.getText() + i + "<br>");
            } else if (i.length() > 100) {
                String temp = i;
                while (!temp.isEmpty()) {
                    if (temp.length() >= 100) {
                        for (int b = 100; b >= 0; b--) {
                            if (temp.charAt(b) == ' ') {
                                this.label.setText(this.label.getText() + temp.substring(0, b) + "<br>");
                                temp = temp.replace(temp.substring(0, b), "");
                                break;
                            } else if (b == 0 && temp.charAt(b) != ' ') {
                                this.label.setText(this.label.getText() + temp.substring(0, b) + "<br>");
                                temp = temp.replace(temp.substring(0, b), "");
                            }
                        }
                    } else if (temp.length() < 100) {
                        this.label.setText(this.label.getText() + temp + "<br>");
                        temp = "";
                    }
                }
            } else {
                this.label.setText(this.label.getText() + "Comment reading error<br>");
                System.out.println("Commnet readint error.");
            }
            this.label.setText(this.label.getText() + "<br>");
        }

        this.label.setText(this.label.getText() + "</html>");
        JScrollPane scrollComments = new JScrollPane(this.textPanel);
        this.textPanel.add(this.label, BorderLayout.CENTER);
        
        this.textPanel.setBorder(blackline);
        this.label.setBounds(100, 0, 600, 300);
//        frame.add(this.textPanel, BorderLayout.CENTER);
        frame.add(scrollComments, BorderLayout.CENTER);
        this.textPanel.setBounds(75, 0, 550, 250);
        scrollComments.setBounds(75, 0, 550, 250);
        frame.add(scrollComments);

        JButton addComment = new JButton("Add comment");
        JButton showUnits = new JButton("Show Units");
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 3));
        buttons.add(addComment);
        buttons.add(showUnits);
        buttons.add(this.backToMenu, BorderLayout.SOUTH);
        buttons.setBounds(200, 450, 300, 25);
        frame.add(buttons);

        JTextArea unitInput = new JTextArea();
        JTextArea nameInput = new JTextArea();
        JTextArea commentInput = new JTextArea();
        JTextArea ratingInput = new JTextArea();
        JLabel unitLabel = new JLabel("Enter unit Num:");
        JLabel nameLabel = new JLabel("Enter your name:");
        JLabel commentLabel = new JLabel("Enter a comment:");
        JLabel ratingLabel = new JLabel("Enter a rating out of 5:");
        
        unitLabel.setBounds(85, 250, 100, 20);
        unitInput.setBounds(185, 250, 20, 20);
        nameLabel.setBounds(85, 275, 100, 20);
        nameInput.setBounds(185, 275, 200, 20);
        commentLabel.setBounds(85, 300, 100, 20);
        commentInput.setBounds(185, 300, 425, 100);
        ratingLabel.setBounds(85, 420, 125, 20);
        ratingInput.setBounds(210, 420, 20, 20);
        //JScrollPane scrollPane = new JScrollPane(commentInput);
        /////////////////////////////

        unitInput.setLineWrap(true);
        unitInput.setWrapStyleWord(true);
        commentInput.setLineWrap(true);
        commentInput.setWrapStyleWord(true);
        nameInput.setLineWrap(true);
        nameInput.setWrapStyleWord(true);
        ratingInput.setLineWrap(true);
        ratingInput.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(commentInput);
        sp.setBounds(185, 300, 425, 100);

        doc = new DefaultStyledDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(500));
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCount();
            }
        });
        commentInput.setDocument(doc);

        updateCount();
        this.remaningLabel.setBounds(210, 375, 200, 60);

        frame.add(unitLabel);
        frame.add(unitInput);
        frame.add(nameLabel);
        frame.add(nameInput);
        frame.add(commentLabel);
        frame.add(sp);
        frame.add(ratingLabel);
        frame.add(ratingInput);
        frame.add(this.remaningLabel);

        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);

//        frame.pack();
        frame.setLayout(null);
        frame.setVisible(true);

        addComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(String.valueOf(commentInput.getText()));
                try {
                    if (String.valueOf(unitInput.getText()).trim().isEmpty()||String.valueOf(nameInput.getText()).trim().isEmpty() || String.valueOf(commentInput.getText()).trim().isEmpty() || String.valueOf(ratingInput.getText()).trim().isEmpty()) {
                        errorMessageFrame("One of the input is empty.");
                    } else if (!String.valueOf(unitInput.getText()).trim().isEmpty()&&!String.valueOf(nameInput.getText()).trim().isEmpty() && !String.valueOf(commentInput.getText()).trim().isEmpty() && (Integer.valueOf(ratingInput.getText())<=5&&Integer.valueOf(ratingInput.getText())>=0)) {
                        if(nameInput.getText().length()>50){
                        errorMessageFrame("Your name is too long. Please enter shorter version");
                    }
                        else if(units.checkUnit(String.valueOf(unitInput.getText()).trim())==true){
                            quality.addComment(Integer.valueOf(unitInput.getText()), String.valueOf(nameInput.getText()), String.valueOf(commentInput.getText()), Integer.valueOf(ratingInput.getText()));
                            nortificationMessageFrame("New comment added");
                            frame.dispose();
                            
                        }else if(units.checkUnit(String.valueOf(unitInput.getText()).trim())==false){
                            errorMessageFrame("The unit is not exists");
                        }else if(Integer.valueOf(ratingInput.getText())>5||Integer.valueOf(ratingInput.getText())<0){
                            errorMessageFrame("Wrong rating input");
                        }
                    }else{
                        errorMessageFrame("Some intput is wrong");
                    }
                } catch (NumberFormatException b) {
                    errorMessageFrame("Please enter a whole number in rating field or unit number.");
                }

            }
        });
        this.backToMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //Add back to menu!!!!!!!!!!!!!!!!!!!
                BookingControllerClass menu = new BookingControllerClass();
                menu.eventHandlerBackButton();
            }
        });
        showUnits.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllUnitsInterface();
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
    
    private void nortificationMessageFrame(String string) {
        JFrame frame = new JFrame("Nortification");
        frame.setSize(250, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel errorLabel = new JLabel("<html>Nortification Message<br>");
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
                QualityInterface show = new QualityInterface();
            }
        });
    }
    
    private void showAllUnitsInterface() {
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

        JButton ok = new JButton("Ok");
        this.buttonPanel = new JPanel();
        buttonPanel.add(ok);
        frame.add(buttonPanel, BorderLayout.SOUTH);
//        this.buttonPanel.setBorder(blackline);//to see border of buttons
        this.buttonPanel.setBounds(200, 190, 100, 30);

//        frame.pack();
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);

        frame.setLayout(null);
        frame.setVisible(true);
        ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
            
        });
    }

    private void updateCount() {
        remaningLabel.setText((500 - doc.getLength()) + " characters remaining");
    }
}
