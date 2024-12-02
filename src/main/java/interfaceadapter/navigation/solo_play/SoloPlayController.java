package interfaceadapter.navigation.solo_play;

import usecase.navigation.solo_play.SoloPlayInputBoundary;
import usecase.navigation.solo_play.SoloPlayInputData;

public class SoloPlayController {
    private final SoloPlayInputBoundary soloPlayUseCaseInteractor;

    public SoloPlayController(SoloPlayInputBoundary soloPlayUseCaseInteractor) {
        this.soloPlayUseCaseInteractor = soloPlayUseCaseInteractor;
    }

    /**
     * Executes the interactor to switch views, keeping the username intact.
     * @param username the username of the current user
     */
    public void execute(String username) {
        final SoloPlayInputData soloPlayInputData = new SoloPlayInputData(username);
        soloPlayUseCaseInteractor.execute(soloPlayInputData);
    }
}
