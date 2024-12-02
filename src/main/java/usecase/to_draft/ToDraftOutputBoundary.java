package usecase.to_draft;

/**
 * Interface for output boundary of to draft use case.
 */
public interface ToDraftOutputBoundary {
    /**
     * Execute method for to draft output boundary.
     * @param toDraftOutputData Output data for to draft.
     */
    void execute(ToDraftOutputData toDraftOutputData);
}
