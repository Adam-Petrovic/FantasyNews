package usecase.navigation.solo_play;

import entity.User;

/**
 * Output data for solo play use case.
 */
public class SoloPlayOutputData {

    private final User user;

    public SoloPlayOutputData(User user) {
        this.user = user;
    }

    /**
     * Gets the user.
     * @return the user
     */
    public User getUser() {
        return this.user;
    }
}
