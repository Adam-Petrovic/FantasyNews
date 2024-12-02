package interfaceadapter.leagueuserstory.to_league;

import java.util.ArrayList;

import entity.League;

/**
 * Represents the state of the league, including information about leagues,
 * league IDs, the associated username, and any error messages.
 */
public class LeagueState {
    private ArrayList<String> leagueIds;
    private ArrayList<League> leagues;
    private String username;
    private String errorMessage;

    /**
     * Gets the list of league IDs associated with the state.
     *
     * @return a list of league IDs.
     */
    public ArrayList<String> getLeagueIds() {
        return leagueIds;
    }

    /**
     * Sets the list of league IDs associated with the state.
     *
     * @param leagueID the list of league IDs to be set.
     */
    public void setLeagueIds(ArrayList<String> leagueID) {
        this.leagueIds = leagueID;
    }

    /**
     * Gets the list of leagues associated with the state.
     *
     * @return a list of leagues.
     */
    public ArrayList<League> getLeagues() {
        return leagues;
    }

    /**
     * Sets the list of leagues associated with the state.
     *
     * @param leagues the list of leagues to be set.
     */
    public void setLeagues(ArrayList<League> leagues) {
        this.leagues = leagues;
    }

    /**
     * Gets the username associated with the state.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the state.
     *
     * @param username the username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets an error message describing an issue with the state.
     *
     * @param errorMessage the error message to be set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message associated with the state.
     *
     * @return the error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
