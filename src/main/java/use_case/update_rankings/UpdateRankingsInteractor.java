package use_case.update_rankings;

import data_access.Constants;
import entity.User;
import data_access.GuardianDataAccessObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateRankingsInteractor implements UpdateRankingsInputBoundary{
    private final GuardianDataAccessObject guardianDataAccessObject;
    private final UpdateRankingsDataAccessInterface userDataAccessObject;
    private final UpdateRankingsOutputBoundary updateRankingsPresenter;

    public UpdateRankingsInteractor(GuardianDataAccessObject guardianDataAccessObject,
                                    UpdateRankingsOutputBoundary updateRankingsPresenter,
                                    UpdateRankingsDataAccessInterface userDataAccessObject) {
        this.guardianDataAccessObject = guardianDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.updateRankingsPresenter = updateRankingsPresenter;
    }

    @Override
    public void execute(UpdateRankingsInputData updateRankingsInputData) {
        ArrayList<User> leagueUsers = userDataAccessObject.getUsers(updateRankingsInputData.getLeague().getUsers());
        HashMap<String, ArrayList<User>> rankings = new HashMap<>();
        HashMap<User, Integer> userPoints = new HashMap<>();

        for(int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            rankings.put(Constants.CATEGORIES[index], new ArrayList<User>());
            //ArrayList<Integer> categoryPoints = new ArrayList<>();
            for (User user: leagueUsers) {
                Integer categoryPoints = guardianDataAccessObject.getPointsForCategory(user.getWordFromCategory(Constants.CATEGORIES[index]));
                // update user points attribute with above, then use order to rank.
                rankings.get(Constants.CATEGORIES[index]).add(user);
            }
        }


        UpdateRankingsOutputData outputData = new UpdateRankingsOutputData(rankings);
        updateRankingsPresenter.execute(outputData);

    }
}
