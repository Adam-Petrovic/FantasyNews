package use_case.to_draft;

import entity.User;

public class ToDraftOutputData {
    private final User user;

    public ToDraftOutputData(User user) {
        this.user = user;
    }

    public User getUser() { return this.user; }
}
