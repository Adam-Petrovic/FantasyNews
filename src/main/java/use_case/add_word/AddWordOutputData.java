package use_case.add_word;

public class AddWordOutputData {
    private String word;
    private Integer[] selectedCell;

    public AddWordOutputData(String word, Integer[] selectedCell) {
        this.word = word;
        this.selectedCell = selectedCell;
    }

    public String getWord() {
        return word;
    }

    public Integer[] getSelectedCell() {return selectedCell;}
}
