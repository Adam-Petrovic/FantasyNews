package interfaceadapter.selectwordsuserstory.draft_words;

import usecase.selectwordsuserstory.draft_words.DraftWordsInputBoundary;
import usecase.selectwordsuserstory.draft_words.DraftWordsInputData;

/**
 * Controller for Draft Word use case.
 */
public class DraftWordsController {
    private final DraftWordsInputBoundary draftWordsInteractor;

    public DraftWordsController(DraftWordsInputBoundary draftWordsInteractor) {
        this.draftWordsInteractor = draftWordsInteractor;
    }

    /**
     * Execute method for draft word use case.
     * @param username Username.
     * @param num Category number.
     * @param newWord New word.
     * @param leagueID League ID.
     */
    public void execute(String username, Integer num, String newWord, String leagueID) {
        final DraftWordsInputData draftWordsInputData = new DraftWordsInputData(username, num, newWord, leagueID);
        draftWordsInteractor.execute(draftWordsInputData);
    }
}
