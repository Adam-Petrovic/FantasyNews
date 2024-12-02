package interfaceadapter.leagueuserstory.to_league_actions;

import data_access.Constants;
import interfaceadapter.ViewModel;

public class LeagueActionsViewModel extends ViewModel<LeagueActionsState> {
    public LeagueActionsViewModel() {
        super(Constants.LEAGUE_ACTIONS_VIEW_NAME);
        setState(new LeagueActionsState());
    }
}
