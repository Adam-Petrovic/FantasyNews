package interface_adapter.solo_play;

import java.util.ArrayList;
import java.util.Arrays;

public class SoloPlayState {
    private String username = "";
    private String[][] WORDS = {{"USR1", "Volleyball", "Gattaca", "Green Party", "Cevapi"},
                                {"USR2", "Equestrian", "Star Wars", "House Party!", "Karađorđeva šnicla"}};


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[][] getWORDS() {
        return WORDS;
    }

    public void setWORDS(String[][] WORDS) {
        this.WORDS = WORDS;
    }
}
