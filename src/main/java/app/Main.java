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
                                            .addAddFriendsView()
                                            .addGoHomeUseCase()
                                            .addToLeagueView()
                                            .addToLeagueUseCase()
                                            .addCreateLeagueUseCase()
                                            .addToRankingsView()
                                            .addToRankingsUseCase()
                                            .addAddFriendsUseCase()
                                            .addAddNewFriendUseCase()
                                            .addDraftView()
                                            .addDraftUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);


    }
}
