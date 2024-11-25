package use_case.join_league;

import entity.League;
import entity.User;
import use_case.create_league.CreateLeagueInputBoundary;
import use_case.create_league.CreateLeagueOutputBoundary;
import use_case.create_league.CreateLeagueOutputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class JoinLeagueInteractor implements JoinLeagueInputBoundary {
    private CreateLeagueOutputBoundary createLeaguePresenter;
    private JoinLeagueUserDataAccessInterface userDataAccessObject;
    private JoinLeagueLeagueDataAccessInterface leagueDataAccessObject;

    public JoinLeagueInteractor(CreateLeagueOutputBoundary createLeaguePresenter,
                                JoinLeagueUserDataAccessInterface userDataAccessObject,
                                JoinLeagueLeagueDataAccessInterface leagueDataAccessObject) {
        this.createLeaguePresenter = createLeaguePresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.leagueDataAccessObject = leagueDataAccessObject;
    }

    @Override
    public void execute(JoinLeagueInputData joinLeagueInputData) {
        String username = joinLeagueInputData.getUsername();
        String leagueID = joinLeagueInputData.getLeagueID();

        if(leagueDataAccessObject.leagueExist(leagueID)){
            sleep(3);
            User user = userDataAccessObject.get(username);
            sleep(2);
            leagueDataAccessObject.updateLeagueUsers(user, leagueID);
            sleep(2);
            ArrayList<String> leagueIDs = userDataAccessObject.updateUserLeagues(user, leagueID);
            sleep(2);
            ArrayList<League> leagues = new ArrayList<>();
            for(String id : leagueIDs) {
                sleep(3);
                leagues.add(createLeague(id, leagueDataAccessObject.getLeagueUsers(id), leagueDataAccessObject.getData(id)));
            }
            //if no leagues, don't update the view & change my nice cat jpg :(
            if(leagues.isEmpty()){
                return;
            }
            CreateLeagueOutputData createLeagueOutputData = new CreateLeagueOutputData(user.getName(), leagues);
            createLeaguePresenter.prepareSuccessView(createLeagueOutputData);
        }
        else{
            System.out.println("league doesn't exist -- create that hoe");
            createLeaguePresenter.prepareFailureView("League Doesn't Exist");
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

    //creates league from data given by league database
    public League createLeague(String id, ArrayList<String> usernames, HashMap<String, String[]> data){
        ArrayList<User> users = new ArrayList();
        HashMap<User, String[]> finalData = new HashMap<>();
        for(String username: data.keySet()){
            sleep(3);
            User user = userDataAccessObject.get(username);
            users.add(user);
            finalData.put(user, data.get(username));
        }
        return new League(id, users, finalData);
    }
}
