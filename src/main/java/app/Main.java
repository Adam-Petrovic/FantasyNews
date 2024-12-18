package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();

        final JFrame application = appBuilder
                                            .addLeagueActionsView()
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                                            .addSoloPlayView()
                                            .addSoloPlayUseCase()
                                            .addAddWordUseCase()
                                            .addDraftView()
                                            .addFriendsView()
                                            .addToLeagueView()
                                            .addToRankingsView()
                                            .addToLeagueUseCase()
                                            .addDraftView()
                                            .addToRankingsUseCase()
                                            .addAddFriendsUseCase()
                                            .addAddNewFriendUseCase()
                                            .addToDraftUseCase()
                                            .addUpdateLeaguesUseCase()
                                            .addUpdatePointsForLeagueUseCase()
                                            .addUpdateLeaguePointsUseCase()
                                            .addToLeagueActionsUseCase()
                                            .addDraftUseCase()
                                            .addGoHomeUseCase()
                                            .build();

        application.setVisible(true);
        application.pack();

    }
}
