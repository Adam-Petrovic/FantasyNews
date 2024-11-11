package interface_adapter.add_word;

import interface_adapter.ViewManagerModel;
import interface_adapter.solo_play.SoloPlayState;
import interface_adapter.solo_play.SoloPlayViewModel;
import use_case.add_word.AddWordOutputBoundary;
import use_case.add_word.AddWordOutputData;

public class AddWordPresenter implements AddWordOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SoloPlayViewModel soloPlayViewModel;

    public AddWordPresenter(ViewManagerModel viewManagerModel, SoloPlayViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.soloPlayViewModel = viewModel;
    }

    public void addWord(AddWordOutputData addWordOutputData) {
        final SoloPlayState soloPlayState = soloPlayViewModel.getState();
        soloPlayState.setInputtedWord(addWordOutputData.getWord());
        soloPlayState.setSelectedCell(addWordOutputData.getSelectedCell());
        this.soloPlayViewModel.setState(soloPlayState);
        this.soloPlayViewModel.firePropertyChanged();
        this.viewManagerModel.setState(soloPlayViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void refreshSoloPlayView() {
        viewManagerModel.setState(soloPlayViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
