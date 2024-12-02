package usecase.addWord;

import entity.User;
import usecase.solo_play.SoloPlayUserDataAccessInterface;

/**
 * Interactor for add words.
 */
public class AddWordInteractor implements AddWordInputBoundary {
    private final AddWordOutputBoundary addWordPresenter;
    private final SoloPlayUserDataAccessInterface userDataAccessInterface;

    public AddWordInteractor(AddWordOutputBoundary addWordPresenter,
                             SoloPlayUserDataAccessInterface userDataAccessInterface) {
        this.addWordPresenter = addWordPresenter;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    /**
     * Execute method for add word interactor.
     * @param addWordInputData Contains username, word category, and new word data.
     */
    public void execute(AddWordInputData addWordInputData) {
        User user = userDataAccessInterface.get(addWordInputData.getUsername());
        user.swapWords(addWordInputData.getCategory(), addWordInputData.getNewWord());
        userDataAccessInterface.save(user);
        final AddWordOutputData addWordOutputData = new AddWordOutputData(user.getWords());
        addWordPresenter.execute(addWordOutputData);
    }

}
