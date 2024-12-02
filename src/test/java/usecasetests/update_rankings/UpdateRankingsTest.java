package usecasetests.update_rankings;

import dataaccess.GuardianDataAccessObject;
import dataaccess.InMemoryLeagueDataAccessObject;
import dataaccess.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import usecase.rankingsuserstory.update_rankings.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class UpdateRankingsTest {
    @Test
    void successTest() throws FileNotFoundException {
        UpdateRankingsInputData inputData = new UpdateRankingsInputData("rankingsTestLeague");

        GuardianDataAccessObject guardianDataAccessObject = new GuardianDataAccessObject("c7dda92e-78d1-440d-a3b7-ee3d27ee35be");

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

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected");
            }


        };


        UpdateRankingsInputBoundary interactor = new UpdateRankingsInteractor(guardianDataAccessObject,
                successPresenter, leagueRepository);
        interactor.execute(inputData);


    }

    @Test
    void failTest() throws FileNotFoundException {
        UpdateRankingsInputData failInputData = new UpdateRankingsInputData("");


        GuardianDataAccessObject guardianDataAccessObject = new GuardianDataAccessObject("c7dda92e-78d1-440d-a3b7-ee3d27ee35be");

        UpdateRankingsLeagueDataAccessInterface failLeagueRepository = new InMemoryLeagueDataAccessObject();
        LeagueFactory leagueFactory = new LeagueFactory();
        League failLeague = leagueFactory.create("notempty", new ArrayList<>());
        failLeagueRepository.save(failLeague);

        UpdateRankingsOutputBoundary failPresenter = new UpdateRankingsOutputBoundary() {
            @Override
            public void execute(UpdateRankingsOutputData updateRankingsOutputData) {
                fail("Use case failure is expected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("League Does Not Exist", errorMessage);
            }

        };

        UpdateRankingsInputBoundary failInteractor = new UpdateRankingsInteractor(guardianDataAccessObject,
                failPresenter, failLeagueRepository);
        failInteractor.execute(failInputData);
    }
}