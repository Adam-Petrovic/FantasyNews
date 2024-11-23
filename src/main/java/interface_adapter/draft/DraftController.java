package interface_adapter.draft;

import use_case.draft.DraftInputBoundary;
import use_case.draft.DraftInputData;

public class DraftController {
    private final DraftInputBoundary draftInteractor;

    public DraftController(DraftInputBoundary draftInteractor) {
        this.draftInteractor = draftInteractor;
    }

    public void execute (String username){
        final DraftInputData draftInputData = new DraftInputData(username);
        draftInteractor.execute(draftInputData);
    }
}
