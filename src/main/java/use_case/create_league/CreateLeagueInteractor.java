package use_case.create_league;

public class CreateLeagueInteractor implements CreateLeagueInputBoundary{
    private CreateLeagueOutputBoundary createLeaguePresenter;
    private CreateLeagueUserDataAccessInterface userDataAccessInterface;

    public CreateLeagueInteractor(CreateLeagueOutputBoundary createLeaguePresenter,
                                  CreateLeagueUserDataAccessInterface userDataAccessInterface) {
        this.createLeaguePresenter = createLeaguePresenter;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    public void execute(CreateLeagueInputData createLeagueInputData){
        String username = createLeagueInputData.getUsername();
        String leagueID = createLeagueInputData.getLeagueID();
        if(!userDataAccessInterface.inLeague(username)){
            userDataAccessInterface.setLeague(username, leagueID);
            CreateLeagueOutputData createLeagueOutputData = new CreateLeagueOutputData(username, leagueID);
            createLeaguePresenter.prepareSuccessView(createLeagueOutputData);
        }
        System.out.println("rip");
    }
}
