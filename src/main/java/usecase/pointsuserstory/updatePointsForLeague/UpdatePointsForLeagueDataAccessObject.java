package usecase.pointsuserstory.updatePointsForLeague;

public interface UpdatePointsForLeagueDataAccessObject {
    /**
     * Gets the point for a category.
     * @param category category to get the word from
     * @return points in the user category
     */
    int getPointsForCategory(String category);
}
