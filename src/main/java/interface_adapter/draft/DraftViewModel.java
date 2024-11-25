package interface_adapter.draft;

import data_access.Constants;
import interface_adapter.ViewModel;

public class DraftViewModel extends ViewModel<DraftState>{
    public DraftViewModel(){
        super("draft");
        setState(new DraftState());
    }
}
