package use_case.create_league;

import entity.League;
import entity.User;
import use_case.signup.SignupOutputBoundary;

import java.util.ArrayList;

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

        if(!userDataAccessInterface.leagueExist(leagueID)){
            User user = userDataAccessInterface.get(username);
            ArrayList<String> leagueIDs = userDataAccessInterface.updateUserLeagues(user, leagueID);
            System.out.println("made it here");
            System.out.println("leagues rn: " + leagueIDs);
            ArrayList<League> leagues = new ArrayList<>();
            for(String id : leagueIDs)
                leagues.add(userDataAccessInterface.getLeague(user, id));
            CreateLeagueOutputData createLeagueOutputData = new CreateLeagueOutputData(user.getName(), leagues);
            createLeaguePresenter.prepareSuccessView(createLeagueOutputData);
        }
        else{
            System.out.println("rip");
        }
    }
}
