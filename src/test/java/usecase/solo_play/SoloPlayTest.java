package usecase.solo_play;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;


public class SoloPlayTest {

    @Test
    public void testSoloPlayInputData() {
        SoloPlayInputData soloPlayInputData = new SoloPlayInputData("Adam5");
        assert(soloPlayInputData.getUsername().equals("Adam5"));
    }

    @Test
    public void testSoloPlayOutputData() {
        SoloPlayOutputData soloPlayOutputData = new SoloPlayOutputData(new CommonUser("Adam", ""));
        assert(soloPlayOutputData.getUser().getName().equals("Adam"));
    }

    @Test
    public void testSoloPlayEndToEnd() {
        SoloPlayInputData soloPlayInputData = new SoloPlayInputData("Adam");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Adam", "password");
        userRepository.save(user);
        userRepository.setCurrentUsername("Adam");

        SoloPlayOutputBoundary soloPlayPresenter = new SoloPlayOutputBoundary() {

            @Override
            public void showSoloPlay(SoloPlayOutputData soloPlayOutputData) {
                assert(soloPlayOutputData.getUser().getName().equals("Adam"));
            }
        };

        SoloPlayInputBoundary interactor = new SoloPlayInteractor(soloPlayPresenter, userRepository);
        interactor.execute(soloPlayInputData);
    }


}
