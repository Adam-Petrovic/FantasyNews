package usecase.rankingsuserstory.update_rankings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import data_access.Constants;
import entity.League;
import entity.User;

/**
 * The {@code UpdateRankingsInteractor} class implements the business logic for updating user rankings
 * within a specific league. This class retrieves league and user data, calculates live and historical
 * league points for users, and sorts the rankings based on these points.
 *
 * <p>This interactor communicates with data access interfaces to retrieve necessary information
 * and uses a presenter to output the updated rankings.
 */
public class UpdateRankingsInteractor implements UpdateRankingsInputBoundary {
    private final UpdateRankingsGuardianDataAccessInterface guardianDataAccessInterface;
    private final UpdateRankingsOutputBoundary updateRankingsPresenter;
    private final UpdateRankingsLeagueDataAccessInterface leagueDataAccessInterface;

    public UpdateRankingsInteractor(UpdateRankingsGuardianDataAccessInterface guardianDataAccessInterface,
                                    UpdateRankingsOutputBoundary updateRankingsPresenter,
                                    UpdateRankingsLeagueDataAccessInterface leagueDataAccessInterface) {

        this.guardianDataAccessInterface = guardianDataAccessInterface;
        this.updateRankingsPresenter = updateRankingsPresenter;
        this.leagueDataAccessInterface = leagueDataAccessInterface;
    }

    @Override
    public void execute(UpdateRankingsInputData updateRankingsInputData) {

        String leagueID = updateRankingsInputData.getLeague();
        boolean leagueExists = leagueDataAccessInterface.leagueExists(leagueID);

        if (!leagueExists) {
            updateRankingsPresenter.prepareFailView("League Does Not Exist");
        }
        else {
            ArrayList<String> leagueIDArray = new ArrayList<>();
            leagueIDArray.add(leagueID);

            ArrayList<User> liveRankings = new ArrayList<>();
            ArrayList<User> historicalRankings = new ArrayList<>();

            ArrayList<League> leagues = leagueDataAccessInterface.getLeagues(leagueIDArray);
            League leagueOutput = leagues.get(0);
            Map<String, String[]> league = leagueOutput.getData();
            ArrayList<User> users = leagueOutput.getUserObjArr();
            int j = 0;

            for (String username : league.keySet()) {
                String[] words = league.get(username);
                int total = 0;
                User user = users.get(j);
                for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
                    total += guardianDataAccessInterface.getPointsForCategory(words[index]);
                }
                user.setLiveLeaguePoints(total);
                user.setLeaguePoints((int) Float.parseFloat(words[Constants.NUM_CATEGORIES]));
                liveRankings.add(user);
                historicalRankings.add(user);

                j++;
            }

            liveRankings.sort(Comparator.comparingInt(User::getLiveLeaguePoints));
            historicalRankings.sort(Comparator.comparingInt(User::getLeaguePoints));
            UpdateRankingsOutputData outputData = new UpdateRankingsOutputData(liveRankings, leagueOutput,
                    historicalRankings);
            updateRankingsPresenter.execute(outputData);

        }

    }
}
