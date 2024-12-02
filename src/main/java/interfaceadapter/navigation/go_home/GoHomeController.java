package interfaceadapter.navigation.go_home;

import usecase.navigation.goHome.GoHomeOutputBoundary;

/**
 * Controller for go home button.
 */
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
