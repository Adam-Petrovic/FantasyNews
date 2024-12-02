package usecase.award_league_points;

public interface AwardLeaguePointsInputBoundary {

    /**
     * The execution method for an interactor.
     * @param awardLeaguePointsInputData the input data following clean architecture
     */
    void execute(AwardLeaguePointsInputData awardLeaguePointsInputData);
}
