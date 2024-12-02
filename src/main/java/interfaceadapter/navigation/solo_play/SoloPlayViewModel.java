package interfaceadapter.navigation.solo_play;

import data_access.Constants;
import interfaceadapter.ViewModel;

public class SoloPlayViewModel extends ViewModel<SoloPlayState> {
    public SoloPlayViewModel() {
        super(Constants.SOLO_PLAY_VIEW_NAME);
        setState(new SoloPlayState());
    }
}