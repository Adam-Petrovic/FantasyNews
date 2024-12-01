package interface_adapter.add_word;

import usecase.addWord.AddWordInputBoundary;
import usecase.addWord.AddWordInputData;

public class AddWordController {
    private final AddWordInputBoundary addWordInteractor;

    public AddWordController(AddWordInputBoundary addWordInteractor) {
        this.addWordInteractor = addWordInteractor;
    }

    public void execute(String username, String category, String newWord) {
        final AddWordInputData addWordInputData = new AddWordInputData(username, category, newWord);
        addWordInteractor.execute(addWordInputData);
    }

}
