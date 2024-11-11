package interface_adapter.solo_play;

import entity.User;


public class SoloPlayState {
    private User user;
    private String inputtedWord;

    public String getUsername() {
        return user.getName();
    }

    public String[] getWORDS() {
        return user.getWords();
    }

    public String getWordFromCategory (String category) {
        return user.getWordFromCategory(category);
    }

    public void setInputtedWord (String inputtedWord) {
        this.inputtedWord = inputtedWord;
    }

    public void swapWord(String category, String word){
        user.swapWords(category, word);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getInputtedWord(){
        return this.inputtedWord;
    }
}
