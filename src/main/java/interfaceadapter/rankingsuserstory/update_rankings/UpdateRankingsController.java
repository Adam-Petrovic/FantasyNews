package interfaceadapter.rankingsuserstory.update_rankings;

import usecase.rankingsuserstory.update_rankings.UpdateRankingsInputBoundary;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsInputData;

/**
 * The {@code UpdateRankingsController} class acts as the controller in the application architecture
 * to handle input for updating rankings and delegate the process to the interactor.
 * This class translates user actions or input into data required by the use case layer.
 */
public class UpdateRankingsController {
    private UpdateRankingsInputBoundary updateRankingsInteractor;

    public UpdateRankingsController(UpdateRankingsInputBoundary updateRankingsInteractor) {
        this.updateRankingsInteractor = updateRankingsInteractor;
    }

    /**
     * Executes the update rankings process for the specified league.
     * This method creates an {@code UpdateRankingsInputData} object with the provided league ID
     * and passes it to the interactor for processing.
     *
     * @param leagueID the unique identifier of the league for which rankings need to be updated
     */
    public void execute(String leagueID) {
        UpdateRankingsInputData updateRankingsInputData = new UpdateRankingsInputData(leagueID);
        updateRankingsInteractor.execute(updateRankingsInputData);
    }
}
