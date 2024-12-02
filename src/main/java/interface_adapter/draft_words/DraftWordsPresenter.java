package interface_adapter.draft_words;

import interface_adapter.ViewManagerModel;
import usecase.draft_words.DraftWordsOutputBoundary;
import usecase.draft_words.DraftWordsOutputData;

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
}
