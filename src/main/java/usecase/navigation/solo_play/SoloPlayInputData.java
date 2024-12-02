package usecase.navigation.solo_play;

/**
 * Input data for solo play use case.
 */
public class SoloPlayInputData {

    private final String username;

    public SoloPlayInputData(String username) {
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
