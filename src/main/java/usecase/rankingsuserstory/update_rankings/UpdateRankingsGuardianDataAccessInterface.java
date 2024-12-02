package usecase.rankingsuserstory.update_rankings;

public interface UpdateRankingsGuardianDataAccessInterface {
    /**
     * Gets the point for a category.
     * @param category the catergor
     * @return the points
     */
    int getPointsForCategory(String category);
}
