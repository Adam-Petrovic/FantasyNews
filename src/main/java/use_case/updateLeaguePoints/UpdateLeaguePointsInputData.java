package use_case.updateLeaguePoints;

import entity.User;

import java.util.ArrayList;

public class UpdateLeaguePointsInputData {
    private final ArrayList<User> users;
    public UpdateLeaguePointsInputData(ArrayList<User> users){
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }


}
