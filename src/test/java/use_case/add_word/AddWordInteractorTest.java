package use_case.add_word;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.add_word.AddWordPresenter;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;
import use_case.solo_play.SoloPlayUserDataAccessInterface;
import static org.junit.jupiter.api.Assertions.*;

public class AddWordInteractorTest {

    @Test
    void successTest(){
        AddWordInputData inputData = new AddWordInputData("a", "Sports", "Soccer");
        SoloPlayUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("a", "a");
        userRepository.save(user);

        AddWordOutputBoundary successPresenter = new AddWordOutputBoundary() {
            @Override
            public void execute(AddWordOutputData addWordOutputData) {
                assertEquals("Soccer", user.getWordFromCategory("Sports"));
            }
        };

        AddWordInputBoundary interactor = new AddWordInteractor(successPresenter, userRepository);
        interactor.execute(inputData);

    }

}
