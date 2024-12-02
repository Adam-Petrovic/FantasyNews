package usecase.frienduserstory.to_friends;

import entity.User;

/**
 * Interactor for switching to friends view.
 */
public class FriendsInteractor implements FriendsInputBoundary {
    private final FriendsOutputBoundary addFriendsPresenter;
    private final FriendsUserDataAccessInterface userDataAccessObject;

    public FriendsInteractor(FriendsOutputBoundary addFriendsPresenter,
                             FriendsUserDataAccessInterface userDataAccessObject) {
        this.addFriendsPresenter = addFriendsPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    /**
     * Executes switching to friends view with friendsInputData.
     * @param friendsInputData the input data.
     */
    public void execute(FriendsInputData friendsInputData) {
        User user = userDataAccessObject.get(friendsInputData.getUsername());
        FriendsOutputData outputData = new FriendsOutputData(user);
        addFriendsPresenter.execute(outputData);
    }
}
