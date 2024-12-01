package interface_adapter.to_draft;

import interface_adapter.ViewManagerModel;
import interface_adapter.draft_words.DraftState;
import interface_adapter.draft_words.DraftViewModel;
import usecase.to_draft.ToDraftOutputBoundary;
import usecase.to_draft.ToDraftOutputData;


public class ToDraftPresenter implements ToDraftOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DraftViewModel draftViewModel;

    public ToDraftPresenter(ViewManagerModel viewManagerModel, DraftViewModel draftViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.draftViewModel = draftViewModel;
    }

//    @Override
//    public void showDraft(ToDraftOutputData toDraftOutputData) {
//        final DraftState draftState = draftViewModel.getState();
//        draftState.setUser(toDraftOutputData.getUser());
//        this.draftViewModel.setState(draftState);
//        this.draftViewModel.firePropertyChanged();
//
//        viewManagerModel.setState(draftViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }
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
