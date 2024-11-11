package use_case.solo_play;

public class SoloPlayInputData {

    private final String username;
    private final String password;

    public SoloPlayInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    public String getPassword() { return password; }
}
