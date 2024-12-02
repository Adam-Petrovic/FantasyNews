package interfaceadapter.navigation.logout;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.navigation.loggedIn.LoggedInState;
import interfaceadapter.navigation.loggedIn.LoggedInViewModel;
import interfaceadapter.navigation.login.LoginState;
import interfaceadapter.navigation.login.LoginViewModel;
import usecase.navigation.logout.LogoutOutputBoundary;
import usecase.navigation.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // We need to switch to the login view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string.

        // TODO: have prepareSuccessView update the LoggedInState
        // 1. get the LoggedInState out of the appropriate View Model,
        // 2. set the username in the state to the empty string
        // 3. set the state in the LoggedInViewModel to the updated state
        // 4. firePropertyChanged so that the View that is listening is updated.
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername("");
        this.loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();

        // TODO: have prepareSuccessView update the LoginState
        // 5. get the LoginState out of the appropriate View Model,
        // 6. set the username and password in the state to the empty string
        // 7. set the state in the LoginViewModel to the updated state
        // 8. firePropertyChanged so that the View that is listening is updated.
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername("");
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}