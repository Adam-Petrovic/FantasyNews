package entity;

import java.util.ArrayList;

public class LeagueFactory {
    public League create(){
        return new League();
    }

    public League create(String leagueName, ArrayList<User> users){
        return new League(leagueName, users);
    }

}
