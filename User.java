import java.util.ArrayList;
import java.util.List;

public class User {
    private String username, password, role;
    public static List<User> users = new ArrayList<>();

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public static void initializeDefaultUsers() {
        users.add(new User("admin", "admin123", "admin"));
        users.add(new User("user", "user123", "user"));
    }

    public static User authenticate(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
