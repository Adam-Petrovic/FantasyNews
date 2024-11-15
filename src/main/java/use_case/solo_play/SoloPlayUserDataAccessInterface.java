package use_case.solo_play;
import entity.User;

public interface SoloPlayUserDataAccessInterface {
    public User get(String username);
}
