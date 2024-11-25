package use_case.create_league;

import entity.League;

public interface CreateLeagueUserDataAccessInterface {
    public void setUserLeague(String username, String leagueID);
    public boolean inLeague(String username);
    public League getLeague(String leagueID);
    }

