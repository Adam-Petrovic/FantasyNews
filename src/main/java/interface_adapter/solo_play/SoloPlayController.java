package interface_adapter.solo_play;

import usecase.solo_play.SoloPlayInputBoundary;
import usecase.solo_play.SoloPlayInputData;

public class SoloPlayController {
    private final SoloPlayInputBoundary soloPlayUseCaseInteractor;


    public SoloPlayController(SoloPlayInputBoundary soloPlayUseCaseInteractor) {
        this.soloPlayUseCaseInteractor = soloPlayUseCaseInteractor;
    }

    public void execute(String username) {
        final SoloPlayInputData soloPlayInputData = new SoloPlayInputData(username);
        soloPlayUseCaseInteractor.execute(soloPlayInputData);
    }
}
