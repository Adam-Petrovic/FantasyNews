package interface_adapter.solo_play;

import interface_adapter.ViewModel;

public class SoloPlayViewModel extends ViewModel<SoloPlayState> {
    public SoloPlayViewModel() {
        super("solo play");
        setState(new SoloPlayState());
    }
}
