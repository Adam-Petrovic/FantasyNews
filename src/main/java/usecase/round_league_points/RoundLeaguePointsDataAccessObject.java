package usecase.round_league_points;

import entity.User;

import java.util.ArrayList;

public interface RoundLeaguePointsDataAccessObject {
    void roundPoints(String leagueID, ArrayList<User> users);
}
