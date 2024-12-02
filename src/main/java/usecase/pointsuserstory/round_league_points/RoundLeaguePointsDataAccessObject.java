package usecase.pointsuserstory.round_league_points;

import java.util.ArrayList;

import entity.User;


public interface RoundLeaguePointsDataAccessObject {
    /**
     * The method to round points.
     * @param leagueID the league where we need to round points
     * @param users an ArrayList of users to round
     */
    void roundPoints(String leagueID, ArrayList<User> users);
}
