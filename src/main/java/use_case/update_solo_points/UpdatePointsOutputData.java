package use_case.update_solo_points;

import entity.User;

public class UpdatePointsOutputData {
    private int[] points;

    public UpdatePointsOutputData(int[] points) {
        this.points = points;
    }


    public int[] getPoints() {
        return points;
    }
}
