package use_case.solo_play;

import entity.User;

public class SoloPlayOutputData {

    private final User user;

    public SoloPlayOutputData(User user) {
        this.user = user;
    }

    public User getUser() { return this.user; }
}
