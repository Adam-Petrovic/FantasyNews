package interfaceadapter.selectwordsuserstory.add_word;

import usecase.selectwordsuserstory.addWord.AddWordInputBoundary;
import usecase.selectwordsuserstory.addWord.AddWordInputData;

/**
 * The controller for add word.
 */
public class AddWordController {
    private final AddWordInputBoundary addWordInteractor;

    public AddWordController(AddWordInputBoundary addWordInteractor) {
        this.addWordInteractor = addWordInteractor;
    }

    /**
     * Execute method for controller, takes in username, category, and new word.
     * @param username User's username.
     * @param category Category of changed word.
     * @param newWord New word.
     */
    public void execute(String username, String category, String newWord) {
        final AddWordInputData addWordInputData = new AddWordInputData(username, category, newWord);
        addWordInteractor.execute(addWordInputData);
    }

}
