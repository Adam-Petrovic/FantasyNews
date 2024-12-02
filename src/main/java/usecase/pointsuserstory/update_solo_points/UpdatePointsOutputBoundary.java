package usecase.pointsuserstory.update_solo_points;

/**
 * Output boundary for updating points use case.
 */
public interface UpdatePointsOutputBoundary {
    /**
     * The execute method for the presenter.
     * @param updatePointsOutputData the data
     */
    void execute(UpdateSoloPlayPointsOutputData updatePointsOutputData);
}
