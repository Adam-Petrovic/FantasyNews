package usecase.leagueuserstory.update_leagues;

import java.util.ArrayList;

import entity.League;

/**
 * Interactor.
 */
public class UpdateLeaguesInteractor implements UpdateLeaguesInputBoundary {
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

        boolean leagueExists = leagueDataAccessObject.leagueExists(leagueID);
        boolean userInLeague = userDataAccessObject.userInLeague(username, leagueID);

        ArrayList<String> userLeagueIDList;

        // join league
        if (updateLeaguesInputData.isJoin()) {
            if (!leagueExists) {
                updateLeaguesPresenter.prepareFailView("League Does Not Exist");
                return;
            }
            if (userInLeague) {
                updateLeaguesPresenter.prepareFailView("Already In League");
                return;
            }
            // update user league list & returns list of leagues
            userLeagueIDList = userDataAccessObject.addLeague(username, leagueID);
            // update league's list of users
            leagueDataAccessObject.addUserToLeague(leagueID, username);
        }
        // create league (if not joining, then creating)
        else {
            if (leagueExists) {
                updateLeaguesPresenter.prepareFailView("League Already Exists");
                return;
            }
            // update user league list & returns updated list of leagues
            userLeagueIDList = userDataAccessObject.addLeague(username, leagueID);
            // creates new league
            leagueDataAccessObject.saveNewLeague(leagueID, username);
        }

        ArrayList<League> userLeagueList = leagueDataAccessObject.getLeagues(userLeagueIDList);
        UpdateLeaguesOutputData updateLeaguesOutputData = new UpdateLeaguesOutputData(username, userLeagueList);
        this.updateLeaguesPresenter.prepareSuccessView(updateLeaguesOutputData);

    }
}
