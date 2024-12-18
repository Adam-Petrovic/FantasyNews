package interfaceadapter.navigation.solo_play;

import dataaccess.Constants;
import interfaceadapter.ViewModel;

/**
 * View model for solo play use case.
 */
public class SoloPlayViewModel extends ViewModel<SoloPlayState> {
    public SoloPlayViewModel() {
        super(Constants.SOLO_PLAY_VIEW_NAME);
        setState(new SoloPlayState());
    }
}
