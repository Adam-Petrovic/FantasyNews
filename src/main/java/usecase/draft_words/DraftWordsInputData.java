package usecase.draft_words;

public class DraftWordsInputData {
    private final String username;
    private final Integer categoryNum;
    private final String newWord;
    private final String leagueID;

    public DraftWordsInputData(String username, Integer categoryNum, String newWord, String leagueID) {
        this.username = username;
        this.categoryNum = categoryNum;
        this.newWord = newWord;
        this.leagueID = leagueID;
    }

    public String getUsername() {
        return username;
    }

    public Integer getCategoryNum() {
        return categoryNum;
    }

    public String getNewWord() {
        return newWord;
    }

    public String getLeagueID() {return leagueID;}
}
