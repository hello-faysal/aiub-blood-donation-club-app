package client.components;

import client.assets.Color.*;
import server.classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.table.*;

public class DonorsProfile {
    User u;
    private boolean isShowDropdown;

    JFrame frame;
    private JPanel mainPanel, navbarPanel;
    private JScrollPane scrollPane;
    private ImageIcon favIcon, icon;
    // navbar
    private BufferedImage image;
    private JLabel aiubLogo;
    private JLabel logo;
    private JLabel aiubText;
    private JButton name;
    private JLabel dropdownBox;
    private JButton goHomeButton;
    private JButton myDonationsButton;
    private JButton myRequestsButton;
    private JButton findDonorButton;
    private JButton logoutButton;

    // profile
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel tableHeaderLabel;

    public DonorsProfile(Donor d, User u) {
        this.u = u;
        frame = new JFrame("Profile - " + d.getName() + " [" + d.getAiubId() + "]" + " - AIUB BLOOD DONATION CLUB");

        // favIcon
        try {
            favIcon = new ImageIcon("client/assets/images/logo.png");
            frame.setIconImage(favIcon.getImage());
        } catch (Exception e) {
        }

        // navbar panel
        navbarPanel = new JPanel();
        navbarPanel.setLayout(null);
        navbarPanel.setPreferredSize(new Dimension(1366, 80));
        navbarPanel.setBackground(MyColor.darkRed);

        // aiub and blood logo
        try {
            image = ImageIO.read(new File("client/assets/images/aiub_logo_sm.png"));
            aiubLogo = new JLabel(new ImageIcon(image));
            aiubLogo.setBounds(50, 15, image.getWidth(), image.getHeight());
            navbarPanel.add(aiubLogo);
        } catch (Exception e) {
        }

        try {
            image = ImageIO.read(new File("client/assets/images/logo_sm.png"));
            logo = new JLabel(new ImageIcon(image));
            logo.setBounds(100, 15, image.getWidth(), image.getHeight());
            navbarPanel.add(logo);
        } catch (Exception e) {
        }

        // aiub text
        aiubText = new JLabel("AIUB BLOOD DONATION CLUB");
        aiubText.setBounds(160, 15, 400, 50);
        aiubText.setForeground(MyColor.white);
        aiubText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));

        // name label and dropdown
        icon = new ImageIcon("client/assets/images/dropdown.png");
        name = new JButton("Welcome, " + u.getName().split(" ")[0], icon);
        name.setBounds(1366 - 320, 15, 250, 50);
        name.setForeground(MyColor.white);
        name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        name.setOpaque(false);
        name.setContentAreaFilled(false);
        name.setBorderPainted(false);
        name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // dropdown box
        dropdownBox = new JLabel("");
        dropdownBox.setBounds(1366 - 300, 70, 250, 330);
        dropdownBox.setBackground(MyColor.yellow);
        dropdownBox.setOpaque(true);
        dropdownBox.setVisible(false);
        isShowDropdown = false;

        // go home button
        goHomeButton = new JButton("Home");
        goHomeButton.setBounds(1366 - 280, 80, 210, 65);
        goHomeButton.setBackground(MyColor.white);
        goHomeButton.setForeground(MyColor.black);
        goHomeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        goHomeButton.setBorderPainted(false);
        goHomeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goHomeButton.setVisible(false);

        // my donations button
        myDonationsButton = new JButton("My Donations");
        myDonationsButton.setBounds(1366 - 280, 160, 210, 65);
        myDonationsButton.setBackground(MyColor.white);
        myDonationsButton.setForeground(MyColor.black);
        myDonationsButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        myDonationsButton.setBorderPainted(false);
        myDonationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myDonationsButton.setVisible(false);

        // my requests button
        myRequestsButton = new JButton("My Requests");
        myRequestsButton.setBounds(1366 - 280, 160, 210, 65);
        myRequestsButton.setBackground(MyColor.white);
        myRequestsButton.setForeground(MyColor.black);
        myRequestsButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        myRequestsButton.setBorderPainted(false);
        myRequestsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myRequestsButton.setVisible(false);

        // find donor button
        findDonorButton = new JButton("Find Donor");
        findDonorButton.setBounds(1366 - 280, 240, 210, 65);
        findDonorButton.setBackground(MyColor.white);
        findDonorButton.setForeground(MyColor.black);
        findDonorButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        findDonorButton.setBorderPainted(false);
        findDonorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        findDonorButton.setVisible(false);

        // logout button
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(1366 - 280, 320, 210, 65);
        logoutButton.setBackground(MyColor.white);
        logoutButton.setForeground(MyColor.black);
        logoutButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        logoutButton.setBorderPainted(false);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setVisible(false);

        // navbar adding
        navbarPanel.add(aiubText);
        navbarPanel.add(name);
        frame.add(goHomeButton);
        frame.add(myDonationsButton);
        frame.add(myRequestsButton);
        frame.add(findDonorButton);
        frame.add(logoutButton);
        frame.add(dropdownBox);

        // profile info in table view

        // table header
        boolean isProfileOwner = d.getAiubId().equals(u.getAiubId()) && u.getIsDonor();
        tableHeaderLabel = new JLabel(isProfileOwner ? "Your Details:" : "Details:");
        tableHeaderLabel.setBounds(100, 100, 400, 100);
        tableHeaderLabel.setFont(new Font("Arial", Font.BOLD, 30));
        tableHeaderLabel.setOpaque(true);
        tableHeaderLabel.setForeground(MyColor.black);

        // create data for the table
        Object[][] data = {
                { "  Name", "  " + d.getName() },
                { "  User Type", "  Donor" },
                { "  Blood Group", "  " + d.getBloodGroup() },
                { "  AIUB ID", "  " + d.getAiubId() },
                { "  Status", "  " + d.getStatus() },
                { "  Email", "  " + d.getEmail() },
                { "  Phone", "  " + d.getContact() },
                { "  Last Donate Date", "  " + d.getLastDonateDate() },
                { "  Total Donation", "  " + d.getTotalDonation() },
        };
        String[] columnNames = { "", "" };

        // create a table model with the data
        tableModel = new DefaultTableModel(data, columnNames);

        // create a JTable with the model
        table = new JTable(tableModel);
        table.setBounds(100, 200, 1180, 450);
        table.setFont(new Font("Arial", Font.BOLD, 22));
        table.setForeground(MyColor.white);
        table.setBackground(MyColor.darkBlue);
        table.setRowHeight(50);
        table.setEnabled(false);

        // adding
        frame.add(tableHeaderLabel);
        frame.add(table);

        // main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(navbarPanel, BorderLayout.NORTH);

        // scroll pane
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // adding to frame
        frame.add(scrollPane);

        // frame
        frame.setSize(1366, 768);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // action listeners

        // dropdown action
        name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isShowDropdown) {
                    hideDropdown();
                } else {
                    showDropdown();
                }
            }
        });

        // go home action
        goHomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                if (u.getIsDonor()) {
                    new DonorDashboard(Donor.login(u.getAiubId(), u.getPassword()));
                } else {
                    new RecipientDashboard(Recipient.login(u.getAiubId(), u.getPassword()));
                }
            }
        });

        // my requests action
        myRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyRequests(Recipient.login(u.getAiubId(), u.getPassword()));
            }
        });

        // my donations action
        myDonationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyDonations(Donor.login(u.getAiubId(), u.getPassword()));
            }
        });

        // find donor action
        findDonorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new DonorsList(u, null);
            }
        });

        // logout action
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Login();
            }
        });
    }

    // show dropwdown
    private void showDropdown() {
        isShowDropdown = true;
        dropdownBox.setVisible(true);
        goHomeButton.setVisible(true);
        findDonorButton.setVisible(true);
        logoutButton.setVisible(true);
        if (u.getIsDonor()) {
            myDonationsButton.setVisible(true);
        } else {
            myRequestsButton.setVisible(true);
        }
    }

    // hide dropdown
    private void hideDropdown() {
        isShowDropdown = false;
        dropdownBox.setVisible(false);
        goHomeButton.setVisible(false);
        findDonorButton.setVisible(false);
        logoutButton.setVisible(false);
        myDonationsButton.setVisible(false);
        myRequestsButton.setVisible(false);
    }
}
