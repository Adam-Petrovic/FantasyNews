package interfaceadapter.selectwordsuserstory.draft_words;

import interfaceadapter.ViewModel;

/**
 * The Viewmodel for the draft use case.
 */
public class DraftViewModel extends ViewModel<DraftState> {
    public DraftViewModel() {
        super("draft");
        setState(new DraftState());
    }
}
