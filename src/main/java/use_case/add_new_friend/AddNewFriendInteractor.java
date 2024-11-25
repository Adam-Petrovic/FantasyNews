package use_case.add_new_friend;

import use_case.add_friends.AddFriendsUserDataAccessInterface;

public class AddNewFriendInteractor implements AddNewFriendInputBoundary{
    private final AddNewFriendOutputBoundary addNewFriendPresenter;
    private final AddFriendsUserDataAccessInterface userDataAccessObject;

    public AddNewFriendInteractor(AddNewFriendOutputBoundary addNewFriendPresenter,
                                  AddFriendsUserDataAccessInterface userDataAccessObject) {
        this.addNewFriendPresenter = addNewFriendPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(AddNewFriendInputData addNewFriendInputData) {
        if (userDataAccessObject.existsByName(addNewFriendInputData.getFriend_username())) {
            final AddNewFriendOutputData addNewFriendOutputData = new AddNewFriendOutputData(userDataAccessObject.get(addNewFriendInputData.getFriend_username()));
            userDataAccessObject.get(addNewFriendInputData.getUsername()).addFriend(addNewFriendOutputData.getFriend());
            addNewFriendPresenter.prepareSuccessView(addNewFriendOutputData);
        }
        else if (!userDataAccessObject.existsByName(addNewFriendInputData.getFriend_username())){
            addNewFriendPresenter.prepareFailView("User " + addNewFriendInputData.getFriend_username() + " not found.");
        }
//        else if (addNewFriendInputData.getFriend_username().equals(addNewFriendInputData.getUsername())){
//            addNewFriendPresenter.prepareFailView("You cannot add yourself as a friend :(");
//        }
//        else if (userDataAccessObject.get(addNewFriendInputData.getUsername()).getFriends().contains(userDataAccessObject.get(addNewFriendInputData.getFriend_username()))) {
//            addNewFriendPresenter.prepareFailView(addNewFriendInputData.getFriend_username() + " is already your friend!");
//        }
    }
}
