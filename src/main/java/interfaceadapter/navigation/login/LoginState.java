package interfaceadapter.navigation.login;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String username = "";
    private String loginError;
    private String password = "";

    /**
     * Gets the username of the user logged in.
     * @return the username of the user logged in
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the Login Error in string format.
     * @return the Login Error in string format
     */
    public String getLoginError() {
        return loginError;
    }

    /**
     * Gets the current logged in user's password.
     * @return the current logged in user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username of the user to be logged in.
     * @param username the username of the user to be logged in
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the Login error to be displayed.
     * @param usernameError the Login error to be displayed
     */
    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    /**
     * Sets the password for the user.
     * @param password the password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
