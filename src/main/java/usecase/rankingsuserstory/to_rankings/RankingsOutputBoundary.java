package usecase.rankingsuserstory.to_rankings;

/**
 * The RankingsOutputBoundary interface defines the contract for the output
 * boundary of the "to rankings" use case. Implementing classes are responsible
 * for processing the output data and updating the presentation layer accordingly.
 */
public interface RankingsOutputBoundary {
    /**
     * Executes the process of handling the output data from the "to rankings"
     * use case. Implementations should update the necessary view models or
     * notify the presentation layer to reflect changes.
     */
    void execute();
}
