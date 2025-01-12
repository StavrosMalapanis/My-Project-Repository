import javax.swing.*;

public class AdminMenu {
    public static void showAdminMenu() {
        JFrame frame = new JFrame("Admin Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JButton manageDestinationsButton = new JButton("Manage Destinations");
        JButton setAirplanesButton = new JButton("Set Number of Airplanes");
        JButton addUserButton = new JButton("Add User");

        // Ensure logoutButton is defined only once
        JButton logoutButton = new JButton("Logout"); 
        logoutButton.addActionListener(e -> {
            CombinedApplication.logout(frame); // Call the logout method
        });

        panel.add(manageDestinationsButton);
        panel.add(setAirplanesButton);
        panel.add(addUserButton);
        panel.add(logoutButton); // Add the logout button to the panel

        manageDestinationsButton.addActionListener(e -> {
            frame.dispose();
            CombinedApplication.manageDestinations();
        });

        setAirplanesButton.addActionListener(e -> {
            CombinedApplication.setAirplanes();
        });

        addUserButton.addActionListener(e -> {
            UserManagement.addUser();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
