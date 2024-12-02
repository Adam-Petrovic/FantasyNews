package interfaceadapter.navigation.go_home;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.navigation.loggedIn.LoggedInState;
import interfaceadapter.navigation.loggedIn.LoggedInViewModel;
import usecase.navigation.goHome.GoHomeOutputBoundary;

/**
 * Presenter for go home use case.
 */
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
