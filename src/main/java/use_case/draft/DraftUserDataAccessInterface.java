package use_case.draft;

import entity.User;

public interface DraftUserDataAccessInterface {
    public User get(String username);
    public void save(User user);
    public void setWord(String category, String word);
}
