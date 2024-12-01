package usecase.award_league_points;

public interface AwardLeaguePointsDataAccessInterface {
    void updatePointsForUser(String leagueID, String name, int numUsers);
}
