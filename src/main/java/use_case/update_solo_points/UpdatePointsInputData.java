package use_case.update_solo_points;

import entity.User;

public class UpdatePointsInputData {
    private final User user;

    public UpdatePointsInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
