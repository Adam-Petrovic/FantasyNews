package use_case.add_word;

public class AddWordInteractor implements AddWordInputBoundary{
    private final AddWordOutputBoundary addWordPresenter;

    public AddWordInteractor(AddWordOutputBoundary addWordPresenter) {this.addWordPresenter = addWordPresenter;}

    public void execute(AddWordInputData addWordInputData) {
        AddWordOutputData addWordOutputData = new AddWordOutputData(addWordInputData.getWord());
        addWordPresenter.addWord(addWordOutputData);
    }

}
