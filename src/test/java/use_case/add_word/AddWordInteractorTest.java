package use_case.add_word;

import data_access.InMemoryUserDataAccessObject;
import org.junit.jupiter.api.Test;

public class AddWordInteractorTest {

    @Test
    void successTest(){
        AddWordInputData inputData = new AddWordInputData("a", "Sports", "Soccer");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();


    }

}
