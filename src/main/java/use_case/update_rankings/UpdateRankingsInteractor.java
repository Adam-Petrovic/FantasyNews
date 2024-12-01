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
//
//    public void sleep(int seconds){
//        try {
//            TimeUnit.SECONDS.sleep(seconds);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void sort(ArrayList<User> list) {
//        list.sort(Comparator.comparingInt(User::getLiveLeaguePoints));
//    }

    @Override
    public void execute(UpdateRankingsInputData updateRankingsInputData) {

        String leagueID = updateRankingsInputData.getLeague();
        ArrayList<String> leagueIDArray = new ArrayList<>();
        leagueIDArray.add(leagueID);

        ArrayList<User> liveRankings = new ArrayList<>();
        ArrayList<User> historicalRankings = new ArrayList<>();

        ArrayList<League> leagues = leagueDataAccessInterface.getLeagues(leagueIDArray);
        League leagueOutput = leagues.get(0);
        HashMap<String, String[]> league = leagueOutput.getData();
        ArrayList<User> users = leagueOutput.getUserObjArr();
        int j = 0;

        for (String username : league.keySet()) {
            String[] words = league.get(username);
            int total = 0;
            User user = users.get(j);
            for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
                // sleep(1);
                total += guardianDataAccessObject.getPointsForCategory(words[index]);
                //total += 10;
            }
            user.setLiveLeaguePoints(total);
            user.setLeaguePoints((int)Float.parseFloat(words[5]));
            liveRankings.add(user);
            historicalRankings.add(user);

            j++;
        }

        liveRankings.sort(Comparator.comparingInt(User::getLiveLeaguePoints));
        historicalRankings.sort(Comparator.comparingInt(User::getLeaguePoints));
        UpdateRankingsOutputData outputData = new UpdateRankingsOutputData(liveRankings, leagueOutput, historicalRankings);
        updateRankingsPresenter.execute(outputData);

    }
}
