package use_case.change_password;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChangePasswordInteractorTest {
    @Test
    void successTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("a", "b");
        ChangePasswordUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("a", "b");
        userRepository.save(user);

        ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData output) {
                assertEquals("b", user.getPassword());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userRepository, successPresenter, factory);
        interactor.execute(inputData);

    }
}
