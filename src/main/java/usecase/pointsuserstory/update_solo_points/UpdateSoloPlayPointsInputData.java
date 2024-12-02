package usecase.pointsuserstory.update_solo_points;

import entity.User;

/**
 * Input data for updating points in solo play mode.
 */
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
