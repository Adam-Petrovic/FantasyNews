package use_case.to_league_actions;

import entity.League;

import java.util.ArrayList;

public class ToLeagueActionsInteractor implements ToLeagueActionsInputBoundary{
    ToLeagueActionsOutputBoundary toLeagueActionsPresenter;
    ToLeagueActionsUserDataAccessInterface userDataAccessObject;
    ToLeagueActionsLeagueDataAccessInterface leagueDataAccessObject;

    public ToLeagueActionsInteractor(ToLeagueActionsOutputBoundary toLeagueActionsPresenter,
                              ToLeagueActionsUserDataAccessInterface userDataAccessObject,
                              ToLeagueActionsLeagueDataAccessInterface leagueDataAccessObject) {
        this.toLeagueActionsPresenter = toLeagueActionsPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(ToLeagueActionsInputData toLeagueActionsInputData) {
        String username = toLeagueActionsInputData.getUsername();
        String leagueID = toLeagueActionsInputData.getLeagueID();
        if(userDataAccessObject.userInLeague(username, leagueID)){
            ArrayList<String> leagueIDs = new ArrayList<>();
            leagueIDs.add(leagueID);
            League league = leagueDataAccessObject.getLeagues(leagueIDs).get(0);

            ToLeagueActionsOutputData toLeagueActionsOutputData = new ToLeagueActionsOutputData(league);
            toLeagueActionsPresenter.prepareSuccessView(toLeagueActionsOutputData);
        }
        toLeagueActionsPresenter.prepareFailureView("User Not In League");
    }
}
