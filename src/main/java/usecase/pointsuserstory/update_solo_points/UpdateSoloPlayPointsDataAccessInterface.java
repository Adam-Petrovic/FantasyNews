package usecase.pointsuserstory.update_solo_points;

/**
 * Data access interface for updating points in solo play mode.
 */
public interface UpdateSoloPlayPointsDataAccessInterface {
    /**
     * Gets the point for a category.
     * @param category the catergor
     * @return the points
     */
    int getPointsForCategory(String category);
}
