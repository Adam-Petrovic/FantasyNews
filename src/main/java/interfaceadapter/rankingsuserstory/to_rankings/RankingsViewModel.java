package interfaceadapter.rankingsuserstory.to_rankings;

import data_access.Constants;
import interfaceadapter.ViewModel;

public class RankingsViewModel extends ViewModel<RankingsState> {
    public RankingsViewModel() {
        super(Constants.RANKINGS_VIEW_NAME);
        setState(new RankingsState());
    }
}
