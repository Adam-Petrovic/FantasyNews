package interface_adapter.add_word;

import use_case.add_word.AddWordInputBoundary;
import use_case.add_word.AddWordInputData;

public class AddWordController {
    private final AddWordInputBoundary addWordInteractor;

    public AddWordController(AddWordInputBoundary addWordInteractor) {
        this.addWordInteractor = addWordInteractor;
    }

    public void execute(String word) {
        final AddWordInputData addWordInputData = new AddWordInputData(word);
        addWordInteractor.execute(addWordInputData);
    }
}
