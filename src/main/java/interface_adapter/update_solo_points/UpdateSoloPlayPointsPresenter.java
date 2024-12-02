package interface_adapter.update_solo_points;

import interface_adapter.ViewManagerModel;
import interface_adapter.solo_play.SoloPlayViewModel;
import usecase.update_solo_points.UpdatePointsOutputBoundary;
import usecase.update_solo_points.UpdateSoloPlayPointsOutputData;

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
