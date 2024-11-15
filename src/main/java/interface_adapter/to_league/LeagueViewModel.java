package interface_adapter.to_league;

import data_access.Constants;
import interface_adapter.ViewModel;

public class LeagueViewModel extends ViewModel<LeagueState> {
    public LeagueViewModel() {
        super(Constants.LEAGUE_VIEW_NAME);
        setState(new LeagueState());
    }
}