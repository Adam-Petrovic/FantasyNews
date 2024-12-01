package interface_adapter.add_word;


import interface_adapter.ViewManagerModel;
import interface_adapter.solo_play.SoloPlayState;
import interface_adapter.solo_play.SoloPlayViewModel;
import usecase.addWord.AddWordOutputBoundary;
import usecase.addWord.AddWordOutputData;

public class AddWordPresenter implements AddWordOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SoloPlayViewModel soloPlayViewModel;

    public AddWordPresenter(ViewManagerModel viewManagerModel, SoloPlayViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.soloPlayViewModel = viewModel;
    }

    @Override
    public void execute(AddWordOutputData addWordOutputData) {
        final SoloPlayState soloPlayState = soloPlayViewModel.getState();
        soloPlayViewModel.getState().getUser().setWords(addWordOutputData.getWords());
        //soloPlayState.setUser(user);
        this.soloPlayViewModel.setState(soloPlayState);
        this.soloPlayViewModel.firePropertyChanged();

        this.viewManagerModel.setState(soloPlayViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
