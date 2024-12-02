package usecasetests.award_points_tests;

import dataaccess.Constants;
import dataaccess.GuardianDataAccessObject;
import dataaccess.InMemoryLeagueDataAccessObject;
import entity.CommonUser;
import entity.League;
import entity.LeagueFactory;
import entity.User;
import org.junit.Test;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsDataAccessInterface;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputBoundary;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputData;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInteractor;
import usecase.pointsuserstory.update_solo_points.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AwardPointsTests {

    @Test
    public void testAwardPoints() {

        InMemoryLeagueDataAccessObject database = new InMemoryLeagueDataAccessObject();


        ArrayList<String> usernames = new ArrayList<>();
        usernames.add("petroblicky");

        League league = new League("league", usernames);
        database.save(league);

        ArrayList<User> users = new ArrayList<>();
        users.add(new CommonUser("petroblicky", ""));

        AwardLeaguePointsInputData inputData = new AwardLeaguePointsInputData(league.getId(), users);

        AwardLeaguePointsInputBoundary interactor = new AwardLeaguePointsInteractor(database);

        interactor.execute(inputData);

        assertNotEquals(users, 0);


    }
}
