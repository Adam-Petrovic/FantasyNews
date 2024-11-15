package interface_adapter.add_word;

import interface_adapter.ViewManagerModel;
import interface_adapter.solo_play.SoloPlayState;
import interface_adapter.solo_play.SoloPlayViewModel;
import use_case.add_word.AddWordOutputBoundary;

public class AddWordPresenter implements AddWordOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SoloPlayViewModel soloPlayViewModel;

    public AddWordPresenter(ViewManagerModel viewManagerModel, SoloPlayViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.soloPlayViewModel = viewModel;
    }

    @Override
    public void execute() {
        final SoloPlayState soloPlayState = soloPlayViewModel.getState();
        this.soloPlayViewModel.setState(soloPlayState);
        this.soloPlayViewModel.firePropertyChanged();

        this.viewManagerModel.setState(soloPlayViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
