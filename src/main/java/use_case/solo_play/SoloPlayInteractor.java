package use_case.solo_play;

public class SoloPlayInteractor implements SoloPlayInputBoundary{
    private final SoloPlayOutputBoundary soloPlayPresenter;

    public SoloPlayInteractor(SoloPlayOutputBoundary soloPlayPresenter){
        this.soloPlayPresenter = soloPlayPresenter;
    }

    @Override
    public void execute(SoloPlayInputData soloPlayInputData) {
        soloPlayPresenter.showSoloPlay(soloPlayInputData.getUsername());

    }
}
