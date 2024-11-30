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
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                                            .addSoloPlayView()
                                            .addSoloPlayUseCase()
                                            .addAddWordUseCase()
                                            .addFriendsView()
                                            .addToLeagueView()
                                            .addGoHomeUseCase()
                                            .addToLeagueUseCase()
                                            .addToRankingsView()
                                            .addToRankingsUseCase()
                                            .addAddFriendsUseCase()
                                            .addAddNewFriendUseCase()
                                            .addDraftView()
//                                            .addDraftUseCase()
                                            .addUpdateLeaguesUseCase()
                                            .addUpdatePointsforLeagueUseCase()
                                            .addUpdateLeaguePointsUseCase()
                                            .build();


        application.pack();
        application.setVisible(true);

    }
}
