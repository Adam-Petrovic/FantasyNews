package usecasetests.to_league_actions;

import dataaccess.InMemoryLeagueDataAccessObject;
import dataaccess.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.League;
import entity.LeagueFactory;
import entity.User;
import org.junit.Test;
import usecase.leagueuserstory.to_league_actions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ToLeagueActionsInteractorTest {
    @Test
    public void viewLeagueSuccessTest() throws FileNotFoundException {
        ToLeagueActionsInputData inputData = new ToLeagueActionsInputData("username", "leagueID");

        ToLeagueActionsLeagueDataAccessInterface leagueRepository = new InMemoryLeagueDataAccessObject();
        ToLeagueActionsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        CommonUserFactory userFactory = new CommonUserFactory();
        User basic = userFactory.create("username", "p");
        userRepository.save(basic);

        LeagueFactory leagueFactory = new LeagueFactory();
        League league = leagueFactory.create("leagueID", new ArrayList<>());
        leagueRepository.save(league);
        leagueRepository.addUserToLeague("leagueID", "meow");


        ToLeagueActionsOutputBoundary successPresenter = new ToLeagueActionsOutputBoundary() {
            @Override
            public void prepareSuccessView(ToLeagueActionsOutputData toLeagueActionsOutputData) {
                assert(basic.getLeagueIDs().contains("leagueID"));
            }

            @Override
            public void prepareFailureView(String errorMessage) {

            }
        };

        ToLeagueActionsInputBoundary interactor = new ToLeagueActionsInteractor(successPresenter, userRepository, leagueRepository);
        interactor.execute(inputData);

    }
}
