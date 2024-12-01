package use_case.update_rankings;

import data_access.GuardianDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateRankingsTest {
    @Test
    void successTest(){
        UpdateRankingsInputData inputData = new UpdateRankingsInputData("rankingsTestLeague");

        GuardianDataAccessObject guardianDataAccessObject = new GuardianDataAccessObject("PUT API KEY HERE");

        // creates test users and adds them to in memory dao
        UpdateRankingsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        CommonUserFactory userFactory = new CommonUserFactory();
        User more = userFactory.create("more_points", "p");
        User less = userFactory.create("less_points", "p");
        userRepository.save(more);
        userRepository.save(less);

        // creates test league
        UpdateRankingsLeagueDataAccessInterface leagueRepository = new InMemoryUserDataAccessObject();
        LeagueFactory leagueFactory = new LeagueFactory();
        League league = leagueFactory.create("rankingsTestLeague", new ArrayList<>());
        league.setWords("more_points", new String[]{"the", "he", "she", "they", "no", "100"});
        league.setWords("less_points", new String[]{"", "", "", "", "", "-1"});
        leagueRepository.save(league);

        UpdateRankingsOutputBoundary successPresenter = new UpdateRankingsOutputBoundary() {
            @Override
            public void execute(UpdateRankingsOutputData updateRankingsOutputData) {
                ArrayList<User> expectedRanking = new ArrayList<>();
                expectedRanking.add(more);
                expectedRanking.add(less);
                assertEquals(expectedRanking, updateRankingsOutputData.getLiveRankings());
                assertEquals(expectedRanking, updateRankingsOutputData.getHistoricalRankings());
            }
        };

        UpdateRankingsInputBoundary interactor = new UpdateRankingsInteractor(guardianDataAccessObject,
                successPresenter, leagueRepository, userRepository);
        interactor.execute(inputData);

    }
}
