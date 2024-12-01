package interface_adapter.draft_words;

import interface_adapter.ViewModel;

public class DraftViewModel extends ViewModel<DraftState>{
    public DraftViewModel(){
        super("draft");
        setState(new DraftState());
    }
}
