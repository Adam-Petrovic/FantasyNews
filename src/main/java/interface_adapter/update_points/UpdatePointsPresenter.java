package interface_adapter.update_points;

import interface_adapter.ViewManagerModel;
import interface_adapter.solo_play.SoloPlayViewModel;
import use_case.update_solo_points.UpdatePointsOutputBoundary;

public class UpdatePointsPresenter implements UpdatePointsOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private SoloPlayViewModel soloPlayViewModel;

    public UpdatePointsPresenter(ViewManagerModel viewManagerModel,
                                 SoloPlayViewModel soloPlayViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.soloPlayViewModel = soloPlayViewModel;

    }

    @Override
    public void execute() {
        this.soloPlayViewModel.firePropertyChanged();

        this.viewManagerModel.setState(soloPlayViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
