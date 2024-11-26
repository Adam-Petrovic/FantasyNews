package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data_access.Constants;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.draft.DraftController;
import interface_adapter.logout.LogoutController;
import interface_adapter.solo_play.SoloPlayController;
import interface_adapter.to_league.LeagueController;
import interface_adapter.to_rankings.RankingsController;
import interface_adapter.update_leagues.UpdateLeaguesController;

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
    private AddFriendsController addFriendsController;
    private DraftController draftController;

    private final JLabel greeting;
    private final JButton logOut;
    private final JButton soloPlay;
    private final JButton toLeague;
    private final JButton toRankings;
    private final JButton addFriends;
    private final JButton draft;

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

        toLeague = new JButton("My League");
        buttons.add(toLeague);

        toRankings = new JButton("Rankings");
        buttons.add(toRankings);
      
        addFriends = new JButton("Friends");
        buttons.add(addFriends);

        draft = new JButton("Draft");
        buttons.add(draft);


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
                        updateLeaguesController.execute(loggedInViewModel.getState().getUsername());
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
                        addFriendsController.execute(loggedInViewModel.getState().getUsername());
                    }
                }
        );

        draft.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(draft)) {
                        draftController.execute(loggedInViewModel.getState().getUsername());
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

    public void setAddFriendsController(AddFriendsController addFriendsController) {
        this.addFriendsController = addFriendsController;
    }

    public void setDraftController(DraftController draftController){
        this.draftController = draftController;
    }

    public void setUpdateLeaguesController(UpdateLeaguesController updateLeaguesController){
        this.updateLeaguesController = updateLeaguesController;
    }

}
