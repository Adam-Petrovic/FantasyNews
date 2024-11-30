package use_case.update_rankings;

import data_access.Constants;
import entity.CommonUser;
import entity.League;
import entity.User;
import data_access.GuardianDataAccessObject;

import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.util.concurrent.TimeUnit;

public class UpdateRankingsInteractor implements UpdateRankingsInputBoundary{
    private final GuardianDataAccessObject guardianDataAccessObject;
    private final UpdateRankingsOutputBoundary updateRankingsPresenter;
    private final UpdateRankingsLeagueDataAccessInterface leagueDataAccessInterface;
    private final UpdateRankingsUserDataAccessInterface userDataAccessInterface;


    public UpdateRankingsInteractor(GuardianDataAccessObject guardianDataAccessObject,
                                    UpdateRankingsOutputBoundary updateRankingsPresenter,
                                    UpdateRankingsLeagueDataAccessInterface leagueDataAccessInterface,
                                    UpdateRankingsUserDataAccessInterface userDataAccessInterface) {

        this.guardianDataAccessObject = guardianDataAccessObject;
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

    public static void sort(ArrayList<User> list) {
        list.sort(Comparator.comparingInt(User::getLiveLeaguePoints));
    }

    @Override
    public void execute(UpdateRankingsInputData updateRankingsInputData) {

        //ArrayList<User> leagueUsers = userDataAccessInterface.getUsers(updateRankingsInputData.getLeague().getUsers());

        String leagueID = updateRankingsInputData.getLeague();
        ArrayList<String> leagueIDArray = new ArrayList<>();
        leagueIDArray.add(leagueID);
        ArrayList<User> leagueUsers = new ArrayList<>();
        //leagueDataAccessInterface.getData(leagueID);

        ArrayList<User> liveRankings = new ArrayList<>();
        ArrayList<User> historicalRankings = new ArrayList<>();

        HashMap<String, String[]> league = null;
        League leagueOutput = null;
        System.out.println("ranking league " + leagueID);

        ArrayList<League> leagues = leagueDataAccessInterface.getLeagues(leagueIDArray);
        leagueOutput = leagues.get(0);
        league = leagueOutput.getData();
        ArrayList<User> users = leagueOutput.getUserObjArr();
        int i = 0;

        // league.keySet()
        // ArrayList<String> usersList = new ArrayList<>(league.keySet());
        // ArrayList<User> users = userDataAccessInterface.getUsers(usersList);
        for (String username : league.keySet()) {
            String[] words = league.get(username);
            int total = 0;
            // take it outside so we only call pantry api once!
            User user = users.get(i);
            //User user = userDataAccessInterface.get(username);
            // loop through categories bc words5 is total
            for (String word : words) {
                Integer categoryPoints = guardianDataAccessObject.getPointsForCategory(word);
                // set user
                // guardian up to 1 call per second
                //sleep(1);
                //Integer categoryPoints = 10;
                total += categoryPoints;
            }
            user.setLiveLeaguePoints(total);
            user.setLeaguePoints(Integer.parseInt(words[5]));
            liveRankings.add(user);
            historicalRankings.add(user);

            i++;
        }




        // live rankings
        // use comparator on user.liveLeaguePoints
        // historical rankings
        // use comparator on user.leaguePoints

        //System.out.println(rankings);
        liveRankings.sort(Comparator.comparingInt(User::getLiveLeaguePoints));
        historicalRankings.sort(Comparator.comparingInt(User::getLeaguePoints));
        UpdateRankingsOutputData outputData = new UpdateRankingsOutputData(liveRankings, leagueOutput, historicalRankings);
        updateRankingsPresenter.execute(outputData);



    }
}
