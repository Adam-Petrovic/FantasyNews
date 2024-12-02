package entitytests.CommonUserTests;

import data_access.Constants;
import entity.CommonUser;
import org.junit.Test;

public class CommonUserTest {


//    public () {
//        this.commonUserWithoutTerms = new CommonUser("Adam", "petroblicky");
//        String[] terms = {"Word 1", "Word 2", "Word 3", "Word 4", "Word 5"};
//        this.commonUserWithTerms = new CommonUser("Adam", "petroblicky", terms);
//    }

    @Test
    public void testCreateWordMap() {
        CommonUser commonUserWithoutTerms = new CommonUser("Adam", "petroblicky");

        String[] withoutTermsCommonUserWordMap = commonUserWithoutTerms.getWords();

        for(int i = 0; i < Constants.NUM_CATEGORIES - 1; i++){
            assert(withoutTermsCommonUserWordMap[i].equals(Constants.DEFAULT_WORDS[i]));
        }

        String[] terms = {"Word 1", "Word 2", "Word 3", "Word 4", "Word 5"};
        CommonUser commonUserWithTerms = new CommonUser("Adam", "petroblicky", terms);
        String[] withTermsCommonUserWordMap = commonUserWithTerms.getWords();

        for(int i = 0; i < terms.length; i++){
            assert(terms[i].equals(withTermsCommonUserWordMap[i]));
        }
    }

    @Test
    public void testSwapWords(){
        CommonUser commonUserWithoutTerms = new CommonUser("Adam", "petroblicky");
        commonUserWithoutTerms.swapWords(Constants.CATEGORIES[0], "New word");
        assert(commonUserWithoutTerms.getWords()[0].equals("New word"));

        String[] terms = {"Word 1", "Word 2", "Word 3", "Word 4", "Word 5"};
        CommonUser commonUserWithTerms = new CommonUser("Adam", "petroblicky", terms);
        commonUserWithTerms.swapWords(Constants.CATEGORIES[0], "New word");
        assert(commonUserWithTerms.getWords()[0].equals("New word"));
    }

    @Test
    public void testGetWords(){

        String[] terms = {"Word 1", "Word 2", "Word 3", "Word 4", "Word 5"};
        CommonUser commonUserWithTerms = new CommonUser("Adam", "petroblicky", terms);
        for(int i = 0; i < terms.length; i++){
            assert(commonUserWithTerms.getWords()[i].equals(terms[i]));
        }

    }

    @Test
    public void testSetWords(){
        CommonUser commonUserWithoutTerms = new CommonUser("Adam", "petroblicky");
        String[] newWords = {"New Word 1", "New Word 2", "New Word 3", "New Word 4", "New Word 5"};
        commonUserWithoutTerms.setWords(newWords);
        for(int i = 0; i < newWords.length; i++){
            assert(commonUserWithoutTerms.getWords()[i].equals(newWords[i]));
        }

        String[] terms = {"Word 1", "Word 2", "Word 3", "Word 4", "Word 5"};
        CommonUser commonUserWithTerms = new CommonUser("Adam", "petroblicky", terms);
        commonUserWithTerms.setWords(newWords);
        for(int i = 0; i < newWords.length; i++){
            assert(commonUserWithTerms.getWords()[i].equals(newWords[i]));
        }
    }

    @Test
    public void testSetLeaguePoints(){
        CommonUser commonUserWithoutTerms = new CommonUser("Adam", "petroblicky");
         commonUserWithoutTerms.setLeaguePoints(100);

        assert(commonUserWithoutTerms.getLeaguePoints() == 100);

        String[] terms = {"Word 1", "Word 2", "Word 3", "Word 4", "Word 5"};
        CommonUser commonUserWithTerms = new CommonUser("Adam", "petroblicky", terms);
        commonUserWithTerms.setLeaguePoints(100);

        assert(commonUserWithTerms.getLeaguePoints() == 100);
    }

    @Test
    public void testGetLeaguePoints(){
        CommonUser commonUserWithoutTerms = new CommonUser("Adam", "petroblicky");
        for(int i = 0; i < Constants.DEFAULT_WORDS.length - 1; i++){
            assert(commonUserWithoutTerms.getAllPoints()[i].equals(0));
        }

        String[] terms = {"Word 1", "Word 2", "Word 3", "Word 4", "Word 5"};

        CommonUser commonUserWithTerms = new CommonUser("Adam", "petroblicky", terms);
        for(int i = 0; i < terms.length; i++){
            assert(commonUserWithTerms.getAllPoints()[i].equals(0));
        }
    }

    @Test
    public void testGetAllPoints(){
        CommonUser commonUserWithoutTerms = new CommonUser("Adam", "petroblicky");
        assert(commonUserWithoutTerms.getLeaguePoints() == 0);

        String[] terms = {"Word 1", "Word 2", "Word 3", "Word 4", "Word 5"};

        CommonUser commonUserWithTerms = new CommonUser("Adam", "petroblicky", terms);
        assert(commonUserWithTerms.getLeaguePoints() == 0);
    }

}
