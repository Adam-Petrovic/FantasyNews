package usecase.navigation.solo_play;

/**
 * Output boundary for solo play use case.
 */
public interface SoloPlayOutputBoundary {
    /**
     * The method which shows the view.
     * @param soloPlayOutputData the data to be presented
     */
    void showSoloPlay(SoloPlayOutputData soloPlayOutputData);
}
