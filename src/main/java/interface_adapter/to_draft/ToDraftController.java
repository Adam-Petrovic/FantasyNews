package interface_adapter.to_draft;


import use_case.to_draft.ToDraftInputBoundary;
import use_case.to_draft.ToDraftInputData;
import use_case.to_draft.ToDraftOutputBoundary;

public class ToDraftController {
    private final ToDraftInputBoundary toDraftInteractor;

    public ToDraftController(ToDraftInputBoundary toDraftInteractor) {
        this.toDraftInteractor = toDraftInteractor;
    }


    public void execute(String username, String leagueID){
        final ToDraftInputData toDraftInputData = new ToDraftInputData(username, leagueID);
        toDraftInteractor.execute(toDraftInputData);
    }
}
