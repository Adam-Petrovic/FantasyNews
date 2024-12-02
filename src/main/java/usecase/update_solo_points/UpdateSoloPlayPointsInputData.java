package usecase.update_solo_points;

import entity.User;

public class UpdateSoloPlayPointsInputData {
    private final User user;

    public UpdateSoloPlayPointsInputData(User user) {
        this.user = user;
    }

    /**
     * Gets the user.
     * @return the user
     */
    public User getUser() {
        return user;
    }
}
