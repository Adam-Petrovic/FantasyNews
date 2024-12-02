package interfaceadapter.navigation.solo_play;

import interfaceadapter.ViewManagerModel;
import usecase.navigation.solo_play.SoloPlayOutputBoundary;
import usecase.navigation.solo_play.SoloPlayOutputData;

public class SoloPlayPresenter implements SoloPlayOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SoloPlayViewModel soloPlayViewModel;

    public SoloPlayPresenter(ViewManagerModel viewManagerModel,
                             SoloPlayViewModel soloPlayViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.soloPlayViewModel = soloPlayViewModel;
    }

    @Override
    public void showSoloPlay(SoloPlayOutputData outputData) {
        final SoloPlayState soloPlayState = soloPlayViewModel.getState();
        soloPlayState.setUser(outputData.getUser());
        this.soloPlayViewModel.setState(soloPlayState);
        this.soloPlayViewModel.firePropertyChanged();

        this.viewManagerModel.setState(soloPlayViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
