package interface_adapter.to_league;

import entity.League;

import java.util.ArrayList;

public class LeagueState {
    private ArrayList<String> leagueIDs;
    private ArrayList<League> leagues;
    private String username;

    public ArrayList<String> getLeagueIDs() {
        return leagueIDs;
    }
    public void setLeagueIDs(ArrayList<String> leagueID) {
        this.leagueIDs = leagueID;
    }
    public ArrayList<League> getLeagues(){
        return leagues;
    }
    public void setLeagues(ArrayList<League> leagues) {
        this.leagues = leagues;
    }

    public void addLeague(League league){
        if(this.leagues != null){
            this.leagues.add(league);
        }
        else{
            this.leagues = new ArrayList<>();
            this.leagues.add(league);
        }
    }

    public void addLeagueID(String leagueID){
        if(this.leagueIDs != null){
            this.leagueIDs.add(leagueID);
        }
        else{
            this.leagueIDs = new ArrayList<>();
            this.leagueIDs.add(leagueID);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
