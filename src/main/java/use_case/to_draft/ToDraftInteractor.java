package use_case.to_draft;

import entity.User;

public class ToDraftInteractor implements ToDraftInputBoundary {
    private final ToDraftOutputBoundary draftPresenter;
    private final ToDraftUserDataAccessInterface userDataAccessObject;

    public ToDraftInteractor(ToDraftOutputBoundary draftPresenter,
                             ToDraftUserDataAccessInterface userDataAccessObject) {
        this.draftPresenter = draftPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(ToDraftInputData toDraftInputData) {
        User user = userDataAccessObject.get(toDraftInputData.getUsername());
        ToDraftOutputData outputData = new ToDraftOutputData(user);
        //draftPresenter.showDraft(outputData);
    }
}
