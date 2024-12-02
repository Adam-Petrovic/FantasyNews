package interfaceadapter.pointsuserstory.update_league_points;

import java.util.ArrayList;

import entity.User;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueInputBoundary;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueInputData;

/**
 * Controller for updating points for league.
 */
public class UpdateLeaguePointsController {

    private UpdatePointsForLeagueInputBoundary updateLeaguePointsInteractor;

    public UpdateLeaguePointsController(UpdatePointsForLeagueInputBoundary updateLeaguePointsInteractor) {
        this.updateLeaguePointsInteractor = updateLeaguePointsInteractor;
    }

    /**
     * The execute function for the controller.
     * @param leagueID the name of the league
     * @param users an ArrayList of Users in the current league
     */
    public void execute(String leagueID, ArrayList<User> users) {
        final UpdatePointsForLeagueInputData updateLeaguePointsInputData =
                new UpdatePointsForLeagueInputData(leagueID, users);
        updateLeaguePointsInteractor.execute(updateLeaguePointsInputData);
    }
}
