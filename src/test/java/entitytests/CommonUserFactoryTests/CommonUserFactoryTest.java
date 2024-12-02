package entitytests.CommonUserFactoryTests;

import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.Test;

public class CommonUserFactoryTest {

    @Test
    public void testCreateUserWithoutTerms(){
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User adamFactory = commonUserFactory.create("Adam", "petroblicky");
        CommonUser adamCommon = new CommonUser("Adam", "petroblicky");
        assert(adamCommon.getName().equals(adamFactory.getName()));
        assert(adamCommon.getPassword().equals(adamFactory.getPassword()));
    }

    @Test
    public void testCreateUserWithTerms(){
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        String[] terms = {"Word 1", "Word 2", "Word 3", "Word 4", "Word 5"};
        User adamFactory = commonUserFactory.create("Adam", "petroblicky", terms);
        CommonUser adamCommon = new CommonUser("Adam", "petroblicky", terms);
        assert(adamCommon.getName().equals(adamFactory.getName()));
        assert(adamCommon.getPassword().equals(adamFactory.getPassword()));
        assert(adamCommon.getWords()[0].equals(adamFactory.getWords()[0]));
        assert(adamCommon.getWords()[1].equals(adamFactory.getWords()[1]));
        assert(adamCommon.getWords()[2].equals(adamFactory.getWords()[2]));
        assert(adamCommon.getWords()[3].equals(adamFactory.getWords()[3]));
        assert(adamCommon.getWords()[4].equals(adamFactory.getWords()[4]));
    }
}
