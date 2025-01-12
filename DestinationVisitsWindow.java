import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DestinationVisitsWindow {
    public static void showDestinationVisitsWindow() {
        JFrame frame = new JFrame("Number of Visits for Each Destination");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(CombinedApplication.numberOfDestinations + 1, 2));
        JTextField[] visitFields = new JTextField[CombinedApplication.numberOfDestinations];

        for (int i = 0; i < CombinedApplication.numberOfDestinations; i++) {
            panel.add(new JLabel(CombinedApplication.insertedAirports.get(i))); // Show destination names
            visitFields[i] = new JTextField("1"); // Default value for visits
            panel.add(visitFields[i]);
        }

        JButton submitButton = new JButton("Submit Visits");
        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);

        // Submit button action listener
        submitButton.addActionListener(e -> {
            try {
                int[] visits = new int[CombinedApplication.numberOfDestinations];
                int totalVisits = 0;

                // Parse and validate input
                for (int i = 0; i < CombinedApplication.numberOfDestinations; i++) {
                    visits[i] = Integer.parseInt(visitFields[i].getText());
                    if (visits[i] <= 0) {
                        JOptionPane.showMessageDialog(frame, "Each destination must have at least 1 visit!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    totalVisits += visits[i];
                }

                // Validate total visits against available airplane slots
                if (totalVisits <= CombinedApplication.numberOfAirplanes * 3) {
                    JOptionPane.showMessageDialog(frame, "Visits successfully submitted! Total visits: " + totalVisits);
                    frame.dispose(); // Close the current frame
                    CombinedApplication.calculateDistancesAndAssignments(visits); // Proceed to next step
                } else {
                    JOptionPane.showMessageDialog(frame, "Total visits exceed the available airplane slots!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for visits!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
