package Model;

import java.util.List;
import java.util.ArrayList;
import Model.User;

public class UserList {
    private static List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User("admin", "123", "admin@example.com"));
        users.add(new User("user1", "abc", "user1@example.com"));
        // Thêm user khác nếu muốn
    }

    public static User findUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
