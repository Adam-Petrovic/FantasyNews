package usecase.pointsuserstory.round_league_points;

import java.util.ArrayList;

import entity.User;

/**
 * Interactor for rounding points for league.
 */
public class RoundLeaguePointsInteractor implements RoundLeaguePointsInputBoundary {

    private RoundLeaguePointsDataAccessObject roundLeaguePointsDataAccessObject;

    public RoundLeaguePointsInteractor(RoundLeaguePointsDataAccessObject roundLeaguePointsDataAccessObject) {
        this.roundLeaguePointsDataAccessObject = roundLeaguePointsDataAccessObject;
    }

    @Override
    public void roundUp(RoundLeaguePointsInputData roundLeaguePointsInputData) {
        String leagueID = roundLeaguePointsInputData.getLeagueID();
        ArrayList<User> users = roundLeaguePointsInputData.getUsers();
        roundLeaguePointsDataAccessObject.roundPoints(leagueID, users);
    }
}
