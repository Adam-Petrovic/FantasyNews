package usecase.navigation.solo_play;

import entity.User;

/**
 * Interactor for solo play use case.
 */
public class SoloPlayInteractor implements SoloPlayInputBoundary {
    private final SoloPlayOutputBoundary soloPlayPresenter;
    private final SoloPlayUserDataAccessInterface userDataAccessObject;

    public SoloPlayInteractor(SoloPlayOutputBoundary soloPlayPresenter,
                              SoloPlayUserDataAccessInterface userDataAccessObject) {
        this.soloPlayPresenter = soloPlayPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(SoloPlayInputData soloPlayInputData) {
        User user = userDataAccessObject.get(soloPlayInputData.getUsername());

        SoloPlayOutputData outputData = new SoloPlayOutputData(user);
        soloPlayPresenter.showSoloPlay(outputData);
    }
}
