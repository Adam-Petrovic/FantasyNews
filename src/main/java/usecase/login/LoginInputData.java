package usecase.login;

/**
 * The Input Data for the Login Use Case.
 */
public class LoginInputData {

    private final String username;
    private final String password;

    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username from the login.
     * @return the username from the login
     */
    String getUsername() {
        return username;
    }

    /**
     * Gets the password from the login.
     * @return  the password from the login
     */
    String getPassword() {
        return password;
    }

}
