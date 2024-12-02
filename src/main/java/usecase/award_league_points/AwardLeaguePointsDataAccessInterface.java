package usecase.award_league_points;

public interface AwardLeaguePointsDataAccessInterface {
    /**
     * The method to update points for a User.
     * @param leagueID the name of the league of the user
     * @param name the name of the user
     * @param numUsers the amount of users in the league
     */
    void updatePointsForUser(String leagueID, String name, int numUsers);
}
