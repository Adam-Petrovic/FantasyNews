package use_case.to_friends;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import usecase.to_friends.*;

import static org.junit.jupiter.api.Assertions.*;

class FriendsInteractorTest {

    @Test
    void executeUserExistsTest() {
        // Arrange
        String username = "Alice";
        FriendsInputData inputData = new FriendsInputData(username);
        FriendsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Alice to the data access repository
        UserFactory factory = new CommonUserFactory();
        User user = factory.create(username, "password");
        userRepository.save(user);

        // Create a presenter to verify that it receives the correct user
        FriendsOutputBoundary presenter = new FriendsOutputBoundary() {
            @Override
            public void execute(FriendsOutputData outputData) {
                assertNotNull(outputData.getUser(), "User should not be null");
                assertEquals(username, outputData.getUser().getName(), "Usernames should match");
            }
        };

        // Act
        FriendsInputBoundary interactor = new FriendsInteractor(presenter, userRepository);
        interactor.execute(inputData);
    }

    @Test
    void executeUserDoesNotExistTest() {
        // Arrange
        String username = "Leo";
        FriendsInputData inputData = new FriendsInputData(username);
        FriendsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Do not add Leo to the repository to simulate a non-existent user

        // Create a presenter to handle the null user case
        FriendsOutputBoundary presenter = new FriendsOutputBoundary() {
            @Override
            public void execute(FriendsOutputData outputData) {
                // We expect that outputData.getUser() might be null
                assertNull(outputData.getUser(), "User should be null when not found");
            }
        };

        // Act
        FriendsInputBoundary interactor = new FriendsInteractor(presenter, userRepository);
        interactor.execute(inputData);
    }

    @Test
    void executePresenterCalledTest() {
        // Arrange
        String username = "Charlie";
        FriendsInputData inputData = new FriendsInputData(username);
        FriendsUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Charlie to the data access repository
        UserFactory factory = new CommonUserFactory();
        User user = factory.create(username, "password");
        userRepository.save(user);

        // Use a flag to check if presenter was called
        final boolean[] presenterCalled = {false};

        // Create a presenter to verify that it is called
        FriendsOutputBoundary presenter = new FriendsOutputBoundary() {
            @Override
            public void execute(FriendsOutputData outputData) {
                presenterCalled[0] = true;
            }
        };

        // Act
        FriendsInputBoundary interactor = new FriendsInteractor(presenter, userRepository);
        interactor.execute(inputData);

        // Assert
        assertTrue(presenterCalled[0], "Presenter should have been called");
    }
}

