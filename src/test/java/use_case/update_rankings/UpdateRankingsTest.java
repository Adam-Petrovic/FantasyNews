package use_case.update_rankings;

import data_access.Constants;
import data_access.GuardianDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.add_word.*;
import use_case.solo_play.SoloPlayUserDataAccessInterface;
import use_case.update_solo_points.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateRankingsTest {
    @Test
    void successTest(){
        UpdateRankingsInputData inputData = new UpdateRankingsInputData("rankingsTestLeague");
        UpdateRankingsLeagueDataAccessInterface leagueRepository = new InMemoryUserDataAccessObject();
        LeagueFactory factory = new LeagueFactory();
        League league = factory.create("rankingsTestLeague", new ArrayList<>());
        //league.setWords("more_points");
        leagueRepository.save(league);

        UpdateRankingsOutputBoundary successPresenter = new UpdateRankingsOutputBoundary() {
            @Override
            public void execute(UpdateRankingsOutputData UpdateRankingsOutputData) {
                //assertEquals();
            }
        };

        //UpdateRankingsInputBoundary interactor = new UpdateRankingsInteractor();
        //interactor.execute(inputData);

    }
}
