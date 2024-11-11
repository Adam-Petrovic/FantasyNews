package use_case.add_word;

public class AddWordInputData {

    private final String word;
    private final Integer[] selectedCell;

    public AddWordInputData(String word, Integer[] selectedCell) {
        this.word = word;
        this.selectedCell = selectedCell;
    }

    String getWord() {
        return word;
    }

    Integer[] getSelectedCell() {return selectedCell;}
}
