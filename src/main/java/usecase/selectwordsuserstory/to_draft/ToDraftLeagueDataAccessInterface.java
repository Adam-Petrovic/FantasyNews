package usecase.selectwordsuserstory.to_draft;

/**
 * Interface for to draft use case.
 */
public interface ToDraftLeagueDataAccessInterface {
    /**
     * DAO must implement the get words method.
     * @param username Username of player whose words are being gotten.
     * @param leagueID League that the given player is in.
     * @return The words the player has drafted.
     */
    String[] getWords(String username, String leagueID);
}
