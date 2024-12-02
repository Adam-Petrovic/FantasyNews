package interface_adapter.go_home;

import usecase.goHome.GoHomeOutputBoundary;

public class GoHomeController {
    private final GoHomeOutputBoundary goHomePresenter;

    public GoHomeController(GoHomeOutputBoundary goHomePresenter) {
        this.goHomePresenter = goHomePresenter;
    }

    /**
     * Tells the presenter to execute the function.
     */
    public void execute() {
        this.goHomePresenter.execute();
    }
}
