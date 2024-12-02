package view.leagueviews;

// Standard library imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.League;
import interfaceadapter.leagueuserstory.to_league.LeagueState;
import interfaceadapter.leagueuserstory.to_league.LeagueViewModel;
import interfaceadapter.leagueuserstory.to_league_actions.ToLeagueActionsController;
import interfaceadapter.leagueuserstory.update_leagues.UpdateLeaguesController;
import interfaceadapter.navigation.go_home.GoHomeController;
import interfaceadapter.pointsuserstory.award_league_points.AwardLeaguePointsController;

/**
 * League View.
 */
public class LeagueView extends JPanel implements ActionListener, PropertyChangeListener {
    // controllers & stuff
    private final LeagueViewModel leagueViewModel;
    private GoHomeController goHomeController;
    private UpdateLeaguesController updateLeaguesController;
    private ToLeagueActionsController toLeagueActionsController;
    private AwardLeaguePointsController awardLeaguePointsController;

    // visuals

    // bottom panel
    private JPanel functionsPanel;

    // create league panel
    private JTextField createLeagueID;
    private JButton createLeagueButton;

    // join league panel
    private JTextField joinLeagueID;
    private JButton joinLeagueButton;

    // go home & functions
    private JButton goHomeButton;

    // viewLeague panel
    private JButton viewLeagueButton;
    private JTextField viewLeagueID;

    private ArrayList<League> leagues;

    public LeagueView(LeagueViewModel leagueViewModel) {
        this.leagueViewModel = leagueViewModel;
        this.leagueViewModel.addPropertyChangeListener(this);
        this.leagues = leagueViewModel.getState().getLeagues();

        // create new league stuff
        createLeagueID = new JTextField("Enter New League ID");
        createLeagueButton = new JButton("Create New League");

        // join league stuff
        joinLeagueID = new JTextField("Enter League ID");
        joinLeagueButton = new JButton("Join League");

        // go home stuff
        goHomeButton = new JButton("←");

        // see league stuff
        this.viewLeagueButton = new JButton("View League");
        this.viewLeagueID = new JTextField("Enter League ID");

        this.functionsPanel = new JPanel();
        functionsPanel.setLayout(new BoxLayout(functionsPanel, BoxLayout.X_AXIS));
        functionsPanel.add(goHomeButton);
        functionsPanel.add(viewLeagueButton);
        functionsPanel.add(viewLeagueID);
        functionsPanel.add(createLeagueButton);
        functionsPanel.add(createLeagueID);
        functionsPanel.add(joinLeagueButton);
        functionsPanel.add(joinLeagueID);

        this.add(functionsPanel);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                for (League league : leagues) {
                    awardLeaguePointsController.awardPoints(league.getId(), league.getUserObjArr());
                }
            }
        }, 0, 1, TimeUnit.DAYS);

        // button listeners
        createLeagueButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(createLeagueButton)) {
                        String username = leagueViewModel.getState().getUsername();
                        leagueViewModel.getState().setErrorMessage(null);
                        updateLeaguesController.execute(username, createLeagueID.getText(), false);
                    }
                }
        );

        goHomeButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(goHomeButton)) {
                        goHomeController.execute();
                    }
                }
        );

        joinLeagueButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(joinLeagueButton)) {
                        String username = leagueViewModel.getState().getUsername();
                        leagueViewModel.getState().setErrorMessage(null);
                        updateLeaguesController.execute(username, joinLeagueID.getText(), true);
                    }
                }
        );

        viewLeagueButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(viewLeagueButton)) {
                        String username = leagueViewModel.getState().getUsername();
                        leagueViewModel.getState().setErrorMessage(null);
                        toLeagueActionsController.execute(username, viewLeagueID.getText());
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LeagueState state = (LeagueState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }

        this.leagues = leagueViewModel.getState().getLeagues();
        for (League league : this.leagues) {
            awardLeaguePointsController.roundUp(league.getId(), league.getUserObjArr());
        }
    }

    /**
     * Sets the controller responsible for navigating back to the home view.
     *
     * @param controller the {@link GoHomeController} to be set.
     */
    public void setGoHomeController(GoHomeController controller) {
        this.goHomeController = controller;
    }

    /**
     * Sets the controller responsible for updating leagues.
     *
     * @param updateLeaguesController the {@link UpdateLeaguesController} to be set.
     */
    public void setUpdateLeaguesController(UpdateLeaguesController updateLeaguesController) {
        this.updateLeaguesController = updateLeaguesController;
    }

    /**
     * Sets the controller responsible for navigating to the league actions view.
     *
     * @param controller the {@link ToLeagueActionsController} to be set.
     */
    public void setToLeagueActionsController(ToLeagueActionsController controller) {
        this.toLeagueActionsController = controller;
    }

    /**
     * Sets the controller responsible for awarding points in the league.
     *
     * @param awardLeaguePointsController the {@link AwardLeaguePointsController} to be set.
     */
    public void setAwardLeaguePointsController(AwardLeaguePointsController awardLeaguePointsController) {
        this.awardLeaguePointsController = awardLeaguePointsController;
    }

}
