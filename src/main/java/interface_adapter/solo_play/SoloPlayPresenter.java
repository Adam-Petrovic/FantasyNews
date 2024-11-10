package interface_adapter.solo_play;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.solo_play.SoloPlayOutputBoundary;
import use_case.solo_play.SoloPlayOutputData;

public class SoloPlayPresenter implements SoloPlayOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SoloPlayViewModel soloPlayViewModel;

    public SoloPlayPresenter(ViewManagerModel viewManagerModel,
                             SoloPlayViewModel soloPlayViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.soloPlayViewModel = soloPlayViewModel;
    }


    @Override
    public void showSoloPlay(SoloPlayOutputData outputData) {
        final SoloPlayState soloPlayState = soloPlayViewModel.getState();
        soloPlayState.setUsername(outputData.getUsername());
        this.soloPlayViewModel.setState(soloPlayState);
        this.soloPlayViewModel.firePropertyChanged();

        this.viewManagerModel.setState(soloPlayViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
