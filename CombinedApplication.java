import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    
        // Display existing destinations
        for (Airport airport : airports) {
            panel.add(new JLabel(airport.getCity() + " - " + airport.getCountry()));
        }
    
        JButton addDestinationButton = new JButton("Add Destination");
        panel.add(addDestinationButton);
    
        frame.add(panel, BorderLayout.CENTER);
    
        // Add destination functionality
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
    
    public static void setAirplanes() {
        String input = JOptionPane.showInputDialog("Enter number of airplanes:");
        try {
            numberOfAirplanes = Integer.parseInt(input);
            JOptionPane.showMessageDialog(null, "Airplanes set to: " + numberOfAirplanes);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number!");
        }
    }
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
    
    public static void logout(JFrame currentFrame) {
        loggedInUser = null; // Clear the logged-in user
        currentFrame.dispose(); // Close the current frame
        SwingUtilities.invokeLater(LoginWindow::showLoginWindow); // Redirect to login window
    }
    
    public static void showNumberOfDestinationsWindow() {
        JFrame frame = new JFrame("Number of Destinations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
    
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(new JLabel("Enter the number of destinations:"));
    
        JTextField inputField = new JTextField();
        panel.add(inputField);
    
        JButton submitButton = new JButton("Submit");
        frame.add(panel, BorderLayout.CENTER);
        frame.add(submitButton, BorderLayout.SOUTH);
    
        submitButton.addActionListener(e -> {
            try {
                numberOfDestinations = Integer.parseInt(inputField.getText());
                if (numberOfDestinations > 0 && numberOfDestinations <= 10) {
                    JOptionPane.showMessageDialog(frame, "Number of destinations saved: " + numberOfDestinations);
                    frame.dispose();
                    DestinationSelectionWindow.showDestinationSelectionWindow();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a number between 1 and 10!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
            }
        });
    
        frame.setVisible(true);
    }
    public static void calculateDistancesAndAssignments(int[] visits) {
        double[][] destinationCoordinates = new double[numberOfDestinations][2];
        String[] mockLatitudes = {"37.9838", "48.8566", "51.5074", "45.4642", "50.8503", "52.5200", "59.3293", "59.9139", "40.4168", "52.3676"};
        String[] mockLongitudes = {"23.7275", "2.3522", "-0.1278", "9.1900", "4.3517", "13.4050", "18.0686", "10.7522", "-3.7038", "4.9041"};
    
        for (int i = 0; i < numberOfDestinations; i++) {
            destinationCoordinates[i][0] = Double.parseDouble(mockLatitudes[i]);
            destinationCoordinates[i][1] = Double.parseDouble(mockLongitudes[i]);
        }
    
        int[][] distanceMatrix = new int[numberOfDestinations][numberOfDestinations];
    
        for (int i = 0; i < numberOfDestinations; i++) {
            for (int j = i + 1; j < numberOfDestinations; j++) {
                double distance = HaversineDistance.haversine(
                    destinationCoordinates[i][0], destinationCoordinates[i][1],
                    destinationCoordinates[j][0], destinationCoordinates[j][1]
                );
                distanceMatrix[i][j] = (int) Math.round(distance);
                distanceMatrix[j][i] = distanceMatrix[i][j];
            }
        }
    
        assignDestinationsToAirplanes(distanceMatrix, visits);
    }
    
    public static void assignDestinationsToAirplanes(int[][] distanceMatrix, int[] visits) {
        int[][] airplaneAssignments = new int[numberOfAirplanes][3]; // Each airplane gets 3 destinations
        int[] remainingVisits = visits.clone(); // Clone the visits array to track remaining visits
    
        for (int airplane = 0; airplane < numberOfAirplanes; airplane++) {
            int lastLocation = -1; // Initialize to -1 (no last location at the start)
            double totalDistance = 0;
            StringBuilder result = new StringBuilder("Airplane " + (airplane + 1) + " visits: ");
    
            for (int assignment = 0; assignment < 3; assignment++) {
                int nearestDestination = -1;
                int minimumDistance = Integer.MAX_VALUE;
    
                // Find the nearest destination with remaining visits
                for (int destination = 0; destination < numberOfDestinations; destination++) {
                    if (remainingVisits[destination] > 0 && destination != lastLocation &&
                        distanceMatrix[lastLocation == -1 ? 0 : lastLocation][destination] < minimumDistance) {
                        nearestDestination = destination;
                        minimumDistance = distanceMatrix[lastLocation == -1 ? 0 : lastLocation][destination];
                    }
                }
    
                // Assign the nearest destination to the airplane
                if (nearestDestination != -1) {
                    airplaneAssignments[airplane][assignment] = nearestDestination;
                    remainingVisits[nearestDestination]--; // Decrease the remaining visits
                    totalDistance += minimumDistance;
                    lastLocation = nearestDestination; // Update last location
    
                    result.append(insertedAirports.get(nearestDestination)).append(", ");
                } else {
                    result.append("No more destinations available, ");
                }
            }
    
            // Remove trailing comma and space
            if (result.length() > 0) {
                result.setLength(result.length() - 2);
            }
    
            result.append("\nTotal Distance: ").append(totalDistance).append(" km");
    
            JOptionPane.showMessageDialog(null, result.toString(), "Airplane Assignments", JOptionPane.INFORMATION_MESSAGE);
        }
    
        // Check if any destinations still need visits
        for (int destination = 0; destination < numberOfDestinations; destination++) {
            if (remainingVisits[destination] > 0) {
                JOptionPane.showMessageDialog(null, "Remaining visits for " +
                        insertedAirports.get(destination) + ": " + remainingVisits[destination],
                        "Unfulfilled Visits", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

