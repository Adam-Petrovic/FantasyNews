package interface_adapter.go_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import usecase.goHome.GoHomeOutputBoundary;

public class GoHomePresenter implements GoHomeOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    public GoHomePresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void execute() {
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
