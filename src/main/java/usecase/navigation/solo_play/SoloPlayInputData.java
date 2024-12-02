package usecase.navigation.solo_play;

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
