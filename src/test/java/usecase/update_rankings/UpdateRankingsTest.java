package usecase.update_rankings;

import data_access.GuardianDataAccessObject;
import data_access.InMemoryLeagueDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class UpdateRankingsTest {
    @Test
    void successTest() throws FileNotFoundException {
        UpdateRankingsInputData inputData = new UpdateRankingsInputData("rankingsTestLeague");

        GuardianDataAccessObject guardianDataAccessObject = makeGuardianDataAccessObject();

        // creates test users and adds them to in memory dao
        UpdateRankingsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        CommonUserFactory userFactory = new CommonUserFactory();
        User more = userFactory.create("more_points", "p");
        User less = userFactory.create("less_points", "p");
        userRepository.save(more);
        userRepository.save(less);

        // creates test league
        UpdateRankingsLeagueDataAccessInterface leagueRepository = new InMemoryLeagueDataAccessObject();
        LeagueFactory leagueFactory = new LeagueFactory();
        League league = leagueFactory.create("rankingsTestLeague", new ArrayList<>());
        league.setWords("more_points", new String[]{"the", "he", "she", "they", "no", "100"});
        league.setWords("less_points", new String[]{"", "", "", "", "", "-1"});
        leagueRepository.save(league);

        UpdateRankingsOutputBoundary successPresenter = new UpdateRankingsOutputBoundary() {
            @Override
            public void execute(UpdateRankingsOutputData updateRankingsOutputData) {
                assertEquals("less_points", updateRankingsOutputData.getLiveRankings().get(1).getName());
                assertEquals("more_points", updateRankingsOutputData.getLiveRankings().get(0).getName());

                assertEquals("more_points", updateRankingsOutputData.getHistoricalRankings().get(1).getName());
                assertEquals("less_points", updateRankingsOutputData.getHistoricalRankings().get(0).getName());

                assertEquals("rankingsTestLeague", updateRankingsOutputData.getLeague().getId());
            }
        };

        UpdateRankingsInputBoundary interactor = new UpdateRankingsInteractor(guardianDataAccessObject,
                successPresenter, leagueRepository);
        interactor.execute(inputData);

    }


    private GuardianDataAccessObject makeGuardianDataAccessObject() throws FileNotFoundException {
        try {
            String apiKey = new Scanner(new File("guardianAPIToken.txt")).nextLine();
            return new GuardianDataAccessObject(apiKey);
        }

        catch (FileNotFoundException e) {
            System.out.println("Need to find API token, and call the file GuardianAPIToken");
            return null;
        }
    }
}
