package usecase.to_league_actions;

import java.util.ArrayList;

import entity.League;

/**
 * Interactor.
 */
public class ToLeagueActionsInteractor implements ToLeagueActionsInputBoundary {
    private ToLeagueActionsOutputBoundary toLeagueActionsPresenter;
    private ToLeagueActionsUserDataAccessInterface userDataAccessObject;
    private ToLeagueActionsLeagueDataAccessInterface leagueDataAccessObject;

    public ToLeagueActionsInteractor(ToLeagueActionsOutputBoundary toLeagueActionsPresenter,
                              ToLeagueActionsUserDataAccessInterface userDataAccessObject,
                              ToLeagueActionsLeagueDataAccessInterface leagueDataAccessObject) {
        this.toLeagueActionsPresenter = toLeagueActionsPresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.leagueDataAccessObject = leagueDataAccessObject;
    }

    @Override
    public void execute(ToLeagueActionsInputData toLeagueActionsInputData) {
        String username = toLeagueActionsInputData.getUsername();
        String leagueID = toLeagueActionsInputData.getLeagueID();
        if (userDataAccessObject.userInLeague(username, leagueID)) {
            ArrayList<String> leagueIDs = new ArrayList<>();
            leagueIDs.add(leagueID);
            League league = leagueDataAccessObject.getLeagues(leagueIDs).get(0);

            ToLeagueActionsOutputData toLeagueActionsOutputData = new ToLeagueActionsOutputData(league, username);
            toLeagueActionsPresenter.prepareSuccessView(toLeagueActionsOutputData);
        }
        else {
            toLeagueActionsPresenter.prepareFailureView("User Not In League");
        }
    }
}
