package use_case.to_draft;

public class ToDraftInputData {
    private final String username;

    public ToDraftInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
