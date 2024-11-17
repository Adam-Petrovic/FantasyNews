package use_case.add_word;

import entity.User;
import interface_adapter.ViewModel;
import use_case.solo_play.SoloPlayUserDataAccessInterface;

public class AddWordInteractor implements AddWordInputBoundary{
    private final AddWordOutputBoundary addWordPresenter;
    private final SoloPlayUserDataAccessInterface userDataAccessInterface;

    public AddWordInteractor(AddWordOutputBoundary addWordPresenter,
                             SoloPlayUserDataAccessInterface userDataAccessInterface) {
        this.addWordPresenter = addWordPresenter;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    public void execute(AddWordInputData addWordInputData) {
        User user = userDataAccessInterface.get(addWordInputData.getUsername());
        user.swapWords(addWordInputData.getCategory(), addWordInputData.getNewWord());
        userDataAccessInterface.save(user);
        addWordPresenter.execute();
    }

}
