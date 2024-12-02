package usecase.leagueuserstory.to_league;

/**
 * The LeagueOutputBoundary interface defines methods for handling output
 * operations related to league actions. It serves as a contract for output
 * adapters that manage the processing and presentation of league-related data.
 */
public interface LeagueOutputBoundary {
    /**
     * Executes a generic league-related action.
     */
    void execute();

    /**
     * Executes a league-related action with a specific username.
     *
     * @param username the username associated with the league action.
     */
    void execute(String username);
}
