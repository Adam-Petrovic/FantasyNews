package usecase.draft_words;

import data_access.InMemoryLeagueDataAccessObject;
import entity.League;
import entity.LeagueFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class DraftWordsInteractorTest {

    @Test
    void successTest(){
        DraftWordsInputData in = new DraftWordsInputData("a", 0, "soccer", "a");
        DraftWordsLeagueDataAccessInterface leagueRepo = new InMemoryLeagueDataAccessObject();

        LeagueFactory leagueFactory = new LeagueFactory();
        ArrayList<String> users = new ArrayList<>();
        users.add("a");
        League league = leagueFactory.create("a", users);

        ArrayList<League> leagueArrayList = new ArrayList<League>();
        leagueArrayList.add(league);

        ((InMemoryLeagueDataAccessObject) leagueRepo).save(league);

        DraftWordsOutputBoundary successPresenter = new DraftWordsOutputBoundary() {
            @Override
            public void showDraftedWords(DraftWordsOutputData draftWordsOutputData) {
                String exp = ((InMemoryLeagueDataAccessObject) leagueRepo).getLeague("a").getData().get("a")[0];
                String[] newWords = {"soccer", "Default 2", "Default 3", "Default 4", "Default 5", "0"};
                assertArrayEquals(draftWordsOutputData.getWords(), newWords);
                assertEquals(exp, "soccer");
            }
        };

        DraftWordsInputBoundary interactor = new DraftWordsInteractor(successPresenter, leagueRepo);
        interactor.execute(in);
    }
}
