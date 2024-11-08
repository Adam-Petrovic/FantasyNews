package entity;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<User> friends;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friends = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<User> getFriends() {
        return friends;
    }
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
    public void addFriend(User user) {
        this.friends.add(user);
    }
}
