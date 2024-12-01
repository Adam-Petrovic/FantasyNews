package interface_adapter.award_league_points;

import entity.User;
import usecase.award_league_points.AwardLeaguePointsInputBoundary;
import usecase.award_league_points.AwardLeaguePointsInputData;
import usecase.round_league_points.RoundLeaguePointsInputBoundary;
import usecase.round_league_points.RoundLeaguePointsInputData;

import java.util.ArrayList;


public class AwardLeaguePointsController {

    private final RoundLeaguePointsInputBoundary roundLeagueInteractor;
    private AwardLeaguePointsInputBoundary awardLeaguePointsInteractor;


    public AwardLeaguePointsController(AwardLeaguePointsInputBoundary awardLeaguePointsInteractor,
                                       RoundLeaguePointsInputBoundary roundLeagueInteractor) {
        this.awardLeaguePointsInteractor = awardLeaguePointsInteractor;
        this.roundLeagueInteractor = roundLeagueInteractor;
    }

    public void awardPoints(String leagueID, ArrayList<User> users){
        AwardLeaguePointsInputData awardLeaguePointsInputData = new AwardLeaguePointsInputData(leagueID, users);
        awardLeaguePointsInteractor.execute(awardLeaguePointsInputData);
    }

    public void roundUp(String leagueID, ArrayList<User> users){
        RoundLeaguePointsInputData roundLeaguePointsInputData = new RoundLeaguePointsInputData(leagueID, users);
        roundLeagueInteractor.roundUp(roundLeaguePointsInputData);
    }


}
