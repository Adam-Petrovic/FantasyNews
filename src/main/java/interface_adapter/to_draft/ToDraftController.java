package interface_adapter.to_draft;


import usecase.to_draft.ToDraftInputBoundary;
import usecase.to_draft.ToDraftInputData;

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
