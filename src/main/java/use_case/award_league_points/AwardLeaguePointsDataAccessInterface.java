package use_case.award_league_points;

public interface AwardLeaguePointsDataAccessInterface {
    void updatePointsForUser(String leagueID, String name, int numUsers);
}
