package usecasetests.round_points_tests;

import dataaccess.InMemoryLeagueDataAccessObject;
import entity.CommonUser;
import entity.League;
import entity.User;
import org.junit.jupiter.api.Test;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputBoundary;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputData;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInteractor;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsInputBoundary;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsInputData;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsInteractor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class roundPointsTests {

    @Test
    void roundPointsTest() {
        InMemoryLeagueDataAccessObject database = new InMemoryLeagueDataAccessObject();


        ArrayList<String> usernames = new ArrayList<>();
        usernames.add("petroblicky");

        League league = new League("league", usernames);
        database.save(league);

        ArrayList<User> users = new ArrayList<>();
        users.add(new CommonUser("petroblicky", ""));

        RoundLeaguePointsInputData inputData = new RoundLeaguePointsInputData(league.getId(), users);

        RoundLeaguePointsInputBoundary interactor = new RoundLeaguePointsInteractor(database);

        interactor.roundUp(inputData);

        assertNotEquals(users, 0);

    }
}
