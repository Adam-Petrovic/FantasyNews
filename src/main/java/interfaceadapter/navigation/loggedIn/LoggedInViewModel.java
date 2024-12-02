package interfaceadapter.navigation.loggedIn;

import dataaccess.Constants;
import interfaceadapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {

    public static final String GREETING = "Welcome,";
    public static final String LOG_OUT_LABEL = "Log out";
    public static final String SOLO_PLAY_LABEL = "Solo Play";

    public LoggedInViewModel() {
        super(Constants.LOGGED_IN_VIEW_NAME);
        setState(new LoggedInState());
    }

}
