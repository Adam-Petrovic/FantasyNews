package use_case.solo_play;

public class SoloPlayInteractor implements SoloPlayInputBoundary{
    private final SoloPlayOutputBoundary soloPlayPresenter;

    public SoloPlayInteractor(SoloPlayOutputBoundary soloPlayPresenter){
        this.soloPlayPresenter = soloPlayPresenter;
    }


    @Override
    public void execute(SoloPlayInputData soloPlayInputData) {
        SoloPlayOutputData outputData = new SoloPlayOutputData(soloPlayInputData.getUsername());
        soloPlayPresenter.showSoloPlay(outputData);
    }
}
