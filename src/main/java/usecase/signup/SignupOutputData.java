package usecase.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public SignupOutputData(String username, boolean useCaseFailed) {
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
     * If the usecase has had an error/failed in any way.
     * @return true if failed; false otherwise
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
