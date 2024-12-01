package interface_adapter.update_league_points;

import entity.User;
import usecase.updatePointsForLeague.UpdatePointsForLeagueInputBoundary;
import usecase.updatePointsForLeague.UpdatePointsForLeagueInputData;

import java.util.ArrayList;

public class UpdateLeaguePointsController {

    private UpdatePointsForLeagueInputBoundary updateLeaguePointsInteractor;

    public UpdateLeaguePointsController(UpdatePointsForLeagueInputBoundary updateLeaguePointsInteractor) {
        this.updateLeaguePointsInteractor = updateLeaguePointsInteractor;
    }

    public void execute(String leagueID, ArrayList<User> users){
        final UpdatePointsForLeagueInputData updateLeaguePointsInputData
                = new UpdatePointsForLeagueInputData(leagueID, users);
        updateLeaguePointsInteractor.execute(updateLeaguePointsInputData);
   }
}
