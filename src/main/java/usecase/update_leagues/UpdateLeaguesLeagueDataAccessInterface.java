package usecase.update_leagues;

import entity.League;

import java.util.ArrayList;

public interface UpdateLeaguesLeagueDataAccessInterface {
    void saveNewLeague(String leagueID, String username);

    boolean LeagueExists(String leagueID);

    void addUserToLeague(String leagueID, String username);

    ArrayList<League> getLeagues(ArrayList<String> userLeagueIDList);

}
