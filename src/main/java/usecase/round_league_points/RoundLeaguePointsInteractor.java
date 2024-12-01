package usecase.round_league_points;

import entity.User;

import java.util.ArrayList;

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
