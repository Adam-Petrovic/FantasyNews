package interface_adapter.signup;

/**
 * The state for the Signup View Model.
 */
public class SignupState {
    private String username = "";
    private String usernameError;
    private String password = "";
    private String passwordError;
    private String repeatPassword = "";
    private String repeatPasswordError;

    /**
     * Gets the username .
     * @return  the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the Username error.
     * @return gets the Username error
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * GetsGets the Username error.
     * @return Gets the Username error
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the password error.
     * @return the password error
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Gets the repeated password.
     * @return the repeated password
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Gets the repeat password error.
     * @return the repeat password error
     */
    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    /**
     * Sets the username for the user.
     * @param username the repeat passsword error
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the username error.
     * @param usernameError the username error.
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Sets the password for the user.
     * @param password the password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the password error.
     * @param passwordError the password error
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Sets the repeat password.
     * @param repeatPassword the repeat password
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Sets the repeat password error.
     * @param repeatPasswordError the repeat password
     */
    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    @Override
    public String toString() {
        return "SignupState{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + '}';
    }
}
