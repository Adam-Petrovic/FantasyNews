package interface_adapter.draft_words;

import entity.User;

public class DraftState {
    private String username = "";
    private User user;
    private String leagueID;
    private String[] words;
    private String inputtedWord;

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLeagueID(String leagueID) {
        this.leagueID = leagueID;
    }
    public String getLeagueID() {
        return leagueID;
    }
    public String[] getWords() {
        return words;
    }
    public void setWords(String[] words) {
        this.words = words;
    }
    public void setInputtedWord (String inputtedWord) {
        this.inputtedWord = inputtedWord;
    }

    public String getInputtedWord() {
        return inputtedWord;
    }
}

