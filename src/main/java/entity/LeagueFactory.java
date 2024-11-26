package entity;

import java.util.ArrayList;

public class LeagueFactory {
    public League create(){
        return new League();
    }

    public League create(String leagueName, ArrayList<String> usernames){
        return new League(leagueName, usernames);
    }

}
