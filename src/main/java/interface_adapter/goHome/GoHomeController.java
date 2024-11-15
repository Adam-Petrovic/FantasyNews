package interface_adapter.goHome;

import use_case.goHome.GoHomeOutputBoundary;

public class GoHomeController {
    private final GoHomeOutputBoundary goHomePresenter;

    public GoHomeController(GoHomeOutputBoundary goHomePresenter) {
        this.goHomePresenter = goHomePresenter;
    }

    public void execute(){
        this.goHomePresenter.execute();
    }
}
