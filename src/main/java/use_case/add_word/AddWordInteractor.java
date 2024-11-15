package use_case.add_word;

import entity.User;
import interface_adapter.ViewModel;
import use_case.solo_play.SoloPlayUserDataAccessInterface;

public class AddWordInteractor implements AddWordInputBoundary{
    private final AddWordOutputBoundary addWordPresenter;
    private final SoloPlayUserDataAccessInterface userDataAccessInterface;
    /*
    Adding more lines
    Cuz
    I
    want
    to
    see
    if
    commit
    works
     */
    public AddWordInteractor(AddWordOutputBoundary addWordPresenter,
                             SoloPlayUserDataAccessInterface userDataAccessInterface) {
        this.addWordPresenter = addWordPresenter;
        this.userDataAccessInterface = userDataAccessInterface;
    }
    /*
    This interactor calls the swap words method in User in order to change the User's stored data
    */
    public void execute(AddWordInputData addWordInputData) {
        User user = userDataAccessInterface.get(addWordInputData.getUsername());
        user.swapWords(addWordInputData.getCategory(), addWordInputData.getNewWord());
        addWordPresenter.execute();
    }

}
