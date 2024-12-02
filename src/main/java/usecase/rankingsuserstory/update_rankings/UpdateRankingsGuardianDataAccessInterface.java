package usecase.rankingsuserstory.update_rankings;

/**
 * The UpdateRankingsGuardianDataAccessInterface defines the contract for
 * accessing data related to rankings updates, specifically for retrieving
 * points associated with different categories.
 */
public interface UpdateRankingsGuardianDataAccessInterface {
    /**
     * Gets the point for a category.
     * @param category the category.
     * @return the points
     */
    int getPointsForCategory(String category);
}
