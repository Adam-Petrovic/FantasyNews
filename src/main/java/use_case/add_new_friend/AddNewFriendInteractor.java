package use_case.add_new_friend;

public class AddNewFriendInteractor implements AddNewFriendInputBoundary{
    private final AddNewFriendOutputBoundary addNewFriendPresenter;
    private final AddNewFriendUserDataAccessInterface userDataAccessObject;

    public AddNewFriendInteractor(AddNewFriendOutputBoundary addNewFriendPresenter,
                                  AddNewFriendUserDataAccessInterface userDataAccessObject) {
        this.addNewFriendPresenter = addNewFriendPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(AddNewFriendInputData addNewFriendInputData) {
        if (userDataAccessObject.existsByName(addNewFriendInputData.getFriend_username())) {
            final AddNewFriendOutputData addNewFriendOutputData = new AddNewFriendOutputData(userDataAccessObject.get(addNewFriendInputData.getFriend_username()));
            addNewFriendPresenter.prepareSuccessView(addNewFriendOutputData);
        }
        else {
            addNewFriendPresenter.prepareFailView("User " + addNewFriendInputData.getFriend_username() + " not found.");
        }
    }
}
