package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {

    public static final String GREETING = "Welcome,";
    public static final String LOG_OUT_LABEL = "Log out";
    public static final String SOLO_PLAY_LABEL = "Solo Play";

    public LoggedInViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }

}
