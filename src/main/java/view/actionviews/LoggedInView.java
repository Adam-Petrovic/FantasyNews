package view.actionviews;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import interfaceadapter.friendsuserstory.to_friends.FriendsController;
import interfaceadapter.navigation.loggedIn.LoggedInState;
import interfaceadapter.navigation.loggedIn.LoggedInViewModel;
import interfaceadapter.navigation.logout.LogoutController;
import interfaceadapter.navigation.solo_play.SoloPlayController;
import interfaceadapter.leagueuserstory.to_league.LeagueController;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsController;
import interfaceadapter.leagueuserstory.update_leagues.UpdateLeaguesController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final LoggedInViewModel loggedInViewModel;
    private LogoutController logoutController;
    private SoloPlayController soloPlayController;
    private LeagueController leagueController;
    private UpdateLeaguesController updateLeaguesController;
    private RankingsController rankingsController;

    private FriendsController friendsController;


    private final JLabel greeting;
    private final JButton logOut;
    private final JButton soloPlay;
    private final JButton toLeague;
    private final JButton toRankings;
    private final JButton addFriends;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        greeting = new JLabel();
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        logOut = new JButton(LoggedInViewModel.LOG_OUT_LABEL);
        buttons.add(logOut);

        soloPlay = new JButton(LoggedInViewModel.SOLO_PLAY_LABEL);
        buttons.add(soloPlay);

        toLeague = new JButton("My Leagues");
        buttons.add(toLeague);

        toRankings = new JButton("Rankings");
        buttons.add(toRankings);
      
        addFriends = new JButton("Friends");
        buttons.add(addFriends);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        changePassword.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                evt -> {
//                    if (evt.getSource().equals(changePassword)) {
//                        final LoggedInState currentState = loggedInViewModel.getState();
//
//                        this.changePasswordController.execute(
//                                currentState.getUsername(),
//                                currentState.getPassword()
//                        );
//                    }
//                }
//        );

        soloPlay.addActionListener(
                evt -> {
                    if (evt.getSource().equals(soloPlay)) {
                        System.out.println(this.loggedInViewModel.getState().getUsername());
                        soloPlayController.execute(loggedInViewModel.getState().getUsername());
                    }
                });

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        logoutController.execute(loggedInViewModel.getState().getUsername());
                    }
                }
        );

        toLeague.addActionListener(
                evt -> {
                    if (evt.getSource().equals(toLeague)) {
                        leagueController.execute(loggedInViewModel.getState().getUsername());
                    }
                }
        );


        toRankings.addActionListener(
                evt -> {
                    if (evt.getSource().equals(toRankings)) {
                        rankingsController.execute();
                    }
                });

        addFriends.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addFriends)) {
                        friendsController.execute(loggedInViewModel.getState().getUsername());
                    }
                }
        );

        this.add(greeting);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            greeting.setText(LoggedInViewModel.GREETING + " " + state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setSoloPlayController(SoloPlayController soloPlayController) {this.soloPlayController = soloPlayController;
    }

    public void setToLeagueController(LeagueController toLeagueController) {this.leagueController = toLeagueController;}

    public void setToRankingsController(RankingsController toRankingsController) {this.rankingsController = toRankingsController;}

    public void setAddFriendsController(FriendsController friendsController) {
        this.friendsController = friendsController;
    }


    public void setUpdateLeaguesController(UpdateLeaguesController updateLeaguesController){
        this.updateLeaguesController = updateLeaguesController;
    }

}
