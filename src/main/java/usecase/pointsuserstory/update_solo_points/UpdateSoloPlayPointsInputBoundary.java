package usecase.pointsuserstory.update_solo_points;

/**
 * Input boundary for updating points in solo play mode.
 */
public interface UpdateSoloPlayPointsInputBoundary {
    /**
     * The execute method for the interactor.
     * @param updatePointsInputData the data
     */
    void execute(UpdateSoloPlayPointsInputData updatePointsInputData);
}
