import javax.swing.*;

public class UserManagement {
    public static void addUser() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField roleField = new JTextField();

        int result = JOptionPane.showConfirmDialog(
                null,
                new Object[]{"Username:", usernameField, "Password:", passwordField, "Role (admin/user):", roleField},
                "Add New User",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String role = roleField.getText().trim();

            if (username.isEmpty() || password.isEmpty() || (!role.equals("admin") && !role.equals("user"))) {
                JOptionPane.showMessageDialog(null, "Invalid input. All fields must be filled correctly.");
            } else {
                User newUser = new User(username, password, role);
                User.users.add(newUser);
                JOptionPane.showMessageDialog(null, "New user added successfully: " + username);
            }
        }
    }
}
