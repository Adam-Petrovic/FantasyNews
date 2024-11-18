package use_case.add_friends;

import entity.User;

public class AddFriendsInteractor implements AddFriendsInputBoundary {
    private final AddFriendsOutputBoundary addFriendsPresenter;
    private final AddFriendsUserDataAccessInterface userDataAccessObject;

    public AddFriendsInteractor(AddFriendsOutputBoundary addFriendsPresenter,
                                AddFriendsUserDataAccessInterface userDataAccessObject) {
        this.addFriendsPresenter = addFriendsPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(AddFriendsInputData addFriendsInputData) {
        User user = userDataAccessObject.get(addFriendsInputData.getUsername());
        AddFriendsOutputData outputData = new AddFriendsOutputData(user);
        addFriendsPresenter.execute(outputData);
    }
}
