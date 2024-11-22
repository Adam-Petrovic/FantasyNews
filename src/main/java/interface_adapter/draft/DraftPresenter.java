package interface_adapter.draft;

import interface_adapter.ViewManagerModel;
import use_case.draft.DraftOutputBoundary;
import use_case.draft.DraftOutputData;

public class DraftPresenter implements DraftOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DraftViewModel draftViewModel;

    public DraftPresenter(ViewManagerModel viewManagerModel, DraftViewModel draftViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.draftViewModel = draftViewModel;
    }

    @Override
    public void showDraft(DraftOutputData draftOutputData) {
        final DraftState draftState = draftViewModel.getState();
        draftState.setUser(draftOutputData.getUser());
        this.draftViewModel.setState(draftState);
        this.draftViewModel.firePropertyChanged();

        viewManagerModel.setState(draftViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
