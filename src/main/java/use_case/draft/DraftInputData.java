package use_case.draft;

public class DraftInputData {
    private final String username;

    public DraftInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
