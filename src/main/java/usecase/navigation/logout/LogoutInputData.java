package usecase.navigation.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private String username;

    public LogoutInputData(String username) {
        this.username = username;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

}
