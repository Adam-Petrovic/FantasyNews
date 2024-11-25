package use_case.update_rankings;

import entity.User;

public interface UpdateRankingsUserDataAccessInterface {
    public User get(String username);
}
