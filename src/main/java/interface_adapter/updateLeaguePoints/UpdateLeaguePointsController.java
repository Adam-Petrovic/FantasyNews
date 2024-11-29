package interface_adapter.updateLeaguePoints;

import entity.User;
import use_case.updateLeaguePoints.UpdateLeaguePointsInputBoundary;
import use_case.updateLeaguePoints.UpdateLeaguePointsInputData;

import java.util.ArrayList;

public class UpdateLeaguePointsController {

    private UpdateLeaguePointsInputBoundary updateLeaguePointsInteractor;

    public UpdateLeaguePointsController(UpdateLeaguePointsInputBoundary updateLeaguePointsInteractor) {
        this.updateLeaguePointsInteractor = updateLeaguePointsInteractor;
    }

    public void execute(ArrayList<User> users){
        final UpdateLeaguePointsInputData updateLeaguePointsInputData = new UpdateLeaguePointsInputData(users);
        updateLeaguePointsInteractor.execute(updateLeaguePointsInputData);
   }
}
