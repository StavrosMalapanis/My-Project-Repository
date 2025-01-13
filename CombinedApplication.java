package com.codewarriors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.codewarriors.airport.*;
import com.codewarriors.calculation.*;
import com.codewarriors.users.*;

public class CombinedApplication {
    public static List<Airport> airports = new ArrayList<>();
    public static List<String> insertedAirports = new ArrayList<>();
    public static int numberOfAirplanes = 0;
    public static int numberOfDestinations = 0;
    private static User loggedInUser;

    public static void main(String[] args) {
        User.initializeDefaultUsers();
        initializeDefaultAirports();
        initializeDefaultDestinations();
        SwingUtilities.invokeLater(LoginWindow::showLoginWindow);
    }
   
    private static void initializeDefaultDestinations() {
        String[] defaultDestinations = {
            "1. Αθήνα", "2. Παρίσι", "3. Λονδίνο", "4. Μιλάνο", "5. Βρυξέλλες",
            "6. Βερολίνο", "7. Στοκχόλμη", "8. Όσλο", "9. Μαδρίτη", "10. Άμστερνταμ"
        };
        for (String destination : defaultDestinations) {
            insertedAirports.add(destination);
        }
    }
   

    private static void initializeDefaultAirports() {
        airports.add(new Airport("JFK", "USA", "New York", 40.6413, -73.7781));
        airports.add(new Airport("LAX", "USA", "Los Angeles", 33.9416, -118.4085));
    }
    public static void manageDestinations() {
        JFrame frame = new JFrame("Manage Destinations");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
   
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
   
        // Dimiourgia pinaka aerodromiwn
        for (Airport airport : airports) {
            panel.add(new JLabel(airport.getCity() + " - " + airport.getCountry()));
        }
   
        JButton addDestinationButton = new JButton("Add Destination");
        panel.add(addDestinationButton);
   
        frame.add(panel, BorderLayout.CENTER);
   
       
        addDestinationButton.addActionListener(e -> {
            JTextField codeField = new JTextField();
            JTextField countryField = new JTextField();
            JTextField cityField = new JTextField();
            JTextField latitudeField = new JTextField();
            JTextField longitudeField = new JTextField();
   
            int result = JOptionPane.showConfirmDialog(
                null,
                new Object[] {
                    "Code:", codeField,
                    "Country:", countryField,
                    "City:", cityField,
                    "Latitude:", latitudeField,
                    "Longitude:", longitudeField
                },
                "Add New Destination",
                JOptionPane.OK_CANCEL_OPTION
            );

        if (result == JOptionPane.OK_OPTION) {
                try {
                    String code = codeField.getText().trim();
                    String country = countryField.getText().trim();
                    String city = cityField.getText().trim();
                    double latitude = Double.parseDouble(latitudeField.getText().trim());
                    double longitude = Double.parseDouble(longitudeField.getText().trim());
    
                    if (code.isEmpty() || country.isEmpty() || city.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Airport newAirport = new Airport(code, country, city, latitude, longitude);
                        airports.add(newAirport);
                        JOptionPane.showMessageDialog(frame, "New destination added: " + city + ", " + country);
                        frame.dispose();
                        manageDestinations(); // Refresh the list of destinations
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid latitude or longitude!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setVisible(true);
    }
  
    //parathiro gia eisagwgi arithmou aeroplanwn

    public static void showNumberOfAirplanesWindow() {
        JFrame frame = new JFrame("Number of Airplanes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
    
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(new JLabel("Enter the number of airplanes:"));
    
        JTextField inputField = new JTextField();
        panel.add(inputField);
    
        JButton submitButton = new JButton("Submit");
        frame.add(panel, BorderLayout.CENTER);
        frame.add(submitButton, BorderLayout.SOUTH);
    
        submitButton.addActionListener(e -> {
            try {
                numberOfAirplanes = Integer.parseInt(inputField.getText());
                if (numberOfAirplanes > 0) {
                    JOptionPane.showMessageDialog(frame, "Number of airplanes saved: " + numberOfAirplanes);
                    frame.dispose();
                    showNumberOfDestinationsWindow();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a positive number!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
            }
        });
    
        frame.setVisible(true);
    }
    public static void addNewDestination(String destination) {
        if (destination != null && !destination.trim().isEmpty()) {
            insertedAirports.add(destination); // Add to the list of destinations
            JOptionPane.showMessageDialog(null, "New destination added: " + destination);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid destination!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //logout
    
    public static void logout(JFrame currentFrame) {
        loggedInUser = null; // Clear the logged-in user
        currentFrame.dispose(); // Close the current frame
        SwingUtilities.invokeLater(LoginWindow::showLoginWindow); // Redirect to login window
    }
