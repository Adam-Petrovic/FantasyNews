package interfaceadapter.leagueuserstory.to_league_actions;

import data_access.Constants;
import interfaceadapter.ViewModel;

/**
 * The LeagueActionsViewModel class serves as the ViewModel for managing the state
 * and view-specific logic related to league actions. It extends the generic
 * {@link ViewModel} class with a state of type {@link LeagueActionsState}.
 */
public class LeagueActionsViewModel extends ViewModel<LeagueActionsState> {
    public LeagueActionsViewModel() {
        super(Constants.LEAGUE_ACTIONS_VIEW_NAME);
        setState(new LeagueActionsState());
    }
}
