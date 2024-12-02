package usecase.to_draft;

/**
 * Interface for to draft use case.
 */
public interface ToDraftInputBoundary {
    /**
     * Execute method for to draft.
     * @param toDraftInputData Input data contains the name and league.
     */
    void execute(ToDraftInputData toDraftInputData);
}
