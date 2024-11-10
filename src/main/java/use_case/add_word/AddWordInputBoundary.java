package use_case.add_word;

public interface AddWordInputBoundary {

    void execute(AddWordInputData addWordInputData);

    public void refreshSoloPlayView();
}
