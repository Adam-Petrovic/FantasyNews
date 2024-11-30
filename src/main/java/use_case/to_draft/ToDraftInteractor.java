package use_case.to_draft;
import entity.User;
import use_case.solo_play.*;

public class ToDraftInteractor implements ToDraftInputBoundary {

    private final ToDraftOutputBoundary draftPresenter;


    public ToDraftInteractor(ToDraftOutputBoundary draftPresenter) {
        this.draftPresenter = draftPresenter;
    }


    @Override
    public void execute(ToDraftInputData toDraftInputData) {

        ToDraftOutputData outputData = new ToDraftOutputData();
        draftPresenter.showSoloPlay(outputData);
    }
}
