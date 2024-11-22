package use_case.draft;

import entity.User;
import use_case.draft.*;

public class DraftInteractor implements DraftInputBoundary{
    private final DraftOutputBoundary draftPresenter;
    private final DraftUserDataAccessInterface userDataAccessObject;

    public DraftInteractor(DraftOutputBoundary draftPresenter,
                                DraftUserDataAccessInterface userDataAccessObject) {
        this.draftPresenter = draftPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(DraftInputData draftInputData) {
        User user = userDataAccessObject.get(draftInputData.getUsername());
        DraftOutputData outputData = new DraftOutputData(user);
        draftPresenter.showDraft(outputData);
    }
}
