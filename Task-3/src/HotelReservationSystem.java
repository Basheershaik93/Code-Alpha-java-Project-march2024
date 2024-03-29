import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class HotelReservationSystem extends JFrame {

    //private List<Reservation> reservations;
    private JPanel mainPanel, contentPanel;
    private JLabel imageLabel;
    private JButton searchButton, reserveButton, viewDetailsButton,saveButton;
    private JTextField searchField, nameField, dateField, phoneField, amountField;
    private JComboBox<String> roomTypeComboBox;
    private List<Reservation> reservations;


    public HotelReservationSystem() {
        setTitle("Hotel Reservation System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        mainPanel = new JPanel(new BorderLayout());

        // Navigation bar
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel title = new JLabel("Hotel Reservation System");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        navBar.add(title);

        // Buttons on the navigation bar
        searchButton = new JButton("Search Rooms");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show search panel
                showSearchPanel();
            }
        });
        reserveButton = new JButton("Make Reservation");
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show reservation panel
                showReservationPanel();
            }
        });
        viewDetailsButton = new JButton("View Booking Details");
        viewDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show booking details panel
                showBookingDetailsPanel();
            }
        });
        navBar.add(searchButton);
        navBar.add(reserveButton);
        navBar.add(viewDetailsButton);

        mainPanel.add(navBar, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());

        // Load and display the main image
        BufferedImage mainImage = loadImage("Hotel.jpg");
        if (mainImage != null) {
            ImageIcon mainIcon = new ImageIcon(mainImage);
            imageLabel = new JLabel(mainIcon);
            contentPanel.add(imageLabel, BorderLayout.CENTER);
        } else {
            JLabel errorLabel = new JLabel("Failed to load main image.");
            errorLabel.setHorizontalAlignment(JLabel.CENTER);
            contentPanel.add(errorLabel, BorderLayout.CENTER);
        }

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);

        // Initialize list to store reservations
        reservations = new ArrayList<>();
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void showSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout());

        // Load and display the search image
        BufferedImage searchImage = loadImage("Rooms.jpg");
        if (searchImage != null) {
            JLabel searchImageLabel = new JLabel(new ImageIcon(searchImage));
            searchPanel.add(searchImageLabel, BorderLayout.CENTER);
        }

        JPanel searchOptions = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement logic to search for rooms
                String searchTerm = searchField.getText();
                if (searchTerm.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a search term.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Simulate searching logic
                    boolean roomAvailable = searchForRoom(searchTerm);
                    if (roomAvailable) {
                        JOptionPane.showMessageDialog(null, "Room is available.", "Room Availability", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Room is not available.", "Room Availability", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        searchOptions.add(new JLabel("Search for room: "));
        searchOptions.add(searchField);
        searchOptions.add(searchButton);

        searchPanel.add(searchOptions, BorderLayout.SOUTH);

        setContentPanel(searchPanel);
    }

    private boolean searchForRoom(String searchTerm) {
        try {
            int roomNumber = Integer.parseInt(searchTerm);
            return roomNumber >= 101 && roomNumber <= 109;
        } catch (NumberFormatException e) {
            // Invalid input, so room is not available
            return false;
        }
        
    }

    private void showReservationPanel() {
        JPanel reservePanel = new JPanel(new BorderLayout());
    
        // Load and display the image for reservation panel
        ImageIcon imageIcon = new ImageIcon("Reservation_Rooms.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        reservePanel.add(imageLabel, BorderLayout.CENTER);
    
        // Form for reservation details
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);
    
        formPanel.add(new JLabel("Date:"));
        dateField = new JTextField();
        formPanel.add(dateField);
    
        formPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);
    
        formPanel.add(new JLabel("Room Type:"));
        String[] roomTypes = {"Single", "Double", "Suite"};
        roomTypeComboBox = new JComboBox<>(roomTypes);
        roomTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Populate amount field based on room type
                String selectedRoomType = (String) roomTypeComboBox.getSelectedItem();
                double amount = getAmountForRoomType(selectedRoomType);
                amountField.setText(String.valueOf(amount));
            }
        });
        formPanel.add(roomTypeComboBox);
    
        formPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        formPanel.add(amountField);
    
        reservePanel.add(formPanel, BorderLayout.CENTER);
    
        // Save button
        saveButton = new JButton("Save and Proceed");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Save reservation details
                saveReservation();
            }
        });
        reservePanel.add(saveButton, BorderLayout.SOUTH);
    
        setContentPanel(reservePanel);
    }
    
    // Method to get the amount for the selected room type
    private double getAmountForRoomType(String roomType) {
        switch (roomType) {
            case "Single":
                return 500.0;
            case "Double":
                return 800.0;
            case "Suite":
                return 1200.0;
            default:
                return 0.0; // Default to 0 if room type is not recognized
        }
    }

    private void showBookingDetailsPanel() {
        JPanel bookingDetailsPanel = new JPanel(new GridLayout(reservations.size() + 1, 1));

        // Add labels for column headers
        JPanel columnHeaderPanel = new JPanel(new GridLayout(1, 5));
        columnHeaderPanel.add(new JLabel("Name"));
        columnHeaderPanel.add(new JLabel("Date"));
        columnHeaderPanel.add(new JLabel("Phone Number"));
        columnHeaderPanel.add(new JLabel("Room Type"));
        columnHeaderPanel.add(new JLabel("Amount"));
        bookingDetailsPanel.add(columnHeaderPanel);

        // Add reservation details to the panel
        for (Reservation reservation : reservations) {
            JPanel reservationPanel = new JPanel(new GridLayout(1, 5));
            reservationPanel.add(new JLabel(reservation.getName()));
            reservationPanel.add(new JLabel(reservation.getDate()));
            reservationPanel.add(new JLabel(reservation.getPhoneNumber()));
            reservationPanel.add(new JLabel(reservation.getRoomType()));
            reservationPanel.add(new JLabel(String.valueOf(reservation.getAmount())));
            bookingDetailsPanel.add(reservationPanel);
        }

        // Show booking details panel
        setContentPanel(bookingDetailsPanel);
    }

    private void setContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void saveReservation() {
        // Get the input data
        String name = nameField.getText();
        String date = dateField.getText();
        String phoneNumber = phoneField.getText();
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

         // Create a Reservation object
        Reservation reservation = new Reservation(name, date, phoneNumber, roomType, amount);

        // Add the reservation to the list
        reservations.add(reservation);

         // Save the reservation to the file
        saveReservation(reservation);

        // Confirmation message
        JOptionPane.showMessageDialog(this, "Reservation saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to save reservation to a text file
    private void saveReservation(Reservation reservation) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("reservations.txt", true))) {
            writer.println(reservation.toFileString());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save reservation.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HotelReservationSystem();
            }
        });
    }
    static class Reservation {
        private String name;
        private String date;
        private String phoneNumber;
        private String roomType;
        private double amount;

        public Reservation(String name, String date, String phoneNumber, String roomType, double amount) {
            this.name = name;
            this.date = date;
            this.phoneNumber = phoneNumber;
            this.roomType = roomType;
            this.amount = amount;
        }
        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getRoomType() {
            return roomType;
        }

        public double getAmount() {
            return amount;
        }
        public String toFileString() {
            return name + "," + date + "," + phoneNumber + "," + roomType + "," + amount;
        }
    }
}

