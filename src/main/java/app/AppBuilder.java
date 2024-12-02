package app;

import java.awt.CardLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.*;
import entity.CommonUserFactory;
import entity.LeagueFactory;
import entity.UserFactory;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.navigation.loggedIn.LoggedInViewModel;
import interfaceadapter.friendsuserstory.to_friends.FriendsController;
import interfaceadapter.friendsuserstory.to_friends.FriendsPresenter;
import interfaceadapter.friendsuserstory.to_friends.FriendsViewModel;
import interfaceadapter.friendsuserstory.add_new_friend.AddNewFriendController;
import interfaceadapter.friendsuserstory.add_new_friend.AddNewFriendPresenter;
import interfaceadapter.selectwordsuserstory.add_word.AddWordController;
import interfaceadapter.selectwordsuserstory.add_word.AddWordPresenter;
import interfaceadapter.pointsuserstory.award_league_points.AwardLeaguePointsController;

import interfaceadapter.selectwordsuserstory.draft_words.DraftWordsController;
import interfaceadapter.selectwordsuserstory.draft_words.DraftWordsPresenter;
import interfaceadapter.selectwordsuserstory.to_draft.ToDraftController;
import interfaceadapter.selectwordsuserstory.to_draft.ToDraftPresenter;
import interfaceadapter.selectwordsuserstory.draft_words.DraftViewModel;

import interfaceadapter.navigation.go_home.GoHomeController;
import interfaceadapter.navigation.go_home.GoHomePresenter;
import interfaceadapter.navigation.login.LoginController;
import interfaceadapter.navigation.login.LoginPresenter;
import interfaceadapter.navigation.login.LoginViewModel;
import interfaceadapter.navigation.logout.LogoutController;
import interfaceadapter.navigation.logout.LogoutPresenter;
import interfaceadapter.navigation.signup.SignupController;
import interfaceadapter.navigation.signup.SignupPresenter;
import interfaceadapter.navigation.signup.SignupViewModel;
import interfaceadapter.navigation.solo_play.SoloPlayController;
import interfaceadapter.navigation.solo_play.SoloPlayPresenter;
import interfaceadapter.navigation.solo_play.SoloPlayViewModel;
import interfaceadapter.leagueuserstory.to_league.LeagueController;
import interfaceadapter.leagueuserstory.to_league.LeaguePresenter;
import interfaceadapter.leagueuserstory.to_league.LeagueViewModel;
import interfaceadapter.leagueuserstory.to_league_actions.ToLeagueActionsController;
import interfaceadapter.leagueuserstory.to_league_actions.ToLeagueActionsPresenter;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsController;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsViewModel;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsPresenter;
import interfaceadapter.leagueuserstory.to_league_actions.LeagueActionsViewModel;

import interfaceadapter.pointsuserstory.update_league_points.UpdateLeaguePointsController;

import interfaceadapter.leagueuserstory.update_leagues.UpdateLeaguesController;
import interfaceadapter.leagueuserstory.update_leagues.UpdateLeaguesPresenter;
import interfaceadapter.pointsuserstory.update_solo_points.UpdateSoloPlayPointsController;
import interfaceadapter.pointsuserstory.update_solo_points.UpdateSoloPlayPointsPresenter;
import interfaceadapter.rankingsuserstory.update_rankings.UpdateRankingsController;
import interfaceadapter.rankingsuserstory.update_rankings.UpdateRankingsPresenter;
import usecase.frienduserstory.to_friends.FriendsInputBoundary;
import usecase.frienduserstory.to_friends.FriendsInteractor;
import usecase.frienduserstory.to_friends.FriendsOutputBoundary;
import usecase.frienduserstory.add_new_friend.AddNewFriendInputBoundary;
import usecase.frienduserstory.add_new_friend.AddNewFriendInteractor;
import usecase.frienduserstory.add_new_friend.AddNewFriendOutputBoundary;
import usecase.selectwordsuserstory.addWord.AddWordInputBoundary;
import usecase.selectwordsuserstory.addWord.AddWordInteractor;
import usecase.selectwordsuserstory.addWord.AddWordOutputBoundary;

import usecase.selectwordsuserstory.draft_words.DraftWordsInputBoundary;
import usecase.selectwordsuserstory.draft_words.DraftWordsInteractor;
import usecase.selectwordsuserstory.draft_words.DraftWordsOutputBoundary;
import usecase.selectwordsuserstory.to_draft.ToDraftInputBoundary;
import usecase.selectwordsuserstory.to_draft.ToDraftInteractor;
import usecase.selectwordsuserstory.to_draft.ToDraftOutputBoundary;

import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputBoundary;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInteractor;

import usecase.navigation.goHome.GoHomeOutputBoundary;
import usecase.navigation.login.LoginInputBoundary;
import usecase.navigation.login.LoginInteractor;
import usecase.navigation.login.LoginOutputBoundary;
import usecase.navigation.logout.LogoutInputBoundary;
import usecase.navigation.logout.LogoutInteractor;
import usecase.navigation.logout.LogoutOutputBoundary;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsInputBoundary;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsInteractor;
import usecase.navigation.signup.SignupInputBoundary;
import usecase.navigation.signup.SignupInteractor;
import usecase.navigation.signup.SignupOutputBoundary;
import usecase.navigation.solo_play.SoloPlayInputBoundary;
import usecase.navigation.solo_play.SoloPlayInteractor;
import usecase.navigation.solo_play.SoloPlayOutputBoundary;
import usecase.leagueuserstory.to_league.LeagueOutputBoundary;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsInputBoundary;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsInteractor;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsOutputBoundary;
import usecase.rankingsuserstory.to_rankings.RankingsOutputBoundary;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueDataAccessObject;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueInputBoundary;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueInteractor;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesInputBoundary;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesInteractor;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesOutputBoundary;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsInputBoundary;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsInteractor;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsOutputBoundary;
import usecase.pointsuserstory.update_solo_points.UpdateSoloPlayPointsDataAccessInterface;
import usecase.pointsuserstory.update_solo_points.UpdateSoloPlayPointsInputBoundary;
import usecase.pointsuserstory.update_solo_points.UpdatePointsInteractor;
import usecase.pointsuserstory.update_solo_points.UpdatePointsOutputBoundary;


import view.*;
import view.accountviews.LoginView;
import view.accountviews.SignupView;
import view.actionviews.FriendsView;
import view.actionviews.LoggedInView;
import view.actionviews.RankingsView;
import view.actionviews.SoloPlayView;
import view.leagueviews.DraftView;
import view.leagueviews.LeagueActionsView;
import view.leagueviews.LeagueView;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final LeagueFactory leagueFactory = new LeagueFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    //private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final PantryUserDataAccessObject userDataAccessObject = new PantryUserDataAccessObject(userFactory);
    private final PantryLeagueDataAccessObject leagueDataAccessObject = new PantryLeagueDataAccessObject();
    //private final PantryUserDataAccessObject userDataAccessObject = new PantryUserDataAccessObject(userFactory);
    //private final PantryLeagueDataAccessObject leagueDataAccessObject = new PantryLeagueDataAccessObject(leagueFactory);
    //uncomment the line above in order to use the Pantry API userDAO :)
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LeagueViewModel leagueViewModel;
    private RankingsViewModel rankingsViewModel;
    private FriendsViewModel friendsViewModel;
    private DraftViewModel draftViewModel;
    private LeagueActionsViewModel leagueActionsViewModel;

    private LoggedInView loggedInView;
    private LoginView loginView;
    private SoloPlayViewModel soloPlayViewModel;
    private SoloPlayView soloPlayView;
    private LeagueView leagueView;
    private RankingsView rankingsView;
    private FriendsView friendsView;
    private DraftView draftView;
    private LeagueActionsView leagueActionsView;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addUpdateLeaguesUseCase() {
        final UpdateLeaguesOutputBoundary updateLeaguesPresenter = new UpdateLeaguesPresenter(viewManagerModel, leagueViewModel);
        final UpdateLeaguesInputBoundary updateLeaguesInteractor = new UpdateLeaguesInteractor(updateLeaguesPresenter,
                userDataAccessObject, leagueDataAccessObject);
        final UpdateLeaguesController controller = new UpdateLeaguesController(updateLeaguesInteractor);
        loggedInView.setUpdateLeaguesController(controller);
        leagueView.setUpdateLeaguesController(controller);
        return this;
    }

    public AppBuilder addDraftView(){
        draftViewModel = new DraftViewModel();
        draftView = new DraftView(draftViewModel);
        cardPanel.add(draftView, "draft");
        return this;
    }

    public AppBuilder addDraftUseCase(){
        final DraftWordsOutputBoundary presentr = new DraftWordsPresenter(viewManagerModel, draftViewModel);
        final DraftWordsInputBoundary draftWordsInteractor = new DraftWordsInteractor(presentr, leagueDataAccessObject);
        final DraftWordsController draftWordsController = new DraftWordsController(draftWordsInteractor);
        draftView.setDraftWordsController(draftWordsController);
        return this;
    }

    public AppBuilder addToDraftUseCase(){
        final ToDraftOutputBoundary draftPresenter = new ToDraftPresenter(viewManagerModel, draftViewModel);
        final ToDraftInputBoundary draftInteractor = new ToDraftInteractor(draftPresenter, leagueDataAccessObject);
        final ToDraftController controller = new ToDraftController(draftInteractor);
        leagueActionsView.setDraftController(controller);
        return this;
    }
    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, Constants.SIGN_UP_VIEW_NAME);
        return this;
    }

    public AppBuilder addLeagueActionsView(){
        leagueActionsViewModel = new LeagueActionsViewModel();
        leagueActionsView = new LeagueActionsView(leagueActionsViewModel);
        cardPanel.add(leagueActionsView, Constants.LEAGUE_ACTIONS_VIEW_NAME);
        return this;
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSoloPlayView() {
        soloPlayViewModel = new SoloPlayViewModel();
        soloPlayView = new SoloPlayView(soloPlayViewModel);
        cardPanel.add(soloPlayView, Constants.SOLO_PLAY_VIEW_NAME);
        return this;
    }

    public AppBuilder addAddWordUseCase() {
        final AddWordOutputBoundary addWordPresenter = new AddWordPresenter(viewManagerModel, soloPlayViewModel);
        final AddWordInputBoundary addWordInteractor = new AddWordInteractor(addWordPresenter, userDataAccessObject);
        final AddWordController controller = new AddWordController(addWordInteractor);
        soloPlayView.setAddWordController(controller);
        return this;
    }
    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */

    public AppBuilder addGoHomeUseCase(){
        final GoHomeOutputBoundary goHomePresenter = new GoHomePresenter(viewManagerModel, loggedInViewModel);
        final GoHomeController goHomeController = new GoHomeController(goHomePresenter);
        soloPlayView.setGoHomeController(goHomeController);
        draftView.setGoHomeController(goHomeController);
        friendsView.setGoHomeController(goHomeController);
        leagueView.setGoHomeController(goHomeController);
        leagueActionsView.setGoHomeController(goHomeController);
        rankingsView.setGoHomeController(goHomeController);
        return this;
    }

    //add friends view
    public AppBuilder addFriendsView(){
        friendsViewModel = new FriendsViewModel();
        friendsView = new FriendsView(friendsViewModel);
        cardPanel.add(friendsView, Constants.ADD_FRIENDS_VIEW_NAME);
        return this;
    }

    //add friends use case
    public AppBuilder addAddFriendsUseCase(){
        final FriendsOutputBoundary addFriendsPresenter = new FriendsPresenter(viewManagerModel, friendsViewModel);
        final FriendsInputBoundary addFriendsInteractor = new FriendsInteractor(addFriendsPresenter, userDataAccessObject);
        final FriendsController friendsController = new FriendsController(addFriendsInteractor);
        loggedInView.setAddFriendsController(friendsController);
        return this;
    }

    public AppBuilder addToLeagueActionsUseCase(){
        final ToLeagueActionsOutputBoundary toLeagueActionsPresenter = new ToLeagueActionsPresenter
                (leagueActionsViewModel, leagueViewModel, viewManagerModel);
        final ToLeagueActionsInputBoundary toLeagueActionsInteractor = new ToLeagueActionsInteractor
                (toLeagueActionsPresenter, userDataAccessObject, leagueDataAccessObject);
        final ToLeagueActionsController toLeagueActionsController = new ToLeagueActionsController(toLeagueActionsInteractor);
        leagueView.setToLeagueActionsController(toLeagueActionsController);
        return this;
    }

    public AppBuilder addAddNewFriendUseCase() {
        final AddNewFriendOutputBoundary addNewFriendPresenter = new AddNewFriendPresenter(viewManagerModel, friendsViewModel);
        try {
            final GuardianDataAccessObject guardianDataAccessObject = makeGuardianDataAccessObject();
            final AddNewFriendInputBoundary addNewFriendInteractor = new AddNewFriendInteractor(addNewFriendPresenter, userDataAccessObject, guardianDataAccessObject);
            final AddNewFriendController controller = new AddNewFriendController(addNewFriendInteractor);
            friendsView.setAddNewFriendController(controller);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public AppBuilder addToLeagueView(){
        leagueViewModel = new LeagueViewModel();
        leagueView = new LeagueView(leagueViewModel);
        cardPanel.add(leagueView, Constants.LEAGUE_VIEW_NAME);
        return this;
    }

    public AppBuilder addUpdatePointsForLeagueUseCase(){
        try{
            final UpdatePointsForLeagueDataAccessObject updatePointsForLeagueDataAccessObject = makeGuardianDataAccessObject();
            final UpdatePointsForLeagueInputBoundary updateLeaguePointsInteractor = new UpdatePointsForLeagueInteractor(updatePointsForLeagueDataAccessObject );
            final UpdateLeaguePointsController controller = new UpdateLeaguePointsController(updateLeaguePointsInteractor);
            leagueActionsView.setUpdatePointsForLeagueController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public AppBuilder addUpdateLeaguePointsUseCase(){
        try {

            final AwardLeaguePointsInputBoundary awardLeaguePointsInputBoundary = new AwardLeaguePointsInteractor(leagueDataAccessObject);
            final RoundLeaguePointsInputBoundary roundLeaguePointsInteractor = new RoundLeaguePointsInteractor(leagueDataAccessObject);

            final AwardLeaguePointsController controller = new AwardLeaguePointsController(awardLeaguePointsInputBoundary, roundLeaguePointsInteractor);
            leagueView.setAwardLeaguePointsController(controller);

        } catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public AppBuilder addToLeagueUseCase(){
        final LeagueOutputBoundary leaguePresenter = new LeaguePresenter(viewManagerModel, leagueViewModel);
        final LeagueController leagueController = new LeagueController(leaguePresenter);
        loggedInView.setToLeagueController(leagueController);
        return this;
    }


    public AppBuilder addToRankingsView(){
        rankingsViewModel = new RankingsViewModel();
        rankingsView = new RankingsView(rankingsViewModel);
        cardPanel.add(rankingsView, Constants.RANKINGS_VIEW_NAME);
        return this;
    }

    public AppBuilder addToRankingsUseCase(){
        final RankingsOutputBoundary rankingsPresenter = new RankingsPresenter(viewManagerModel, rankingsViewModel);
        final RankingsController rankingsController = new RankingsController(rankingsPresenter);
        final UpdateRankingsOutputBoundary updateRankingsPresenter = new UpdateRankingsPresenter(viewManagerModel,
                rankingsViewModel);

        try {
            final GuardianDataAccessObject guardianDataAccessObject = makeGuardianDataAccessObject();
            final UpdateRankingsInputBoundary updateRankingsInteractor = new UpdateRankingsInteractor(guardianDataAccessObject, updateRankingsPresenter, leagueDataAccessObject);
            final UpdateRankingsController updateRankingsController = new UpdateRankingsController(updateRankingsInteractor);
            rankingsView.setUpdateRankingsController(updateRankingsController);
            loggedInView.setToRankingsController(rankingsController);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public AppBuilder addSoloPlayUseCase() {
        final SoloPlayOutputBoundary soloPlayPresenter = new SoloPlayPresenter(viewManagerModel,soloPlayViewModel);
        final SoloPlayInputBoundary soloPlayInteractor = new SoloPlayInteractor(soloPlayPresenter, userDataAccessObject);
        final SoloPlayController soloPlayController = new SoloPlayController(soloPlayInteractor);

        final UpdatePointsOutputBoundary updatePointsPresenter = new UpdateSoloPlayPointsPresenter(viewManagerModel,
                                                                                           soloPlayViewModel);
        try {
            final UpdateSoloPlayPointsDataAccessInterface updatePointsDataAccessInterface = makeGuardianDataAccessObject();
            final UpdateSoloPlayPointsInputBoundary updatePointsInteractor = new UpdatePointsInteractor(updatePointsDataAccessInterface, updatePointsPresenter);
            final UpdateSoloPlayPointsController updatePointsController = new UpdateSoloPlayPointsController(updatePointsInteractor);
            loggedInView.setSoloPlayController(soloPlayController);
            soloPlayView.setUpdatePointsConroller(updatePointsController);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    private GuardianDataAccessObject makeGuardianDataAccessObject() throws FileNotFoundException {
        try {
            String apiKey = new Scanner(new File("src/main/resources/apikeys/guardianAPIToken.txt")).nextLine();
            return new GuardianDataAccessObject(apiKey);
        }

        catch (FileNotFoundException e) {
            System.out.println("Need to find API token, and call the file GuardianAPIToken");
            return null;
        }
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, Constants.LOG_IN_VIEW_NAME);
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, Constants.LOGGED_IN_VIEW_NAME);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("FantasyNews");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(Constants.SIGN_UP_VIEW_NAME);
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
