package entity;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class League {
    private HashMap<User, LeagueData> Players;

    public League() {
        Players = new HashMap<>();
    }

    public League(HashMap<User, LeagueData> Players) {
        this.Players = Players;
    }

    public League(List<User> users, List<LeagueData> leagueData){
        Players = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            Players.put(users.get(i), leagueData.get(i));
        }
    }
}
