package usecasetests.updatePointsForLeague;

import dataaccess.InMemoryLeagueDataAccessObject;
import entity.CommonUser;
import entity.League;
import entity.User;
import org.junit.Test;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputBoundary;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputData;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInteractor;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueInputBoundary;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueInputData;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueInteractor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UpdatePointsForLeagueTests {

    @Test
    public void testUpdatePointsForLeague() {
        InMemoryLeagueDataAccessObject database = new InMemoryLeagueDataAccessObject();


        ArrayList<String> usernames = new ArrayList<>();
        usernames.add("petroblicky");

        League league = new League("league", usernames);
        database.save(league);

        ArrayList<User> users = new ArrayList<>();
        users.add(new CommonUser("petroblicky", ""));

        UpdatePointsForLeagueInputData inputData = new UpdatePointsForLeagueInputData(league.getId(), users);

        UpdatePointsForLeagueInputBoundary interactor = new UpdatePointsForLeagueInteractor(database);

        interactor.execute(inputData);

        assertNotEquals(users, 0);

        assertEquals(inputData.getLeagueID(), league.getId());
    }
}
