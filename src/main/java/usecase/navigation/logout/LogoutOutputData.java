package usecase.navigation.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {

    private String username;
    private boolean useCaseFailed;

    public LogoutOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if there has been any errors in the process of logging in.
     * @return true if failed; false if not
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
