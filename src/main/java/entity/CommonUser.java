package entity;
import java.lang.reflect.Array;
import java.util.ArrayList;

import data_access.Constants;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;

    private final String[] words = new String[Constants.NUM_CATEGORIES];

    private ArrayList<String> soloWordsDrafted;
    private int soloPoints;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.soloPoints = 0;
        this.soloWordsDrafted = new ArrayList<String>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String[] getWords() {
        return new String[0];
    }

    public ArrayList<String> getSoloWordsDrafted() {
        return soloWordsDrafted;
    }

    public void setSoloWordsDrafted(ArrayList<String> soloWordsDrafted) {
        this.soloWordsDrafted = soloWordsDrafted;
    }

    public int getSoloPoints() {
        return soloPoints;
    }

    public void setSoloPoints(int soloPoints) {
        this.soloPoints = soloPoints;
    }

}
