package use_case.add_new_friend;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.add_friends.AddFriendsUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddNewFriendInteractorTest {

    @Test
    void successTest() {
        AddNewFriendInputData inputData = new AddNewFriendInputData("fio","jennifer");
        AddFriendsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("jennifer", "lala");
        User friend = factory.create("fio", "hehe");
        userRepository.save(user);
        userRepository.save(friend);

        AddNewFriendOutputBoundary successPresenter = new AddNewFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddNewFriendOutputData data) {
                assertEquals(userRepository.get("fio"),data.getFriend());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        AddNewFriendInputBoundary interactor = new AddNewFriendInteractor(successPresenter, userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureFriendDoesNotExistTest() {
        AddNewFriendInputData inputData = new AddNewFriendInputData("leo", "jennifer");
        AddFriendsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        AddNewFriendOutputBoundary failurePresenter = new AddNewFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddNewFriendOutputData data) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User leo not found.", error);
            }
        };

        AddNewFriendInputBoundary interactor = new AddNewFriendInteractor(failurePresenter, userRepository);
        interactor.execute(inputData);
    }
}