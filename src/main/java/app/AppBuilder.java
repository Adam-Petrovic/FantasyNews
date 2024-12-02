package app;

import java.awt.CardLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import dataaccess.Constants;
import dataaccess.GuardianDataAccessObject;
import dataaccess.PantryLeagueDataAccessObject;
import dataaccess.PantryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.LeagueFactory;
import entity.UserFactory;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.friendsuserstory.add_new_friend.AddNewFriendController;
import interfaceadapter.friendsuserstory.add_new_friend.AddNewFriendPresenter;
import interfaceadapter.friendsuserstory.to_friends.FriendsController;
import interfaceadapter.friendsuserstory.to_friends.FriendsPresenter;
import interfaceadapter.friendsuserstory.to_friends.FriendsViewModel;
import interfaceadapter.leagueuserstory.to_league.LeagueController;
import interfaceadapter.leagueuserstory.to_league.LeaguePresenter;
import interfaceadapter.leagueuserstory.to_league.LeagueViewModel;
import interfaceadapter.leagueuserstory.to_league_actions.LeagueActionsViewModel;
import interfaceadapter.leagueuserstory.to_league_actions.ToLeagueActionsController;
import interfaceadapter.leagueuserstory.to_league_actions.ToLeagueActionsPresenter;
import interfaceadapter.leagueuserstory.update_leagues.UpdateLeaguesController;
import interfaceadapter.leagueuserstory.update_leagues.UpdateLeaguesPresenter;
import interfaceadapter.navigation.go_home.GoHomeController;
import interfaceadapter.navigation.go_home.GoHomePresenter;
import interfaceadapter.navigation.loggedIn.LoggedInViewModel;
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
import interfaceadapter.pointsuserstory.award_league_points.AwardLeaguePointsController;
import interfaceadapter.pointsuserstory.update_league_points.UpdateLeaguePointsController;
import interfaceadapter.pointsuserstory.update_solo_points.UpdateSoloPlayPointsController;
import interfaceadapter.pointsuserstory.update_solo_points.UpdateSoloPlayPointsPresenter;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsController;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsPresenter;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsViewModel;
import interfaceadapter.rankingsuserstory.update_rankings.UpdateRankingsController;
import interfaceadapter.rankingsuserstory.update_rankings.UpdateRankingsPresenter;
import interfaceadapter.selectwordsuserstory.add_word.AddWordController;
import interfaceadapter.selectwordsuserstory.add_word.AddWordPresenter;
import interfaceadapter.selectwordsuserstory.draft_words.DraftViewModel;
import interfaceadapter.selectwordsuserstory.draft_words.DraftWordsController;
import interfaceadapter.selectwordsuserstory.draft_words.DraftWordsPresenter;
import interfaceadapter.selectwordsuserstory.to_draft.ToDraftController;
import interfaceadapter.selectwordsuserstory.to_draft.ToDraftPresenter;
import usecase.frienduserstory.add_new_friend.AddNewFriendInputBoundary;
import usecase.frienduserstory.add_new_friend.AddNewFriendInteractor;
import usecase.frienduserstory.add_new_friend.AddNewFriendOutputBoundary;
import usecase.frienduserstory.to_friends.FriendsInputBoundary;
import usecase.frienduserstory.to_friends.FriendsInteractor;
import usecase.frienduserstory.to_friends.FriendsOutputBoundary;
import usecase.leagueuserstory.to_league.LeagueOutputBoundary;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsInputBoundary;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsInteractor;
import usecase.leagueuserstory.to_league_actions.ToLeagueActionsOutputBoundary;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesInputBoundary;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesInteractor;
import usecase.leagueuserstory.update_leagues.UpdateLeaguesOutputBoundary;
import usecase.navigation.goHome.GoHomeOutputBoundary;
import usecase.navigation.login.LoginInputBoundary;
import usecase.navigation.login.LoginInteractor;
import usecase.navigation.login.LoginOutputBoundary;
import usecase.navigation.logout.LogoutInputBoundary;
import usecase.navigation.logout.LogoutInteractor;
import usecase.navigation.logout.LogoutOutputBoundary;
import usecase.navigation.signup.SignupInputBoundary;
import usecase.navigation.signup.SignupInteractor;
import usecase.navigation.signup.SignupOutputBoundary;
import usecase.navigation.solo_play.SoloPlayInputBoundary;
import usecase.navigation.solo_play.SoloPlayInteractor;
import usecase.navigation.solo_play.SoloPlayOutputBoundary;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInputBoundary;
import usecase.pointsuserstory.award_league_points.AwardLeaguePointsInteractor;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsInputBoundary;
import usecase.pointsuserstory.round_league_points.RoundLeaguePointsInteractor;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueDataAccessObject;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueInputBoundary;
import usecase.pointsuserstory.updatePointsForLeague.UpdatePointsForLeagueInteractor;
import usecase.pointsuserstory.update_solo_points.UpdatePointsInteractor;
import usecase.pointsuserstory.update_solo_points.UpdatePointsOutputBoundary;
import usecase.pointsuserstory.update_solo_points.UpdateSoloPlayPointsDataAccessInterface;
import usecase.pointsuserstory.update_solo_points.UpdateSoloPlayPointsInputBoundary;
import usecase.rankingsuserstory.to_rankings.RankingsOutputBoundary;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsInputBoundary;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsInteractor;
import usecase.rankingsuserstory.update_rankings.UpdateRankingsOutputBoundary;
import usecase.selectwordsuserstory.addWord.AddWordInputBoundary;
import usecase.selectwordsuserstory.addWord.AddWordInteractor;
import usecase.selectwordsuserstory.addWord.AddWordOutputBoundary;
import usecase.selectwordsuserstory.draft_words.DraftWordsInputBoundary;
import usecase.selectwordsuserstory.draft_words.DraftWordsInteractor;
import usecase.selectwordsuserstory.draft_words.DraftWordsOutputBoundary;
import usecase.selectwordsuserstory.to_draft.ToDraftInputBoundary;
import usecase.selectwordsuserstory.to_draft.ToDraftInteractor;
import usecase.selectwordsuserstory.to_draft.ToDraftOutputBoundary;
import view.ViewManager;
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
 * App builder creates our app using builder design pattern.
 */
@SuppressWarnings({"checkstyle:ClassDataAbstractionCoupling", "checkstyle:SuppressWarnings", "checkstyle:ClassFanOutComplexity", "checkstyle:LineLength"})
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final UserFactory userFactory = new CommonUserFactory();
    private final LeagueFactory leagueFactory = new LeagueFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final PantryUserDataAccessObject userDataAccessObject = new PantryUserDataAccessObject(userFactory);
    private final PantryLeagueDataAccessObject leagueDataAccessObject = new PantryLeagueDataAccessObject();
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

    /**
     * Adds UpdateLeaguesUseCase.
     * @return the UpdateLeaguesUseCase
     */
    public AppBuilder addUpdateLeaguesUseCase() {
        final UpdateLeaguesOutputBoundary updateLeaguesPresenter = new UpdateLeaguesPresenter(viewManagerModel, leagueViewModel);
        final UpdateLeaguesInputBoundary updateLeaguesInteractor = new UpdateLeaguesInteractor(updateLeaguesPresenter,
                userDataAccessObject, leagueDataAccessObject);
        final UpdateLeaguesController controller = new UpdateLeaguesController(updateLeaguesInteractor);
        loggedInView.setUpdateLeaguesController(controller);
        leagueView.setUpdateLeaguesController(controller);
        return this;
    }

    /**
     * Adds Draft View.
     *
     * @return Draft view
     */
    public AppBuilder addDraftView() {
        draftViewModel = new DraftViewModel();
        draftView = new DraftView(draftViewModel);
        cardPanel.add(draftView, "draft");
        return this;
    }

    /**
     * Adds the Draft Use Case.
     *
     * @return Draft Use case
     */
    public AppBuilder addDraftUseCase() {
        final DraftWordsOutputBoundary presentr = new DraftWordsPresenter(viewManagerModel, draftViewModel);
        final DraftWordsInputBoundary draftWordsInteractor = new DraftWordsInteractor(presentr, leagueDataAccessObject);
        final DraftWordsController draftWordsController = new DraftWordsController(draftWordsInteractor);
        draftView.setDraftWordsController(draftWordsController);
        return this;
    }

    /**
     * Adds the add to Draft use Case.
     *
     * @return the add to Draft use Case
     */
    public AppBuilder addToDraftUseCase() {
        final ToDraftOutputBoundary draftPresenter = new ToDraftPresenter(viewManagerModel, draftViewModel);
        final ToDraftInputBoundary draftInteractor = new ToDraftInteractor(draftPresenter, leagueDataAccessObject);
        final ToDraftController controller = new ToDraftController(draftInteractor);
        leagueActionsView.setDraftController(controller);
        return this;
    }

    /**
     * Adds the Signup View to the application.
     *
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, Constants.SIGN_UP_VIEW_NAME);
        return this;
    }

    /**
     * Adds the league actions view.
     *
     * @return the league actions view
     */
    public AppBuilder addLeagueActionsView() {
        leagueActionsViewModel = new LeagueActionsViewModel();
        leagueActionsView = new LeagueActionsView(leagueActionsViewModel);
        cardPanel.add(leagueActionsView, Constants.LEAGUE_ACTIONS_VIEW_NAME);
        return this;
    }

    /**
     * Adds the SoloPlay View to the application.
     *
     * @return the SoloPlay View
     */
    public AppBuilder addSoloPlayView() {
        soloPlayViewModel = new SoloPlayViewModel();
        soloPlayView = new SoloPlayView(soloPlayViewModel);
        cardPanel.add(soloPlayView, Constants.SOLO_PLAY_VIEW_NAME);
        return this;
    }

    /**
     * Adds the add word usecase.
     *
     * @return add word usecase
     */
    public AppBuilder addAddWordUseCase() {
        final AddWordOutputBoundary addWordPresenter = new AddWordPresenter(viewManagerModel, soloPlayViewModel);
        final AddWordInputBoundary addWordInteractor = new AddWordInteractor(addWordPresenter, userDataAccessObject);
        final AddWordController controller = new AddWordController(addWordInteractor);
        soloPlayView.setAddWordController(controller);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addGoHomeUseCase() {
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

    /**
     * Adds the FriendsView to the builder.
     *
     * @return the FriendsView
     */
    public AppBuilder addFriendsView() {
        friendsViewModel = new FriendsViewModel();
        friendsView = new FriendsView(friendsViewModel);
        cardPanel.add(friendsView, Constants.ADD_FRIENDS_VIEW_NAME);
        return this;
    }

    /**
     * Adds the addFriendsUseCase.
     *
     * @return the addFriendsUseCase
     */
    public AppBuilder addAddFriendsUseCase() {
        final FriendsOutputBoundary addFriendsPresenter = new FriendsPresenter(viewManagerModel, friendsViewModel);
        final FriendsInputBoundary addFriendsInteractor = new FriendsInteractor(addFriendsPresenter, userDataAccessObject);
        final FriendsController friendsController = new FriendsController(addFriendsInteractor);
        loggedInView.setAddFriendsController(friendsController);
        return this;
    }

    /**
     * Adds the ToLeagueActionsUseCase.
     *
     * @return the ToLeagueActionsUseCase
     */
    public AppBuilder addToLeagueActionsUseCase() {
        final ToLeagueActionsOutputBoundary toLeagueActionsPresenter = new ToLeagueActionsPresenter(
                leagueActionsViewModel, leagueViewModel, viewManagerModel);
        final ToLeagueActionsInputBoundary toLeagueActionsInteractor = new ToLeagueActionsInteractor(
                toLeagueActionsPresenter, userDataAccessObject, leagueDataAccessObject);
        final ToLeagueActionsController toLeagueActionsController = new ToLeagueActionsController(toLeagueActionsInteractor);
        leagueView.setToLeagueActionsController(toLeagueActionsController);
        return this;
    }

    /**
     * Adds the AddNewFriendUseCase.
     *
     * @return the AddNewFriendUseCase
     */
    public AppBuilder addAddNewFriendUseCase() {
        final AddNewFriendOutputBoundary addNewFriendPresenter = new AddNewFriendPresenter(viewManagerModel, friendsViewModel);
        try {
            final GuardianDataAccessObject guardianDataAccessObject = makeGuardianDataAccessObject();
            final AddNewFriendInputBoundary addNewFriendInteractor = new AddNewFriendInteractor(addNewFriendPresenter, userDataAccessObject, guardianDataAccessObject);
            final AddNewFriendController controller = new AddNewFriendController(addNewFriendInteractor);
            friendsView.setAddNewFriendController(controller);
        }

        catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return this;
    }

    /**
     * Adds the ToLeagueView usecase.
     *
     * @return the ToLeagueView usecase
     */
    public AppBuilder addToLeagueView() {
        leagueViewModel = new LeagueViewModel();
        leagueView = new LeagueView(leagueViewModel);
        cardPanel.add(leagueView, Constants.LEAGUE_VIEW_NAME);
        return this;
    }

    /**
     * Adds the UpdatePointsForLeagueUseCase.
     *
     * @return the UpdatePointsForLeagueUseCase
     */
    public AppBuilder addUpdatePointsForLeagueUseCase() {
        final UpdatePointsForLeagueDataAccessObject updatePointsForLeagueDataAccessObject;
        try {
            updatePointsForLeagueDataAccessObject = makeGuardianDataAccessObject();
            final UpdatePointsForLeagueInputBoundary updateLeaguePointsInteractor =
                    new UpdatePointsForLeagueInteractor(updatePointsForLeagueDataAccessObject);
            final UpdateLeaguePointsController controller =
                    new UpdateLeaguePointsController(updateLeaguePointsInteractor);
            leagueActionsView.setUpdatePointsForLeagueController(controller);
        }

        catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return this;
    }

    /**
     * Adds the UpdateLeaguePointsUseCase to the builder.
     * @return the UpdateLeaguePointsUseCase
     */
    public AppBuilder addUpdateLeaguePointsUseCase() {
        final AwardLeaguePointsInputBoundary awardLeaguePointsInputBoundary =
                new AwardLeaguePointsInteractor(leagueDataAccessObject);
        final RoundLeaguePointsInputBoundary roundLeaguePointsInteractor =
                new RoundLeaguePointsInteractor(leagueDataAccessObject);
        final AwardLeaguePointsController controller =
                new AwardLeaguePointsController(awardLeaguePointsInputBoundary, roundLeaguePointsInteractor);
        leagueView.setAwardLeaguePointsController(controller);
        return this;
    }

    /**
     * Adds the ToLeagueUseCase functionality.
     * @return the ToLeagueUseCase functionality
     */
    public AppBuilder addToLeagueUseCase() {
        final LeagueOutputBoundary leaguePresenter = new LeaguePresenter(viewManagerModel, leagueViewModel);
        final LeagueController leagueController = new LeagueController(leaguePresenter);
        loggedInView.setToLeagueController(leagueController);
        return this;
    }

    /**
     * Adds the ToRankingsView.
     * @return the ToRankingsView
     */
    public AppBuilder addToRankingsView() {
        rankingsViewModel = new RankingsViewModel();
        rankingsView = new RankingsView(rankingsViewModel);
        cardPanel.add(rankingsView, Constants.RANKINGS_VIEW_NAME);
        return this;
    }

    /**
     * Adds the ToRankingsUseCase.
     * @return the ToRankingsUseCase
     */
    public AppBuilder addToRankingsUseCase() {
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
        }
        catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return this;
    }

    /**
     * Adds the SoloPlayUseCase to the project.
     * @return the SoloPlayUseCase
     */
    public AppBuilder addSoloPlayUseCase() {
        final SoloPlayOutputBoundary soloPlayPresenter = new SoloPlayPresenter(viewManagerModel, soloPlayViewModel);
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
        }

        catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return this;
    }

    private GuardianDataAccessObject makeGuardianDataAccessObject() throws FileNotFoundException {
        try {
            String apiKey = new Scanner(new File("src/main/resources/apikeys/guardianAPIToken.txt")).nextLine();
            return new GuardianDataAccessObject(apiKey);
        }

        catch (FileNotFoundException exception) {
            System.out.println("Need to find API token, and call the file GuardianAPIToken");
            return null;
        }
    }

    /**
     * Adds the Login View to the application.
     *
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
     *
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
     *
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
     *
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
     * Adds the Logout Use Case to the application.
     *
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
     *
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
