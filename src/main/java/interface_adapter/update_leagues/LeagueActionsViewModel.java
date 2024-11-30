package interface_adapter.update_leagues;

import data_access.Constants;
import interface_adapter.ViewModel;

public class LeagueActionsViewModel extends ViewModel<LeagueActionsState> {
    public LeagueActionsViewModel() {
        super(Constants.LEAGUE_ACTIONS_VIEW_NAME);
        setState(new LeagueActionsState());
    }
}
