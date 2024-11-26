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

        ArrayList<League> userLeagueList = new ArrayList<League>();
        JSONObject leagueData = leagueDataAccessObject.get();
        JSONObject userData = userDataAccessObject.get();
        //join league
        if(updateLeaguesInputData.isJoin()){
            if (!leagueExists(leagueData, leagueID)) {
                updateLeaguesPresenter.prepareFailView("League Does Not Exist");
                return;
            }
            //update user league list & returns updated list of leagues
            userLeagueList = addLeague(userData, username,  leagueID);
            //update league's list of users
            addUserToLeague(leagueData, leagueID, username);
        }
        //create league (if not joining, then creating)
        else{
            if (leagueExists(leagueData, leagueID)) {
                updateLeaguesPresenter.prepareFailView("League Already Exists");
                return;
            }
            //update user league list & returns updated list of leagues
            userLeagueList = addLeague(userData, username,  leagueID);
            //creates new league
            saveNewLeague(leagueData, leagueID, username);
        }
        //no leagues, then keep original view, so no presenter needed
        if(userLeagueList.isEmpty()){
            return;
        }
        leagueDataAccessObject.save(leagueData);
        userDataAccessObject.save(userData);

        UpdateLeaguesOutputData updateLeaguesOutputData = new UpdateLeaguesOutputData(username, userLeagueList);
        this.updateLeaguesPresenter.prepareSuccessView(updateLeaguesOutputData);
    }

    public boolean leagueExists(JSONObject leagueData, String leagueID){
        return true;
    }

    public ArrayList<League> addLeague(JSONObject userData, String username, String leagueID){
        return null;
    }

    public void addUserToLeague(JSONObject leagueData, String leagueID, String username){

    }

    public void saveNewLeague(JSONObject leagueData, String leagueID, String username){

    }


}
