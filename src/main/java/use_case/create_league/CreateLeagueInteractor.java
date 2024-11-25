package use_case.create_league;

import entity.League;
import entity.User;
import use_case.signup.SignupOutputBoundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class CreateLeagueInteractor implements CreateLeagueInputBoundary{
    private CreateLeagueOutputBoundary createLeaguePresenter;
    private CreateLeagueUserDataAccessInterface userDataAccessInterface;
    private CreateLeagueLeagueDataAccessInterface leagueDataAccessInterface;

    public CreateLeagueInteractor(CreateLeagueOutputBoundary createLeaguePresenter,
                                  CreateLeagueUserDataAccessInterface userDataAccessInterface,
                                  CreateLeagueLeagueDataAccessInterface leagueDataAccessInterface) {
        this.createLeaguePresenter = createLeaguePresenter;
        this.userDataAccessInterface = userDataAccessInterface;
        this.leagueDataAccessInterface = leagueDataAccessInterface;
    }

    public void execute(CreateLeagueInputData createLeagueInputData){
        String username = createLeagueInputData.getUsername();
        String leagueID = createLeagueInputData.getLeagueID();

        if(!leagueDataAccessInterface.leagueExist(leagueID)){
            sleep(3);
            User user = userDataAccessInterface.get(username);
            sleep(2);
            leagueDataAccessInterface.saveNewLeague(user, leagueID);
            sleep(2);
            ArrayList<String> leagueIDs = userDataAccessInterface.updateUserLeagues(user, leagueID);
            sleep(2);
            ArrayList<League> leagues = new ArrayList<>();
            for(String id : leagueIDs) {
                sleep(3);
                leagues.add(createLeague(id, leagueDataAccessInterface.getLeagueUsers(id), leagueDataAccessInterface.getData(id)));
            }
            //if no leagues, don't update the view & change my nice cat jpg :(
            if(leagues.isEmpty()){
                return;
            }
            CreateLeagueOutputData createLeagueOutputData = new CreateLeagueOutputData(user.getName(), leagues);
            createLeaguePresenter.prepareSuccessView(createLeagueOutputData);
        }
        else{
            System.out.println("league already exists -- join that hoe");
        }
    }

    //cuz pantry is a hoe
    public void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //creates league from usernames given by league database
    public League createLeague(String id, ArrayList<String> usernames, HashMap<String, String[]> data){
        ArrayList<User> users = new ArrayList();
        HashMap<User, String[]> finalData = new HashMap<>();
        for(String username: data.keySet()){
            sleep(3);
            User user = userDataAccessInterface.get(username);
            users.add(user);
            finalData.put(user, data.get(username));
        }
        return new League(id, users, finalData);
    }
}
