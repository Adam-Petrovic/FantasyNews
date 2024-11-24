package use_case.create_league;

public interface CreateLeagueUserDataAccessInterface {
    public void setLeague(String username, String leagueID);
    public boolean inLeague(String username);

    }

