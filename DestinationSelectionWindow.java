import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DestinationSelectionWindow {
    public static void showDestinationSelectionWindow() {
        JFrame frame = new JFrame("Select Destinations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Select " + CombinedApplication.numberOfDestinations + " Destinations", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        JList<String> destinationList = new JList<>(CombinedApplication.insertedAirports.toArray(new String[0]));
        destinationList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(destinationList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit Selection");
        panel.add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            List<String> selectedDestinations = destinationList.getSelectedValuesList();
            if (selectedDestinations.size() == CombinedApplication.numberOfDestinations) {
                CombinedApplication.insertedAirports.clear();
                CombinedApplication.insertedAirports.addAll(selectedDestinations);
                frame.dispose();
                DestinationVisitsWindow.showDestinationVisitsWindow();
            } else {
                JOptionPane.showMessageDialog(frame, "Please select exactly " + CombinedApplication.numberOfDestinations + " destinations.");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
