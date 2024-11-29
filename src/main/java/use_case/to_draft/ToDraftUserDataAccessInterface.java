package use_case.to_draft;

import entity.User;

public interface ToDraftUserDataAccessInterface {
    public User get(String username);
    public void save(User user);
    public void setWord(String category, String word);
}
