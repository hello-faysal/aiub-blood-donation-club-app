package client.components;

import client.assets.Color.MyColor;
import server.classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Signup {
    JFrame frame;
    private JPanel logoPanel, loginPanel;
    private BufferedImage image;
    private ImageIcon favIcon;
    private JLabel bgColor;
    private JLabel aiubLogo;
    private JLabel logo;
    private JLabel taglineText;
    private JLabel aiubText;
    private JLabel signupText;
    private JLabel signupContainerBg;
    private JLabel aiubIdLabel;
    private JTextField aiubIdField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JLabel bloodGroupLabel;
    private JComboBox<String> bloodGroupBox;
    private JCheckBox isDonorLogin;
    private JButton signupButton;
    private JLabel loginText;
    private JButton loginButton;

    public Signup() {
        frame = new JFrame("Sign Up - AIUB BLOOD DONATION CLUB");

        // favIcon
        try {
            favIcon = new ImageIcon("client/assets/images/logo.png");
            frame.setIconImage(favIcon.getImage());
        } catch (Exception e) {

        }

        // frame background
        bgColor = new JLabel("");
        bgColor.setOpaque(true);
        bgColor.setBounds(0, 0, 1366, 768);
        bgColor.setBackground(MyColor.white);

        // logo panel
        logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, -10, 0));
        logoPanel.setBounds(0, 200, 680, 250);
        logoPanel.setBackground(MyColor.white);

        // aiub and blood logo
        try {
            image = ImageIO.read(new File("client/assets/images/aiub_logo.png"));
            aiubLogo = new JLabel(new ImageIcon(image));
            logoPanel.add(aiubLogo);
        } catch (Exception e) {
        }

        try {
            image = ImageIO.read(new File("client/assets/images/logo.png"));
            logo = new JLabel(new ImageIcon(image));
            logoPanel.add(logo);
        } catch (Exception e) {
        }

        // aiub text
        aiubText = new JLabel("AIUB BLOOD DONATION CLUB",
                SwingConstants.CENTER);
        aiubText.setBounds(0, 320, 680, 300);
        aiubText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        aiubText.setForeground(MyColor.black);

        // tagline text
        taglineText = new JLabel("Donate Blood, Save Life.", SwingConstants.CENTER);
        taglineText.setBounds(0, 370, 680, 300);
        taglineText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        taglineText.setForeground(MyColor.red);

        // signup container bg
        signupContainerBg = new JLabel("");
        signupContainerBg.setOpaque(true);
        signupContainerBg.setBounds(680, 0, 770, 768);
        signupContainerBg.setBackground(MyColor.darkRed);

        // signup text
        signupText = new JLabel("Registration");
        signupText.setBounds(770, 80, 500, 50);
        signupText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        signupText.setForeground(MyColor.white);

        // AIUB ID field and label
        aiubIdLabel = new JLabel("AIUB ID: ");
        aiubIdLabel.setBounds(770, 170, 100, 30);
        aiubIdLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        aiubIdLabel.setForeground(MyColor.white);

        aiubIdField = new JTextField("");
        aiubIdField.setBounds(930, 165, 340, 40);
        aiubIdField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

        // name label and field
        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(770, 220, 100, 30);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        nameLabel.setForeground(MyColor.white);

        nameField = new JTextField("");
        nameField.setBounds(930, 215, 340, 40);
        nameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

        // email label and field
        emailLabel = new JLabel("E-mail: ");
        emailLabel.setBounds(770, 270, 100, 30);
        emailLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        emailLabel.setForeground(MyColor.white);

        emailField = new JTextField("");
        emailField.setBounds(930, 265, 340, 40);
        emailField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

        // phone label and field
        phoneLabel = new JLabel("Phone: ");
        phoneLabel.setBounds(770, 320, 100, 30);
        phoneLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        phoneLabel.setForeground(MyColor.white);

        phoneField = new JTextField("");
        phoneField.setBounds(930, 315, 340, 40);
        phoneField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

        // password label and filed
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(770, 370, 200, 30);
        passwordLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        passwordLabel.setForeground(MyColor.white);

        passwordField = new JPasswordField("");
        passwordField.setBounds(930, 365, 340, 40);
        passwordField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

        // confirm password label and filed
        confirmPasswordLabel = new JLabel("Re-Password: ");
        confirmPasswordLabel.setBounds(770, 420, 200, 30);
        confirmPasswordLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        confirmPasswordLabel.setForeground(MyColor.white);

        confirmPasswordField = new JPasswordField("");
        confirmPasswordField.setBounds(930, 415, 340, 40);
        confirmPasswordField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

        // blood group label and combo box
        bloodGroupLabel = new JLabel("Blood Group: ");
        bloodGroupLabel.setBounds(770, 470, 200, 30);
        bloodGroupLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        bloodGroupLabel.setForeground(MyColor.white);

        String[] bloodGroups = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };
        bloodGroupBox = new JComboBox<>(bloodGroups);
        bloodGroupBox.setSelectedIndex(-1);
        bloodGroupBox.setBounds(930, 465, 340, 40);

        // is donor login
        isDonorLogin = new JCheckBox("Would you like to be a Donor?");
        isDonorLogin.setBounds(770, 520, 500, 30);
        isDonorLogin.setForeground(MyColor.white);
        isDonorLogin.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        isDonorLogin.setContentAreaFilled(false);
        isDonorLogin.setBorderPainted(false);
        isDonorLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // signup button
        signupButton = new JButton("Sign Up");
        signupButton.setBounds(770, 580, 500, 50);
        signupButton.setBackground(MyColor.green);
        signupButton.setForeground(MyColor.white);
        signupButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, -10, 0));
        loginPanel.setBounds(680, 650, 1366 - 680, 50);
        loginPanel.setBackground(MyColor.darkRed);

        loginText = new JLabel("Already have an account?");
        loginText.setForeground(MyColor.white);
        loginText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        loginButton = new JButton("Login");
        loginButton.setForeground(MyColor.yellow);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        // login panel adding
        loginPanel.add(loginText);
        loginPanel.add(loginButton);

        // adding
        frame.add(logoPanel);
        frame.add(aiubText);
        frame.add(taglineText);
        frame.add(signupText);
        frame.add(aiubIdLabel);
        frame.add(aiubIdField);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(confirmPasswordLabel);
        frame.add(confirmPasswordField);
        frame.add(bloodGroupLabel);
        frame.add(bloodGroupBox);
        frame.add(isDonorLogin);
        frame.add(signupButton);
        frame.add(loginPanel);
        frame.add(signupContainerBg);
        frame.add(bgColor);

        // frame
        frame.setSize(1366, 768);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // action listeners

        // login action
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // take data
                String aiubId = aiubIdField.getText().trim();
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                char[] password = passwordField.getPassword();
                String passwordString = new String(password).trim();
                password = confirmPasswordField.getPassword();
                String confirmPasswordString = new String(password).trim();
                String bloodGroup = (String) bloodGroupBox.getSelectedItem();
                boolean isDonor = isDonorLogin.isSelected();

                // validation
                if (aiubId.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || passwordString.isEmpty()
                        || confirmPasswordString.isEmpty() || bloodGroup == null) {
                    JOptionPane.showMessageDialog(null,
                            "<html><center><font size='5' color='red'><b>Oops!</b> It seems like some required information is missing. Please fill in all the fields to proceed. Thanks!</font></center></html>",
                            "", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // dont use comma
                if (aiubId.indexOf(',') != -1 || name.indexOf(',') != -1 || email.indexOf(',') != -1
                        || phone.indexOf(',') != -1 || passwordString.indexOf(',') != -1) {
                    JOptionPane.showMessageDialog(null,
                            "<html><center><font size='5' color='red'>Please avoid using commas in any fields. Thanks!</font></center></html>",
                            "", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // valid aiub id
                if (!User.isValidAiubId(aiubId)) {
                    JOptionPane.showMessageDialog(null,
                            "<html><center><font size='5' color='red'><b>Oops!</b> Invalid AIUB ID. Please try again with a valid AIUB ID.</font></center></html>",
                            "", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // valid email
                if (!User.isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null,
                            "<html><center><font size='5' color='red'><b>Oops!</b> Invalid Email. Please try again with a valid email.</font></center></html>",
                            "", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // valid phone
                if (!User.isNumeric(phone) || phone.length() != 11) {
                    JOptionPane.showMessageDialog(null,
                            "<html><center><font size='5' color='red'><b>Oops!</b> Invalid Phone number. Phone Number must be 11 digit.</font></center></html>",
                            "", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // password and confirm password matching
                if (!passwordString.equals(confirmPasswordString)) {
                    JOptionPane.showMessageDialog(null,
                            "<html><center><font size='5' color='red'><b>Oops!</b> Password and confirm password don't match. Please try again.</font></center></html>",
                            "", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // go for sign up process
                if (isDonor) {
                    Donor d = Donor.signup(aiubId, name, email, phone, confirmPasswordString, bloodGroup);
                    if (d != null) {
                        frame.setVisible(false);
                        new DonorDashboard(d);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "<html><center><font size='5' color='red'>User already exist!</font></center></html>",
                                "", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    Recipient r = Recipient.signup(aiubId, name, email, phone, confirmPasswordString,
                            bloodGroup);
                    if (r != null) {
                        frame.setVisible(false);
                        new RecipientDashboard(r);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "<html><center><font size='5' color='red'>User already exist!</font></center></html>",
                                "", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // login action
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Login();
            }
        });
    }
}