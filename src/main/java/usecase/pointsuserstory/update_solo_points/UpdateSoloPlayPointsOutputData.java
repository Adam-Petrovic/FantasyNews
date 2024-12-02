package usecase.pointsuserstory.update_solo_points;

/**
 * Output data for updating points in solo play mode.
 */
public class UpdateSoloPlayPointsOutputData {
    private int[] points;

    public UpdateSoloPlayPointsOutputData(int[] points) {
        this.points = points;
    }

    /**
     * Gets the points array for a user.
     * @return the points array for a user
     */
    public int[] getPoints() {
        return points;
    }
}
