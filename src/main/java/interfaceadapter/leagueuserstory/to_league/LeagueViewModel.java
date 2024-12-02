package interfaceadapter.leagueuserstory.to_league;

import data_access.Constants;
import interfaceadapter.ViewModel;

/**
 * Represents the view model for the league view.
 * Extends the {@link ViewModel} class to manage the state and view name for league-related data.
 */
public class LeagueViewModel extends ViewModel<LeagueState> {
    public LeagueViewModel() {
        super(Constants.LEAGUE_VIEW_NAME);
        setState(new LeagueState());
    }
}
