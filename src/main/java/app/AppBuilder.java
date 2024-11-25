package app;

import java.awt.CardLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.Constants;
import data_access.GuardianDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import data_access.PantryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsPresenter;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.add_new_friend.AddNewFriendController;
import interface_adapter.add_new_friend.AddNewFriendPresenter;
import interface_adapter.add_word.AddWordController;
import interface_adapter.add_word.AddWordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_league.CreateLeagueController;
import interface_adapter.create_league.CreateLeaguePresenter;
import interface_adapter.draft.DraftController;
import interface_adapter.draft.DraftPresenter;
import interface_adapter.draft.DraftViewModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.solo_play.SoloPlayController;
import interface_adapter.solo_play.SoloPlayPresenter;
import interface_adapter.solo_play.SoloPlayViewModel;
import interface_adapter.to_league.LeagueController;
import interface_adapter.to_league.LeaguePresenter;
import interface_adapter.to_league.LeagueViewModel;
import interface_adapter.to_rankings.RankingsController;
import interface_adapter.to_rankings.RankingsViewModel;
import interface_adapter.to_rankings.RankingsPresenter;
import interface_adapter.to_rankings.RankingsViewModel;
import interface_adapter.update_points.UpdatePointsController;
import interface_adapter.update_points.UpdatePointsPresenter;
import use_case.add_friends.AddFriendsInputBoundary;
import use_case.add_friends.AddFriendsInteractor;
import use_case.add_friends.AddFriendsOutputBoundary;
import use_case.add_new_friend.AddNewFriendInputBoundary;
import use_case.add_new_friend.AddNewFriendInteractor;
import use_case.add_new_friend.AddNewFriendOutputBoundary;
import use_case.add_word.AddWordInputBoundary;
import use_case.add_word.AddWordInteractor;
import use_case.add_word.AddWordOutputBoundary;
import use_case.create_league.CreateLeagueInputBoundary;
import use_case.create_league.CreateLeagueInteractor;
import use_case.create_league.CreateLeagueOutputBoundary;
import use_case.draft.DraftInputBoundary;
import use_case.draft.DraftInteractor;
import use_case.draft.DraftOutputBoundary;
import use_case.goHome.GoHomeOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.solo_play.SoloPlayInputBoundary;
import use_case.solo_play.SoloPlayInteractor;
import use_case.solo_play.SoloPlayOutputBoundary;
import use_case.to_league.LeagueOutputBoundary;
import use_case.to_rankings.RankingsOutputBoundary;
import use_case.update_solo_points.UpdatePointsInputBoundary;
import use_case.update_solo_points.UpdatePointsInteractor;
import use_case.update_solo_points.UpdatePointsOutputBoundary;

//import entity.*;
//import interface_adapter.*;
//import use_case.*;
import view.*;

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
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    //private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final PantryUserDataAccessObject userDataAccessObject = new PantryUserDataAccessObject(userFactory);
    //uncomment the line above in order to use the Pantry API userDAO :)
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LeagueViewModel leagueViewModel;
    private RankingsViewModel rankingsViewModel;
    private AddFriendsViewModel addFriendsViewModel;
    private DraftViewModel draftViewModel;

    private LoggedInView loggedInView;
    private LoginView loginView;
    private SoloPlayViewModel soloPlayViewModel;
    private SoloPlayView soloPlayView;
    private LeagueView leagueView;
    private RankingsView rankingsView;
    private AddFriendsView addFriendsView;
    private DraftView draftView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addCreateLeagueUseCase(){
        final CreateLeagueOutputBoundary createLeaguePresenter = new CreateLeaguePresenter(viewManagerModel, leagueViewModel);
        final CreateLeagueInputBoundary createLeagueInteractor = new CreateLeagueInteractor(createLeaguePresenter, userDataAccessObject);
        final CreateLeagueController controller = new CreateLeagueController(createLeagueInteractor);
        leagueView.setCreateLeagueController(controller);
        return this;
    }

    public AppBuilder addDraftView(){
        draftViewModel = new DraftViewModel();
        draftView = new DraftView(draftViewModel);
        cardPanel.add(draftView, "draft");
        return this;
    }


    public AppBuilder addDraftUseCase(){
        final DraftOutputBoundary draftPresenter = new DraftPresenter(viewManagerModel, draftViewModel);
        final DraftInputBoundary draftInteractor = new DraftInteractor(draftPresenter, userDataAccessObject);
        final DraftController controller = new DraftController(draftInteractor);
        loggedInView.setDraftController(controller);
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
        addFriendsView.setGoHomeController(goHomeController);
        leagueView.setGoHomeController(goHomeController);
        return this;
    }

    //add friends view
    public AppBuilder addAddFriendsView(){
        addFriendsViewModel = new AddFriendsViewModel();
        addFriendsView = new AddFriendsView(addFriendsViewModel);
        cardPanel.add(addFriendsView, Constants.ADD_FRIENDS_VIEW_NAME);
        return this;
    }

    //add friends use case
    public AppBuilder addAddFriendsUseCase(){
        final AddFriendsOutputBoundary addFriendsPresenter = new AddFriendsPresenter(viewManagerModel, addFriendsViewModel);
        final AddFriendsInputBoundary addFriendsInteractor = new AddFriendsInteractor(addFriendsPresenter, userDataAccessObject);
        final AddFriendsController addFriendsController = new AddFriendsController(addFriendsInteractor);
        loggedInView.setAddFriendsController(addFriendsController);
        return this;
    }

    public AppBuilder addAddNewFriendUseCase() {
        final AddNewFriendOutputBoundary addNewFriendPresenter = new AddNewFriendPresenter(viewManagerModel, addFriendsViewModel);
        final AddNewFriendInputBoundary addNewFriendInteractor = new AddNewFriendInteractor(addNewFriendPresenter, userDataAccessObject);
        final AddNewFriendController controller = new AddNewFriendController(addNewFriendInteractor);
        addFriendsView.setAddNewFriendController(controller);
        return this;
    }

    public AppBuilder addToLeagueView(){
        leagueViewModel = new LeagueViewModel();
        leagueView = new LeagueView(leagueViewModel);
        cardPanel.add(leagueView, Constants.LEAGUE_VIEW_NAME);
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
        loggedInView.setToRankingsController(rankingsController);
        return this;
    }

    public AppBuilder addSoloPlayUseCase() {
        final SoloPlayOutputBoundary soloPlayPresenter = new SoloPlayPresenter(viewManagerModel,soloPlayViewModel);
        final SoloPlayInputBoundary soloPlayInteractor = new SoloPlayInteractor(soloPlayPresenter, userDataAccessObject);
        final SoloPlayController soloPlayController = new SoloPlayController(soloPlayInteractor);

        final UpdatePointsOutputBoundary updatePointsPresenter = new UpdatePointsPresenter(viewManagerModel,
                                                                                           soloPlayViewModel);
        try {
            final GuardianDataAccessObject guardianDataAccessObject = makeGuardianDataAccessObject();
            final UpdatePointsInputBoundary updatePointsInteractor = new UpdatePointsInteractor(guardianDataAccessObject, updatePointsPresenter);
            final UpdatePointsController updatePointsController = new UpdatePointsController(updatePointsInteractor);
            loggedInView.setSoloPlayController(soloPlayController);
            soloPlayView.setUpdatePointsConroller(updatePointsController);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    private GuardianDataAccessObject makeGuardianDataAccessObject() throws FileNotFoundException {
        try {
            String apiKey = new Scanner(new File("guardianAPIToken.txt")).nextLine();
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
//    public AppBuilder addChangePasswordUseCase() {
//        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
//                new ChangePasswordPresenter(loggedInViewModel);
//
//        final ChangePasswordInputBoundary changePasswordInteractor =
//                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);
//
//        final ChangePasswordController changePasswordController =
//                new ChangePasswordController(changePasswordInteractor);
//        loggedInView.setChangePasswordController(changePasswordController);
//        return this;
//    }

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
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(Constants.SIGN_UP_VIEW_NAME);
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
