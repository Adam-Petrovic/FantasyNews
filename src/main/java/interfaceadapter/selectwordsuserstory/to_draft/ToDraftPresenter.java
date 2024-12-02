package interfaceadapter.selectwordsuserstory.to_draft;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.selectwordsuserstory.draft_words.DraftState;
import interfaceadapter.selectwordsuserstory.draft_words.DraftViewModel;
import usecase.selectwordsuserstory.to_draft.ToDraftOutputBoundary;
import usecase.selectwordsuserstory.to_draft.ToDraftOutputData;

/**
 * Presenter for To Draft.
 */
public class ToDraftPresenter implements ToDraftOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DraftViewModel draftViewModel;

    public ToDraftPresenter(ViewManagerModel viewManagerModel, DraftViewModel draftViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.draftViewModel = draftViewModel;
    }

    @Override
    public void execute(ToDraftOutputData output) {
        final DraftState draftState = draftViewModel.getState();
        draftState.setUsername(output.getUsername());
        draftState.setLeagueID(output.getLeagueID());
        draftState.setWords(output.getWords());
        draftViewModel.setState(draftState);
        draftViewModel.firePropertyChanged();

        viewManagerModel.setState(draftViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
