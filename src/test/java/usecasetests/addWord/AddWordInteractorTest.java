package usecasetests.addWord;

import dataaccess.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import usecase.selectwordsuserstory.addWord.*;
import usecase.navigation.solo_play.SoloPlayUserDataAccessInterface;
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
                String[] newWords = {"Soccer", "Default 2", "Default 3", "Default 4", "Default 5"};
                assertArrayEquals(addWordOutputData.getWords(), newWords);
                assertEquals("Soccer", user.getWordFromCategory("Sports"));
            }
        };

        AddWordInputBoundary interactor = new AddWordInteractor(successPresenter, userRepository);
        interactor.execute(inputData);

    }

}