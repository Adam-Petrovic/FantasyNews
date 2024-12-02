package interfaceadapter.navigation.login;

import dataaccess.Constants;
import interfaceadapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    public LoginViewModel() {
        super(Constants.LOG_IN_VIEW_NAME);
        setState(new LoginState());
    }

}
