package use_case.add_word;

import entity.Users.User;
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
        System.out.println(user.getWords()[0]);
        user.swapWords(addWordInputData.getCategory(), addWordInputData.getNewWord());
        userDataAccessInterface.save(user);
        System.out.println(user.getWords()[0]);
        System.out.println(user.getWords()[1]);
        System.out.println(user.getWords()[2]);
        System.out.println(user.getWords()[3]);
        final AddWordOutputData addWordOutputData = new AddWordOutputData(user.getWords());
        addWordPresenter.execute(addWordOutputData);
    }

}
