package use_case.solo_play;
import entity.Users.User;

public interface SoloPlayUserDataAccessInterface {
    public User get(String username);
    public void save(User user);
    public void setWord(String category, String word);
}
