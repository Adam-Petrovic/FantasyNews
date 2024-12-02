package usecase.add_new_friend;

import data_access.InMemoryUserDataAccessObject;
import data_access.GuardianDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.frienduserstory.add_new_friend.*;
import usecase.frienduserstory.to_friends.FriendsUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddNewFriendInteractorTest {

    private FriendsUserDataAccessInterface userRepository;
    private GuardianDataAccessObject guardianDataAccessObject;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserDataAccessObject();
        guardianDataAccessObject = mock(GuardianDataAccessObject.class);
    }

    @Test
    void successTest() {
        AddNewFriendInputData inputData = new AddNewFriendInputData("fio","jennifer");

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("jennifer", "lala");
        User friend = factory.create("fio", "hehe");
        userRepository.save(user);
        userRepository.save(friend);

        // Mock the method to return a fixed point value
        when(guardianDataAccessObject.getPointsForCategory(anyString())).thenReturn(10);

        AddNewFriendOutputBoundary successPresenter = new AddNewFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddNewFriendOutputData data) {
                assertEquals(userRepository.get("fio"), data.getFriend());
                assertEquals(2, data.getUserPoints().size());
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

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("jennifer", "lala");
        userRepository.save(user);

        guardianDataAccessObject = mock(GuardianDataAccessObject.class);

        AddNewFriendOutputBoundary failurePresenter = new AddNewFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddNewFriendOutputData data) {
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

    @Test
    void failureAddSelfAsFriendTest() {
        // Arrange
        String username = "jennifer";
        AddNewFriendInputData inputData = new AddNewFriendInputData(username, username);

        // Create a mock user repository and guardian data access object
        FriendsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        GuardianDataAccessObject guardianDataAccessObject = mock(GuardianDataAccessObject.class);

        // Create a user and save it to the repository
        UserFactory factory = new CommonUserFactory();
        User user = factory.create(username, "password");
        userRepository.save(user);

        // Mock the guardian data access object to return a fixed value
        when(guardianDataAccessObject.getPointsForCategory(anyString())).thenReturn(10);

        // Create a presenter that checks for the expected failure message
        AddNewFriendOutputBoundary failurePresenter = new AddNewFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddNewFriendOutputData data) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("You cannot add yourself as a friend :(", error);
            }
        };

        // Act
        AddNewFriendInputBoundary interactor = new AddNewFriendInteractor(failurePresenter, userRepository, guardianDataAccessObject);
        interactor.execute(inputData);

        // Assert
        // The assertions are made in the presenter
    }
}
