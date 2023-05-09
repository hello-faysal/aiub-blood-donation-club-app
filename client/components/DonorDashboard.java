package client.components;

import client.assets.Color.MyColor;
import server.classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class DonorDashboard {
    private boolean isShowDropdown;

    JFrame frame;
    private JPanel mainPanel, navbarPanel, postPanel;
    private JScrollPane scrollPane;
    private ImageIcon favIcon, icon;
    // navbar
    private BufferedImage image;
    private JLabel aiubLogo;
    private JLabel logo;
    private JLabel aiubText;
    private JButton name;
    private JLabel dropdownBox;
    private JButton viewProfileButton;
    private JButton myDonationsButton;
    private JButton donorsListButton;
    private JButton logoutButton;

    private JLabel availablePostText;
    private JLabel noRequestText;
    private JLabel unavailableText;
    private JButton unavailableButton;

    public DonorDashboard(Donor d) {
        frame = new JFrame("Dashboard - AIUB BLOOD DONATION CLUB");

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
        name = new JButton("Welcome, " + d.getName().split(" ")[0], icon);
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
        dropdownBox.setBackground(MyColor.green);
        dropdownBox.setOpaque(true);
        dropdownBox.setVisible(false);
        isShowDropdown = false;

        // view profile button
        viewProfileButton = new JButton("View Profile");
        viewProfileButton.setBounds(1366 - 280, 80, 210, 65);
        viewProfileButton.setBackground(MyColor.white);
        viewProfileButton.setForeground(MyColor.black);
        viewProfileButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        viewProfileButton.setBorderPainted(false);
        viewProfileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewProfileButton.setVisible(false);

        // my donations button
        myDonationsButton = new JButton("My Donations");
        myDonationsButton.setBounds(1366 - 280, 160, 210, 65);
        myDonationsButton.setBackground(MyColor.white);
        myDonationsButton.setForeground(MyColor.black);
        myDonationsButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        myDonationsButton.setBorderPainted(false);
        myDonationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myDonationsButton.setVisible(false);

        // donors list button
        donorsListButton = new JButton("Donors List");
        donorsListButton.setBounds(1366 - 280, 240, 210, 65);
        donorsListButton.setBackground(MyColor.white);
        donorsListButton.setForeground(MyColor.black);
        donorsListButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        donorsListButton.setBorderPainted(false);
        donorsListButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        donorsListButton.setVisible(false);

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
        frame.add(viewProfileButton);
        frame.add(myDonationsButton);
        frame.add(donorsListButton);
        frame.add(logoutButton);
        frame.add(dropdownBox);

        // post panel
        postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBackground(MyColor.white);

        // unavailable button here to handle exception
        unavailableButton = new JButton("Be Available");

        // check availability
        if (d.getStatus().equals("Available")) {
            // available post text
            availablePostText = new JLabel(
                    "<html><br><p style=\"margin-left: 65px;\">Choose from available donations to save lives.</p><br></html>");
            availablePostText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
            availablePostText.setForeground(MyColor.black);

            postPanel.add(availablePostText);

            // add posts
            boolean hasPost = false;
            for (Post post : d.getPosts()) {
                PostGUI singlePost = new PostGUI(post, d);
                postPanel.add(singlePost);
                hasPost = true;
            }

            if (!hasPost) {
                postPanel.setLayout(null);

                noRequestText = new JLabel(
                        "No blood request available at the moment.Please check again later.",
                        SwingConstants.CENTER);
                noRequestText.setBounds(0, -50, 1366, 200);
                noRequestText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
                noRequestText.setForeground(MyColor.black);

                postPanel.add(noRequestText);
            }
        } else {
            postPanel.setLayout(null);

            unavailableText = new JLabel(
                    "Unfortunately, you are unable to donate blood at this time.Make yourself available to donate.",
                    SwingConstants.CENTER);
            unavailableText.setBounds(0, -50, 1366, 200);
            unavailableText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
            unavailableText.setForeground(MyColor.black);
            unavailableText.setBackground(MyColor.white);

            unavailableButton = new JButton("Be Available");
            unavailableButton.setBounds(530, 100, 300, 50);
            unavailableButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
            unavailableButton.setBackground(MyColor.green);
            unavailableButton.setForeground(MyColor.white);
            unavailableButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            postPanel.add(unavailableText);
            postPanel.add(unavailableButton);
        }

        // main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(navbarPanel, BorderLayout.NORTH);
        mainPanel.add(postPanel, BorderLayout.CENTER);

        // scroll pane
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getVerticalScrollBar().setBlockIncrement(200);

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

        // view profile action
        viewProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new DonorsProfile(d, d);
            }
        });

        // my donations action
        myDonationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyDonations(d);
            }
        });

        // donors list action
        donorsListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new DonorsList(d, null);
            }
        });

        // logout action
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Login();
            }
        });

        // make available action
        unavailableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.setStatus("Available");
                frame.setVisible(false);
                new DonorDashboard(d);
            }
        });
    }

    // show dropwdown
    private void showDropdown() {
        isShowDropdown = true;
        dropdownBox.setVisible(true);
        viewProfileButton.setVisible(true);
        myDonationsButton.setVisible(true);
        donorsListButton.setVisible(true);
        logoutButton.setVisible(true);
    }

    // hide dropdown
    private void hideDropdown() {
        isShowDropdown = false;
        dropdownBox.setVisible(false);
        viewProfileButton.setVisible(false);
        myDonationsButton.setVisible(false);
        donorsListButton.setVisible(false);
        logoutButton.setVisible(false);
    }

    // post gui component
    private class PostGUI extends JPanel {
        private JLabel postText;
        private JLabel dateLabel;
        private JTextField dateField;
        private JLabel timeLabel;
        private JTextField timeField;
        private JLabel locationLabel;
        private JTextField locationField;
        private JLabel bloodGroupLabel;
        private JTextField bloodGroupField;
        private JLabel descriptionLabel;
        private JTextArea descriptionTextArea;
        private JButton viewRecipientButton;
        private JButton donateBloodButton;

        public PostGUI(Post post, Donor d) {
            setLayout(null);

            // post text
            postText = new JLabel(
                    "Be a hero today: Donate " + post.getRequiredBloodGroup() + " blood to help "
                            + Post.getAuthor(post.getAuthorId()).getName().split(" ")[0] + " ["
                            + Post.getAuthor(post.getAuthorId()).getAiubId() + "]");
            postText.setBounds(80, 60, 1200, 50);
            postText.setForeground(MyColor.white);
            postText.setFont(new Font("Arial", Font.BOLD, 30));

            // date label and field
            dateLabel = new JLabel("Date: ");
            dateLabel.setBounds(80, 130, 100, 50);
            dateLabel.setForeground(MyColor.white);
            dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            dateField = new JTextField("");
            dateField.setBounds(80, 170, 590, 50);
            dateField.setForeground(MyColor.black);
            dateField.setFont(new Font("Arial", Font.PLAIN, 18));
            dateField.setText(post.getDate());
            dateField.setEditable(false);

            // time label and field
            timeLabel = new JLabel("Time: ");
            timeLabel.setBounds(695, 130, 100, 50);
            timeLabel.setForeground(MyColor.white);
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            timeField = new JTextField("");
            timeField.setBounds(695, 170, 590, 50);
            timeField.setForeground(MyColor.black);
            timeField.setFont(new Font("Arial", Font.PLAIN, 18));
            timeField.setText(post.getTime());
            timeField.setEditable(false);

            // location label and field
            locationLabel = new JLabel("Location: ");
            locationLabel.setBounds(80, 220, 100, 50);
            locationLabel.setForeground(MyColor.white);
            locationLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            locationField = new JTextField("");
            locationField.setBounds(80, 260, 590, 50);
            locationField.setForeground(MyColor.black);
            locationField.setFont(new Font("Arial", Font.PLAIN, 18));
            locationField.setText(post.getLocation());
            locationField.setEditable(false);

            // time label and field
            bloodGroupLabel = new JLabel("Blood Group: ");
            bloodGroupLabel.setBounds(695, 220, 300, 50);
            bloodGroupLabel.setForeground(MyColor.white);
            bloodGroupLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            bloodGroupField = new JTextField("");
            bloodGroupField.setBounds(695, 260, 590, 50);
            bloodGroupField.setForeground(MyColor.black);
            bloodGroupField.setFont(new Font("Arial", Font.PLAIN, 18));
            bloodGroupField.setText(post.getRequiredBloodGroup());
            bloodGroupField.setEditable(false);

            // description label and text area
            descriptionLabel = new JLabel("Description:");
            descriptionLabel.setBounds(80, 310, 300, 50);
            descriptionLabel.setForeground(MyColor.white);
            descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            descriptionTextArea = new JTextArea("");
            descriptionTextArea.setBounds(80, 350, 1200, 100);
            descriptionTextArea.setForeground(MyColor.black);
            descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
            descriptionTextArea.setWrapStyleWord(true);
            descriptionTextArea.setLineWrap(true);
            descriptionTextArea.setText(post.getDescription());
            descriptionTextArea.setEditable(false);

            // view recipient button
            viewRecipientButton = new JButton("View Recipient");
            viewRecipientButton.setBounds(480, 490, 200, 50);
            viewRecipientButton.setForeground(MyColor.white);
            viewRecipientButton.setBackground(MyColor.darkRed);
            viewRecipientButton.setFont(new Font("Arial", Font.BOLD, 18));
            viewRecipientButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // donate blood button
            donateBloodButton = new JButton("Donate Blood");
            donateBloodButton.setBounds(690, 490, 200, 50);
            donateBloodButton.setForeground(MyColor.white);
            donateBloodButton.setBackground(MyColor.green);
            donateBloodButton.setFont(new Font("Arial", Font.BOLD, 18));
            donateBloodButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // adding
            add(postText);
            add(dateLabel);
            add(dateField);
            add(timeLabel);
            add(timeField);
            add(locationLabel);
            add(locationField);
            add(bloodGroupLabel);
            add(bloodGroupField);
            add(descriptionLabel);
            add(descriptionTextArea);
            add(viewRecipientButton);
            add(donateBloodButton);

            // panel
            setBackground(MyColor.darkBlue);
            setBorder(BorderFactory.createLineBorder(MyColor.white));
            setPreferredSize(new Dimension(1366, 610));

            // action listeners

            // view recipient action
            viewRecipientButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    new RecipientsProfile(Post.getAuthor(post.getAuthorId()), d);
                }
            });

            // donate blood action
            donateBloodButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (d.donateBlood(post.getPostId())) {
                        JOptionPane.showMessageDialog(null,
                                "<html><center><font size='5' color='green'>Thank you for your life-saving donation! Your generosity and kindness will make a difference in someone's life. You're a hero in our eyes!</font></center></html>",
                                "", JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        new DonorDashboard(d);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "<html><center><font size='5' color='red'><b>Oops!</b> Something went wrong with the blood donation. Please give it another go in a bit. Thank you for your life-saving kindness!</font></center></html>",
                                "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
}
