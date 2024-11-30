package use_case.add_new_friend;

import data_access.Constants;
import data_access.GuardianDataAccessObject;
import entity.User;
import use_case.add_friends.AddFriendsUserDataAccessInterface;

import java.util.HashMap;
import java.util.List;

public class AddNewFriendInteractor implements AddNewFriendInputBoundary{
    private final AddNewFriendOutputBoundary addNewFriendPresenter;
    private final AddFriendsUserDataAccessInterface userDataAccessObject;
    private final GuardianDataAccessObject guardianDataAccessObject;

    public AddNewFriendInteractor(AddNewFriendOutputBoundary addNewFriendPresenter,
                                  AddFriendsUserDataAccessInterface userDataAccessObject, GuardianDataAccessObject guardianDataAccessObject) {
        this.addNewFriendPresenter = addNewFriendPresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.guardianDataAccessObject = guardianDataAccessObject;
    }

    public void execute(AddNewFriendInputData addNewFriendInputData) {
        User user = userDataAccessObject.get(addNewFriendInputData.getUsername());
        List<User> friends = user.getFriends();
        HashMap<User, Integer> userPoints = new HashMap<>();
        userPoints.put(user, sumPoints(getInts(user)));

        if (userDataAccessObject.existsByName(addNewFriendInputData.getFriend_username())) {
            User newFriend = userDataAccessObject.get(addNewFriendInputData.getFriend_username());
            user.addFriend(newFriend);
            for (User friend : friends) {
                userPoints.put(friend, sumPoints(getInts(friend)));
            }
            final AddNewFriendOutputData addNewFriendOutputData = new AddNewFriendOutputData(newFriend, userPoints);
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

    private int[] getInts(User user) {
        int[] points = new int[Constants.NUM_CATEGORIES];
        for (int i = 0; i < Constants.NUM_CATEGORIES; i++) {
            points[i] = guardianDataAccessObject.getPointsForCategory(user.getWordFromCategory(Constants.CATEGORIES[i]));
        }
        return points;
    }

    private int sumPoints(int[] points) {
        int sum = 0;
        for (int point : points) {
            sum += point;
        }
        return sum;
    }
}
