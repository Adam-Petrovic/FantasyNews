package usecase.round_league_points;

public interface RoundLeaguePointsInputBoundary {
    /**
     * The method which rounds up the points.
     * @param roundLeaguePointsInputData the input data from the controller
     */
    void roundUp(RoundLeaguePointsInputData roundLeaguePointsInputData);
}
