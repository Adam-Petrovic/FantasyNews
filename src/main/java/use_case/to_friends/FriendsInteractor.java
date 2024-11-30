package use_case.to_friends;

import entity.User;

public class FriendsInteractor implements FriendsInputBoundary {
    private final FriendsOutputBoundary addFriendsPresenter;
    private final FriendsUserDataAccessInterface userDataAccessObject;

    public FriendsInteractor(FriendsOutputBoundary addFriendsPresenter,
                             FriendsUserDataAccessInterface userDataAccessObject) {
        this.addFriendsPresenter = addFriendsPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(FriendsInputData friendsInputData) {
        User user = userDataAccessObject.get(friendsInputData.getUsername());
        FriendsOutputData outputData = new FriendsOutputData(user);
        addFriendsPresenter.execute(outputData);
    }
}
