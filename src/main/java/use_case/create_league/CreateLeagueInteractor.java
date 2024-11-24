package use_case.create_league;

import entity.League;
import use_case.signup.SignupOutputBoundary;

public class CreateLeagueInteractor implements CreateLeagueInputBoundary{
    private CreateLeagueOutputBoundary createLeaguePresenter;
    private CreateLeagueUserDataAccessInterface userDataAccessInterface;

    public CreateLeagueInteractor(CreateLeagueOutputBoundary createLeaguePresenter,
                                  CreateLeagueUserDataAccessInterface userDataAccessInterface) {
        this.createLeaguePresenter = createLeaguePresenter;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    public void execute(CreateLeagueInputData createLeagueInputData){
        String username = createLeagueInputData.getUsername();
        String leagueID = createLeagueInputData.getLeagueID();
        if(!userDataAccessInterface.inLeague(username)){
            userDataAccessInterface.setUserLeague(username, leagueID);
            League league = userDataAccessInterface.getLeague(leagueID);
            CreateLeagueOutputData createLeagueOutputData = new CreateLeagueOutputData(username, leagueID, league);
            createLeaguePresenter.prepareSuccessView(createLeagueOutputData);
        }
        else{
            System.out.println("rip");
        }
    }
}
