package interface_adapter.draft_words;

import use_case.add_word.AddWordInputBoundary;
import use_case.draft_words.DraftWordsInputBoundary;
import use_case.draft_words.DraftWordsInputData;

public class DraftWordsController {
    private final DraftWordsInputBoundary draftWordsInteractor;

    public DraftWordsController(DraftWordsInputBoundary draftWordsInteractor) {
        this.draftWordsInteractor = draftWordsInteractor;
    }

    public void execute(String username, Integer categoryNum, String newWord, String leagueID){
        final DraftWordsInputData draftWordsInputData = new DraftWordsInputData(username, categoryNum, newWord, leagueID);
        draftWordsInteractor.execute(draftWordsInputData);
    }
}
