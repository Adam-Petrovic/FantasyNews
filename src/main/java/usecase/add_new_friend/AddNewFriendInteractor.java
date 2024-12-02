package usecase.add_new_friend;

import java.util.HashMap;
import java.util.Map;

import data_access.Constants;
import data_access.GuardianDataAccessObject;
import entity.User;
import usecase.to_friends.FriendsUserDataAccessInterface;

/**
 * Interactor for add new friend use case.
 */
public class AddNewFriendInteractor implements AddNewFriendInputBoundary {
    private final AddNewFriendOutputBoundary addNewFriendPresenter;
    private final FriendsUserDataAccessInterface userDataAccessObject;
    private final GuardianDataAccessObject guardianDataAccessObject;

    public AddNewFriendInteractor(AddNewFriendOutputBoundary addNewFriendPresenter,
                                  FriendsUserDataAccessInterface userDataAccessObject,
                                  GuardianDataAccessObject guardianDataAccessObject) {
        this.addNewFriendPresenter = addNewFriendPresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.guardianDataAccessObject = guardianDataAccessObject;
    }

    @Override
    public void execute(AddNewFriendInputData addNewFriendInputData) {
        User user = userDataAccessObject.get(addNewFriendInputData.getUsername());
        // List<User> friends = user.getFriends();
        Map<User, Integer> userPoints = new HashMap<>();
        // userPoints.put(user, sumPoints(getInts(user)));
        if (addNewFriendInputData.getFriendUsername().equals(addNewFriendInputData.getUsername())) {
            addNewFriendPresenter.prepareFailView("You cannot add yourself as a friend :(");
        }
        else if (!userDataAccessObject.existsByName(addNewFriendInputData.getFriendUsername())) {
            addNewFriendPresenter.prepareFailView("User " + addNewFriendInputData.getFriendUsername() + " not found.");
        }
        else {
            User newFriend = userDataAccessObject.get(addNewFriendInputData.getFriendUsername());
            user.addFriend(newFriend);

            userPoints.put(user, sumPoints(getInts(user)));
            userPoints.put(newFriend, sumPoints(getInts(newFriend)));
            final AddNewFriendOutputData addNewFriendOutputData = new AddNewFriendOutputData(newFriend,
                    (HashMap<User, Integer>) userPoints);
            addNewFriendPresenter.prepareSuccessView(addNewFriendOutputData);
        }
    }

    private int[] getInts(User user) {
        int[] points = new int[Constants.NUM_CATEGORIES];
        for (int i = 0; i < Constants.NUM_CATEGORIES; i++) {
            points[i] = guardianDataAccessObject
                    .getPointsForCategory(user.getWordFromCategory(Constants.CATEGORIES[i]));
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
