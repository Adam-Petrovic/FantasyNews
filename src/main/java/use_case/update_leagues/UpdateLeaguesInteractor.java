package use_case.update_leagues;

import entity.League;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpdateLeaguesInteractor implements UpdateLeaguesInputBoundary{
    private UpdateLeaguesOutputBoundary updateLeaguesPresenter;
    private UpdateLeaguesUserDataAccessInterface userDataAccessObject;
    private UpdateLeaguesLeagueDataAccessInterface leagueDataAccessObject;

    public UpdateLeaguesInteractor(UpdateLeaguesOutputBoundary updateLeaguesPresenter,
                                   UpdateLeaguesUserDataAccessInterface userDataAccessObject,
                                   UpdateLeaguesLeagueDataAccessInterface leagueDataAccessObject) {
        this.updateLeaguesPresenter = updateLeaguesPresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.leagueDataAccessObject = leagueDataAccessObject;
    }

    @Override
    public void execute(UpdateLeaguesInputData updateLeaguesInputData) {
        String username = updateLeaguesInputData.getUsername();
        String leagueID = updateLeaguesInputData.getLeagueID();

        ArrayList<String> userLeagueIDList = new ArrayList<String>();
        //join league
        if(updateLeaguesInputData.isJoin()){
            if (!leagueDataAccessObject.LeagueExists(leagueID)) {
                updateLeaguesPresenter.prepareFailView("League Does Not Exist");
                return;
            }
            //update user league list & returns updated list of leagues
            userLeagueIDList = userDataAccessObject.addLeague(username,  leagueID);
            //update league's list of users
            leagueDataAccessObject.addUserToLeague(leagueID, username);
        }
        //create league (if not joining, then creating)
        else{
            if (leagueDataAccessObject.LeagueExists(leagueID)) {
                updateLeaguesPresenter.prepareFailView("League Already Exists");
                return;
            }
            //update user league list & returns updated list of leagues
            userLeagueIDList = userDataAccessObject.addLeague(username,  leagueID);
            //creates new league
            leagueDataAccessObject.saveNewLeague(leagueID, username);
        }
        //no leagues, then keep original view, so no presenter needed
        if(userLeagueIDList.isEmpty()){
            return;
        }
        ArrayList<League> userLeagueList = leagueDataAccessObject.getLeagues(userLeagueIDList);


        UpdateLeaguesOutputData updateLeaguesOutputData = new UpdateLeaguesOutputData(username, userLeagueList);
        this.updateLeaguesPresenter.prepareSuccessView(updateLeaguesOutputData);
    }
}
