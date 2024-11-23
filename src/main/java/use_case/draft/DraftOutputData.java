package use_case.draft;

import entity.User;

public class DraftOutputData {
    private final User user;

    public DraftOutputData(User user) {
        this.user = user;
    }

    public User getUser() { return this.user; }
}
