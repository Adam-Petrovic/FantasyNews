package interfaceadapter.rankingsuserstory.to_rankings;

import data_access.Constants;
import interfaceadapter.ViewModel;

/**
 * The RankingsViewModel class is a part of the presentation layer and manages
 * the state of the rankings view. It extends the generic ViewModel class
 * with a specific state type of RankingsState.
 */
public class RankingsViewModel extends ViewModel<RankingsState> {
    public RankingsViewModel() {
        super(Constants.RANKINGS_VIEW_NAME);
        setState(new RankingsState());
    }
}
