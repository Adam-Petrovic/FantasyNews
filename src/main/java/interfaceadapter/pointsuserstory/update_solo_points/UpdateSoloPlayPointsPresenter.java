package interfaceadapter.pointsuserstory.update_solo_points;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.navigation.solo_play.SoloPlayViewModel;
import usecase.pointsuserstory.update_solo_points.UpdatePointsOutputBoundary;
import usecase.pointsuserstory.update_solo_points.UpdateSoloPlayPointsOutputData;

/**
 * Presenter for updating points in solo play mode.
 */
public class UpdateSoloPlayPointsPresenter implements UpdatePointsOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private SoloPlayViewModel soloPlayViewModel;

    public UpdateSoloPlayPointsPresenter(ViewManagerModel viewManagerModel,
                                         SoloPlayViewModel soloPlayViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.soloPlayViewModel = soloPlayViewModel;

    }

    @Override
    public void execute(UpdateSoloPlayPointsOutputData outputData) {
        soloPlayViewModel.getState().getUser().setPoints(outputData.getPoints());

        this.soloPlayViewModel.firePropertyChanged();

        this.viewManagerModel.setState(soloPlayViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
