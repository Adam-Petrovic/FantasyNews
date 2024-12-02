package interfaceadapter.navigation.loggedIn;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private String username = "";

    private String password = "";
    private String passwordError;

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {

    }

    /**
     * Return the user's username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's name to the parameter's value.
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets user's password to the parameter's value.
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets passwordError field to the parameter's value.
     * @param passwordError password error
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Returns the user's password.
     * @return password.
     */
    public String getPassword() {
        return password;
    }
}
