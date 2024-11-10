package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data_access.Constants;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.solo_play.SoloPlayController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = Constants.LOGGED_IN_VIEW_NAME;
    private final LoggedInViewModel loggedInViewModel;
    private LogoutController logoutController;
    private SoloPlayController soloPlayController;
    private final JLabel greeting;

    private final JButton logOut;

    private final JButton soloPlay;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {

        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        greeting = new JLabel("WRONG MSG!");
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOG_OUT_LABEL);
        buttons.add(logOut);

        soloPlay = new JButton(loggedInViewModel.SOLO_PLAY_LABEL);
        buttons.add(soloPlay);

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

        this.add(greeting);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            greeting.setText(loggedInViewModel.GREETING + " " + state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setSoloPlayController(SoloPlayController soloPlayController) {
        this.soloPlayController = soloPlayController;}
}
