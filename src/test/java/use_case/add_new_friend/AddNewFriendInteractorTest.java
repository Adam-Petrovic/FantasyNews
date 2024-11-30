package use_case.add_new_friend;

import data_access.InMemoryUserDataAccessObject;
import data_access.GuardianDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.to_friends.FriendsUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddNewFriendInteractorTest {

    @Test
    void successTest() {
        AddNewFriendInputData inputData = new AddNewFriendInputData("fio","jennifer");
        FriendsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        GuardianDataAccessObject guardianDataAccessObject = new GuardianDataAccessObject("guardianAPIToken.txt");

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

        AddNewFriendInputBoundary interactor = new AddNewFriendInteractor(successPresenter, userRepository, guardianDataAccessObject);
        interactor.execute(inputData);
    }

    @Test
    void failureFriendDoesNotExistTest() {
        AddNewFriendInputData inputData = new AddNewFriendInputData("leo", "jennifer");
        FriendsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        GuardianDataAccessObject guardianDataAccessObject = new GuardianDataAccessObject("guardianAPIToken.txt");

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("jennifer", "lala");
        userRepository.save(user);

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

        AddNewFriendInputBoundary interactor = new AddNewFriendInteractor(failurePresenter, userRepository, guardianDataAccessObject);
        interactor.execute(inputData);
    }
}