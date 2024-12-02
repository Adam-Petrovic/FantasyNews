package interface_adapter.draft_words;

import interface_adapter.ViewModel;

/**
 * The Viewmodel for the draft use case.
 */
public class DraftViewModel extends ViewModel<DraftState> {
    public DraftViewModel() {
        super("draft");
        setState(new DraftState());
    }
}
