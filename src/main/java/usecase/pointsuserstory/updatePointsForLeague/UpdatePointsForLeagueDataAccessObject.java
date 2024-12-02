package usecase.pointsuserstory.updatePointsForLeague;

/**
 * DAO for updating points for league use case.
 */
public interface UpdatePointsForLeagueDataAccessObject {
    /**
     * Gets the point for a category.
     * @param category category to get the word from
     * @return points in the user category
     */
    int getPointsForCategory(String category);
}
