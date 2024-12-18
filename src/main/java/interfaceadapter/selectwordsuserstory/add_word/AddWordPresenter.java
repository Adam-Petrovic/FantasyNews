package interfaceadapter.selectwordsuserstory.add_word;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.navigation.solo_play.SoloPlayState;
import interfaceadapter.navigation.solo_play.SoloPlayViewModel;
import usecase.selectwordsuserstory.addWord.AddWordOutputBoundary;
import usecase.selectwordsuserstory.addWord.AddWordOutputData;

/**
 * Presenter for add word.
 */
public class AddWordPresenter implements AddWordOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SoloPlayViewModel soloPlayViewModel;

    public AddWordPresenter(ViewManagerModel viewManagerModel, SoloPlayViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.soloPlayViewModel = viewModel;
    }

    /**
     * Presenter takes the new words and puts them on solo play state.
     * @param addWordOutputData Outputdata from add word method, words that user now stores.
     */
    @Override
    public void execute(AddWordOutputData addWordOutputData) {
        final SoloPlayState soloPlayState = soloPlayViewModel.getState();
        soloPlayViewModel.getState().getUser().setWords(addWordOutputData.getWords());

        this.soloPlayViewModel.setState(soloPlayState);
        this.soloPlayViewModel.firePropertyChanged();

        this.viewManagerModel.setState(soloPlayViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
