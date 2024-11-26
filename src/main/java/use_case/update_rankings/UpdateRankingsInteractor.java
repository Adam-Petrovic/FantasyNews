package use_case.update_rankings;

import data_access.Constants;
import entity.User;
import data_access.GuardianDataAccessObject;

import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.concurrent.TimeUnit;

public class UpdateRankingsInteractor implements UpdateRankingsInputBoundary{
    private final GuardianDataAccessObject guardianDataAccessObject;
    private final UpdateRankingsDataAccessInterface userDataAccessObject;
    private final UpdateRankingsOutputBoundary updateRankingsPresenter;
    private final UpdateRankingsLeagueDataAccessInterface leagueDataAccessInterface;
    private final UpdateRankingsUserDataAccessInterface userDataAccessInterface;


    public UpdateRankingsInteractor(GuardianDataAccessObject guardianDataAccessObject,
                                    UpdateRankingsOutputBoundary updateRankingsPresenter,
                                    UpdateRankingsDataAccessInterface userDataAccessObject) {

        this.guardianDataAccessObject = guardianDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.updateRankingsPresenter = updateRankingsPresenter;
        this.leagueDataAccessInterface = leagueDataAccessInterface;
        this.userDataAccessInterface = userDataAccessInterface;
    }


    public void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(UpdateRankingsInputData updateRankingsInputData) {

        ArrayList<User> leagueUsers = userDataAccessObject.getUsers(updateRankingsInputData.getLeague().getUsers());

        String leagueID = updateRankingsInputData.getLeague();
        ArrayList<User> leagueUsers = new ArrayList<>();
        //leagueDataAccessInterface.getData(leagueID);
        ArrayList<String> usernames = leagueDataAccessInterface.getLeagueUsers(leagueID);
        sleep(2);
        for (String username : usernames) {
            User user = userDataAccessInterface.get(username);
            sleep(2);
            leagueUsers.add(user);
        }



        HashMap<String, ArrayList<User>> rankings = new HashMap<>();
        // sports: [mario, luigi, peach], politics: [peach, mario, luigi]
        HashMap<User, Integer> userPoints = new HashMap<>();

        for(int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            rankings.put(Constants.CATEGORIES[index], new ArrayList<User>());
            //ArrayList<Integer> categoryPoints = new ArrayList<>();
            for (User user: leagueUsers) {
                Integer categoryPoints = guardianDataAccessObject.getPointsForCategory(user.getWordFromCategory(Constants.CATEGORIES[index]));
                // update user points attribute with above, then use order to rank.
                rankings.get(Constants.CATEGORIES[index]).add(user);
            }
        }

        System.out.println(rankings);
        UpdateRankingsOutputData outputData = new UpdateRankingsOutputData(rankings);
        updateRankingsPresenter.execute(outputData);

    }
}
