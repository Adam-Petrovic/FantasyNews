package interface_adapter.to_draft;

import interface_adapter.ViewManagerModel;
import interface_adapter.to_league.LeagueState;
import use_case.to_draft.ToDraftOutputBoundary;


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
    public void execute() {
        final DraftState draftState = draftViewModel.getState();
        draftViewModel.setState(draftState);

        viewManagerModel.setState(draftViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
