package interface_adapter.to_draft;


import use_case.to_draft.ToDraftOutputBoundary;

public class ToDraftController {
    private final ToDraftOutputBoundary toDraftPresenter;

    public ToDraftController(ToDraftOutputBoundary toDraftpresenter) {
        this.toDraftPresenter = toDraftpresenter;
    }

//    public void execute (String username){
//        final ToDraftInputData toDraftInputData = new ToDraftInputData(username);
//        toDraftInteractor.execute(toDraftInputData);
//    }
    public void execute(){
        toDraftPresenter.execute();
    }
}
