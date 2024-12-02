package usecase.navigation.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;

    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Gets the username.
     * @return the username
     */
    String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     * @return the password
     */
    String getPassword() {
        return password;
    }

    /**
     * Gets the repeated password.
     * @return the repeated password
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }
}
