package usecase.navigation.solo_play;

/**
 * Input boundary for solo play use case.
 */
public interface SoloPlayInputBoundary {
    /**
     * The execute method that every interactor needs.
     * @param soloPlayInputData the data
     */
    void execute(SoloPlayInputData soloPlayInputData);
}
