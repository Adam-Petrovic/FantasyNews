package use_case.update_rankings;

import data_access.Constants;
import entity.CommonUser;
import entity.League;
import entity.User;
import data_access.GuardianDataAccessObject;

import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;
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

    @Override
    public void execute(UpdateRankingsInputData updateRankingsInputData) {

        //ArrayList<User> leagueUsers = userDataAccessInterface.getUsers(updateRankingsInputData.getLeague().getUsers());


        String leagueID = updateRankingsInputData.getLeague();
        ArrayList<String> leagueIDArray = new ArrayList<>();
        leagueIDArray.add(leagueID);
        ArrayList<User> leagueUsers = new ArrayList<>();
        //leagueDataAccessInterface.getData(leagueID);

        ArrayList<User> rankings = null;
        ArrayList<Integer> points = null;

        HashMap<String, String[]> league = null;
        League leagueOutput = null;
        if (leagueID.equals("friends")) {
            // friends rankings
            System.out.println("rankings friends");
        } else {
            // league rankings
            System.out.println("ranking league " + leagueID);

            ArrayList<League> leagues = leagueDataAccessInterface.getLeagues(leagueIDArray);
//            sleep(2);
            leagueOutput = leagues.get(0);
            league = leagues.get(0).getData();
            rankings = new ArrayList<>();
            User testUser = new CommonUser("test", "test");
            rankings.add(testUser);
            points = new ArrayList<>();
            points.add(-1);
            int i = 0;

            // dont really need the points array just use user.leaguePoints??
            // league.keySet()
            // ArrayList<String> usersList = new ArrayList<>(league.keySet());
            // ArrayList<User> users = userDataAccessInterface.getUsers(usersList);
            for (String username : league.keySet()) {
                String[] words = league.get(username);
                int total = 0;
                // take it outside
                //User user = users.get(i);
                User user = userDataAccessInterface.get(username);
                for (String word : words) {
                    Integer categoryPoints = guardianDataAccessObject.getPointsForCategory(word);
                    // set user
                    total += categoryPoints;
                }
                int j = i;
                while (total < points.get(i)) {
                    j--;
                }
                user.setLeaguePoints(total);
                points.add(j, total);
                rankings.add(j, user);
                i++;
                //System.out.println(rankings);
                //System.out.println(points);
            }


        }

        //remove test user
        if (rankings != null) {
            rankings.remove(rankings.size() - 1);
        }
        //System.out.println(rankings);
        UpdateRankingsOutputData outputData = new UpdateRankingsOutputData(rankings, leagueOutput);
        updateRankingsPresenter.execute(outputData);


        // solo friends rankings
//        HashMap<String, ArrayList<User>> rankings = new HashMap<>();
//        // sports: [mario, luigi, peach], politics: [peach, mario, luigi]
//        HashMap<User, Integer> userPoints = new HashMap<>();

//        for(int index = 0; index < Constants.NUM_CATEGORIES; index++) {
//            rankings.put(Constants.CATEGORIES[index], new ArrayList<User>());
//            //ArrayList<Integer> categoryPoints = new ArrayList<>();
//            for (User user: leagueUsers) {
//                Integer categoryPoints = guardianDataAccessObject.getPointsForCategory(user.getWordFromCategory(Constants.CATEGORIES[index]));
//                // update user points attribute with above, then use order to rank.
//                rankings.get(Constants.CATEGORIES[index]).add(user);
//            }
//        }


    }
}
