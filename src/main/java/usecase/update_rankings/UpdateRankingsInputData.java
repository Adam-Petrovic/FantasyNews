package usecase.update_rankings;

/**
 * The {@code UpdateRankingsInputData} class encapsulates the input data required for the
 * ranking update process, specifically the league identifier.
 * This class is immutable, ensuring thread-safety and consistency of the input data.
 */
public class UpdateRankingsInputData {
    private final String leagueID;

    public UpdateRankingsInputData(String leagueID) {
        this.leagueID = leagueID;
    }

    /**
     * Retrieves the league identifier.
     * This method is safe to use as the class is immutable.
     *
     * @return the unique identifier of the league
     */
    public String getLeague() {
        return leagueID;
    }
}
