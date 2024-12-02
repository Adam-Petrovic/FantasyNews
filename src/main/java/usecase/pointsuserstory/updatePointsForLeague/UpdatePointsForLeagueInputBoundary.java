package usecase.pointsuserstory.updatePointsForLeague;

/**
 * Input boundary for updating points for league use case.
 */
public interface UpdatePointsForLeagueInputBoundary {
    /**
     * The execute method for every interactor.
     * @param updateLeaguePointsInputData the data
     */
    void execute(UpdatePointsForLeagueInputData updateLeaguePointsInputData);
}
