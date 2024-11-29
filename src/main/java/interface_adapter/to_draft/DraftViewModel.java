package interface_adapter.to_draft;

import interface_adapter.ViewModel;

public class DraftViewModel extends ViewModel<DraftState>{
    public DraftViewModel(){
        super("draft");
        setState(new DraftState());
    }
}
