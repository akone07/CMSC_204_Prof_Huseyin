/*
 * Class: CMSC204-31645
 * 
 * Instructor: Huseyin_Aygun 
 * 
 * Description: The PasswordCheckerGUI.java 
 *
 * This is a Java graphical user interface (GUI) application that allows users to enter and check passwords based on predefined rules. 
 * The application can also load a file containing multiple passwords and verify their validity
 *
 * Due: 02-02-2025
 * 
 * Platform/compiler: Windows 
 * 
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: ALASSANE KONE
*/



package password_Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordCheckerGUI extends JFrame {
    private JTextField passwordField1;
    private JTextField passwordField2;
    private JTextArea resultArea;
    private JButton checkPasswordButton;
    private JButton checkFileButton;
    private JButton exitButton;

    public PasswordCheckerGUI() {
        setTitle("Password Checker");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Instructions Panel
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new GridLayout(6, 1));
        rulesPanel.add(new JLabel("Use the following rules when creating your passwords:"));
        rulesPanel.add(new JLabel("1. Length > 6 (10 for strong passwords)"));
        rulesPanel.add(new JLabel("2. At least one uppercase letter"));
        rulesPanel.add(new JLabel("3. At least one lowercase letter"));
        rulesPanel.add(new JLabel("4. At least one numeric character"));
        rulesPanel.add(new JLabel("5. No more than 2 identical characters in sequence"));
        
        add(rulesPanel, BorderLayout.NORTH);

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Enter Password:"));
        passwordField1 = new JTextField();
        passwordField1.setPreferredSize(new Dimension(150, 25)); // Smaller rectangular field
        inputPanel.add(passwordField1);

        inputPanel.add(new JLabel("Re-enter Password:"));
        passwordField2 = new JTextField();
        passwordField2.setPreferredSize(new Dimension(150, 25)); // Smaller rectangular field
        inputPanel.add(passwordField2);

        add(inputPanel, BorderLayout.CENTER);

        // Buttons
        checkPasswordButton = new JButton("Check Password");
        checkFileButton = new JButton("Check Passwords in File");
        exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkPasswordButton);
        buttonPanel.add(checkFileButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.SOUTH);

        // Result Area
        resultArea = new JTextArea(5, 90);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.EAST);

        // Button Actions
        checkPasswordButton.addActionListener(e -> checkPassword());
        checkFileButton.addActionListener(e -> checkPasswordsFromFile());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void checkPassword() {
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();
        
        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(this, "The passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            if (PasswordCheckerUtility.isValidPassword(password1)) {
                if (PasswordCheckerUtility.isWeakPassword(password1)) {
                    resultArea.setText("✅ Password is OK but weak.");
                } else {
                    resultArea.setText("✅ Password is valid.");
                }
            }
        } catch (Exception ex) {
            resultArea.setText("❌ Invalid password: " + ex.getMessage());
        }
    }

    private void checkPasswordsFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Scanner scanner = new Scanner(file);
                ArrayList<String> passwords = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    passwords.add(scanner.nextLine());
                }
                scanner.close();
                
                ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(passwords);
                StringBuilder result = new StringBuilder();
                for (String invalid : invalidPasswords) {
                    result.append("❌ ").append(invalid).append("\n");
                }
                resultArea.setText(result.toString());
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordCheckerGUI().setVisible(true));
    }
}
