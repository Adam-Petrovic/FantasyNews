package usecase.update_leagues;

import data_access.InMemoryLeagueDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.League;
import entity.LeagueFactory;
import entity.User;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UpdateLeaguesInteractorTest {
    @Test
    void noLeagueIDs() throws FileNotFoundException {
        UpdateLeaguesInputData inputData = new UpdateLeaguesInputData("username", "", false);

        UpdateLeaguesLeagueDataAccessInterface leagueRepository = new InMemoryLeagueDataAccessObject();
        UpdateLeaguesUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // creates test users and adds them to in memory dao
        CommonUserFactory userFactory = new CommonUserFactory();
        User basic = userFactory.create("username", "p");
        userRepository.save(basic);

        UpdateLeaguesOutputBoundary successPresenter = new UpdateLeaguesOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData) {
            }

            @Override
            public void prepareFailView(String errorMessage) {
            }
        };

        UpdateLeaguesInputBoundary interactor = new UpdateLeaguesInteractor(successPresenter, userRepository, leagueRepository);
        interactor.execute(inputData);
    }


    @Test
    void createLeagueSuccessTest() throws FileNotFoundException {
        UpdateLeaguesInputData inputData = new UpdateLeaguesInputData("username", "leagueID", false);

        UpdateLeaguesLeagueDataAccessInterface leagueRepository = new InMemoryLeagueDataAccessObject();
        UpdateLeaguesUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // creates test users and adds them to in memory dao
        CommonUserFactory userFactory = new CommonUserFactory();
        User basic = userFactory.create("username", "p");
        userRepository.save(basic);

        UpdateLeaguesOutputBoundary successPresenter = new UpdateLeaguesOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData) {
                assert(basic.getLeagueIDs().contains("leagueID"));
                assert(leagueRepository.leagueExists("leagueID"));
                assert(updateLeaguesOutputData.getUsername().equals("username"));
                assert(updateLeaguesOutputData.getUserLeagueList().get(0).getId().equals("leagueID"));
            }

            @Override
            public void prepareFailView(String errorMessage) {

            }
        };

        UpdateLeaguesInputBoundary interactor = new UpdateLeaguesInteractor(successPresenter, userRepository, leagueRepository);
        interactor.execute(inputData);

    }

    @Test
    void joinLeagueSuccessTest() throws FileNotFoundException {
        UpdateLeaguesInputData inputData = new UpdateLeaguesInputData("username", "leagueID", true);

        UpdateLeaguesLeagueDataAccessInterface leagueRepository = new InMemoryLeagueDataAccessObject();
        UpdateLeaguesUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // creates test users and adds them to in memory dao
        CommonUserFactory userFactory = new CommonUserFactory();
        User basic = userFactory.create("username", "p");
        User meow = userFactory.create("meow", "p");
        userRepository.save(basic);

        LeagueFactory leagueFactory = new LeagueFactory();
        League league = leagueFactory.create("leagueID", new ArrayList<>());
        leagueRepository.save(league);
        leagueRepository.addUserToLeague("leagueID", "meow");

        UpdateLeaguesOutputBoundary successPresenter = new UpdateLeaguesOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData) {
                League league = leagueRepository.getLeagues(basic.getLeagueIDs()).get(0);
                assert(league.getUsers().contains(basic.getName()));
                assert(league.getUsers().contains("meow"));
                assert(basic.getLeagueIDs().contains("leagueID"));
            }

            @Override
            public void prepareFailView(String errorMessage) {

            }
        };

        UpdateLeaguesInputBoundary interactor = new UpdateLeaguesInteractor(successPresenter, userRepository, leagueRepository);
        interactor.execute(inputData);

    }

    @Test
    void joinLeagueNotExistFailTest() throws FileNotFoundException {
        UpdateLeaguesInputData inputData = new UpdateLeaguesInputData("username", "leagueID", true);

        UpdateLeaguesLeagueDataAccessInterface leagueRepository = new InMemoryLeagueDataAccessObject();
        UpdateLeaguesUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // creates test users and adds them to in memory dao
        CommonUserFactory userFactory = new CommonUserFactory();
        User basic = userFactory.create("username", "p");
        userRepository.save(basic);

        UpdateLeaguesOutputBoundary successPresenter = new UpdateLeaguesOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData) {
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assert(errorMessage.equals("League Does Not Exist"));
            }
        };

        UpdateLeaguesInputBoundary interactor = new UpdateLeaguesInteractor(successPresenter, userRepository, leagueRepository);
        interactor.execute(inputData);

    }

    @Test
    void createLeagueAlreadyExistFailTest() throws FileNotFoundException {
        UpdateLeaguesInputData inputData = new UpdateLeaguesInputData("username", "leagueID", false);

        UpdateLeaguesLeagueDataAccessInterface leagueRepository = new InMemoryLeagueDataAccessObject();
        UpdateLeaguesUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // creates test users and adds them to in memory dao
        CommonUserFactory userFactory = new CommonUserFactory();
        User basic = userFactory.create("username", "p");
        userRepository.save(basic);

        LeagueFactory leagueFactory = new LeagueFactory();
        League league = leagueFactory.create("leagueID", new ArrayList<>());
        leagueRepository.save(league);

        UpdateLeaguesOutputBoundary successPresenter = new UpdateLeaguesOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData) {
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assert(errorMessage.equals("League Already Exists"));
            }
        };

        UpdateLeaguesInputBoundary interactor = new UpdateLeaguesInteractor(successPresenter, userRepository, leagueRepository);
        interactor.execute(inputData);

    }

    @Test
    void joinLeagueAlreadyInFailTest() throws FileNotFoundException {
        UpdateLeaguesInputData inputData = new UpdateLeaguesInputData("username", "leagueID", true);

        UpdateLeaguesLeagueDataAccessInterface leagueRepository = new InMemoryLeagueDataAccessObject();
        UpdateLeaguesUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // creates test users and adds them to in memory dao
        CommonUserFactory userFactory = new CommonUserFactory();
        User basic = userFactory.create("username", "p");
        userRepository.save(basic);
        userRepository.addLeague("username", "leagueID");

        LeagueFactory leagueFactory = new LeagueFactory();
        League league = leagueFactory.create("leagueID", new ArrayList<>());
        leagueRepository.save(league);
        leagueRepository.addUserToLeague("leagueID", "username");
        ArrayList<String> ids = new ArrayList<>();
        ids.add("leagueID");
        ArrayList<String> users = new ArrayList<>();
        users.add("username");

        UpdateLeaguesOutputBoundary successPresenter = new UpdateLeaguesOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateLeaguesOutputData updateLeaguesOutputData) {
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assert(errorMessage.equals("Already In League"));
            }
        };

        UpdateLeaguesInputBoundary interactor = new UpdateLeaguesInteractor(successPresenter, userRepository, leagueRepository);
        interactor.execute(inputData);

    }

}
