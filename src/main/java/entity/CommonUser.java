package entity;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
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
