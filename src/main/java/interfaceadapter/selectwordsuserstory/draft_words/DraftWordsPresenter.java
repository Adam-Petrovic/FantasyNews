package interfaceadapter.selectwordsuserstory.draft_words;

import interfaceadapter.ViewManagerModel;
import usecase.selectwordsuserstory.draft_words.DraftWordsOutputBoundary;
import usecase.selectwordsuserstory.draft_words.DraftWordsOutputData;

/**
 * Presenter for draft words.
 */
public class DraftWordsPresenter implements DraftWordsOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DraftViewModel draftViewModel;

    public DraftWordsPresenter(ViewManagerModel viewManagerModel, DraftViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.draftViewModel = viewModel;
    }

    @Override
    public void showDraftedWords(DraftWordsOutputData draftWordsOutputData) {
        final DraftState draftState = draftViewModel.getState();
        draftState.setWords(draftWordsOutputData.getWords());
        draftViewModel.setState(draftState);
        draftViewModel.firePropertyChanged();

        this.viewManagerModel.setState(draftViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage){
        final DraftState draftState = draftViewModel.getState();
        draftState.setDraftError(errorMessage);
        draftViewModel.firePropertyChanged();
    }

}
