package usecasetests.toDraft;


import data_access.InMemoryLeagueDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import usecase.selectwordsuserstory.to_draft.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToDraftInteractorTest {

    @Test
    void successTest(){
        ToDraftInputData inputData = new ToDraftInputData("a", "a");

        ToDraftLeagueDataAccessInterface toDraftLeagueDataAccessInterface = new InMemoryLeagueDataAccessObject();

        LeagueFactory leagueFactory = new LeagueFactory();
        ArrayList<String> users = new ArrayList<>();
        users.add("a");
        League league = leagueFactory.create("a", users);

        ArrayList<League> leagueArrayList = new ArrayList<League>();
        leagueArrayList.add(league);
        ((InMemoryLeagueDataAccessObject) toDraftLeagueDataAccessInterface).save(league);

        ToDraftOutputBoundary presenter = new ToDraftOutputBoundary() {
            @Override
            public void execute(ToDraftOutputData toDraftOutputData) {
                assertEquals(toDraftOutputData.getUsername(), "a");
                assertEquals(toDraftOutputData.getLeagueID(), "a");
                String[] exp = {"Default 1", "Default 2", "Default 3", "Default 4", "Default 5", "0"};
                assertArrayEquals(toDraftLeagueDataAccessInterface.getWords("a", "a"), exp);
            }
        };
        ToDraftInputBoundary interactor = new ToDraftInteractor(presenter, toDraftLeagueDataAccessInterface);
    }

}
