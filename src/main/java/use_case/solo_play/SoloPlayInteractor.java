package use_case.solo_play;

import data_access.GuardianDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;

public class SoloPlayInteractor implements SoloPlayInputBoundary{
    private final SoloPlayOutputBoundary soloPlayPresenter;
    private final UserFactory userFactory;

    public SoloPlayInteractor(SoloPlayOutputBoundary soloPlayPresenter, UserFactory userFactory) {
        this.soloPlayPresenter = soloPlayPresenter;
        this.userFactory = userFactory;
    }


    @Override
    public void execute(SoloPlayInputData soloPlayInputData) {
        User user = this.userFactory.create(soloPlayInputData.getUsername(), soloPlayInputData.getPassword());
        SoloPlayOutputData outputData = new SoloPlayOutputData(user);
        soloPlayPresenter.showSoloPlay(outputData);
    }
}
