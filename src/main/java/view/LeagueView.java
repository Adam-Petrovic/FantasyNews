package view;

import data_access.Constants;
import entity.League;
import entity.User;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.signup.SignupState;
import interface_adapter.to_league.LeagueState;
import interface_adapter.to_league.LeagueViewModel;
import interface_adapter.to_league_actions.ToLeagueActionsController;
import interface_adapter.updateLeaguePoints.UpdateLeaguePointsController;
import interface_adapter.update_leagues.UpdateLeaguesController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class LeagueView  extends JPanel implements ActionListener, PropertyChangeListener {
    //controllers & stuff
    private final LeagueViewModel leagueViewModel;
    private GoHomeController goHomeController;
    private UpdateLeaguesController updateLeaguesController;
    private ToLeagueActionsController toLeagueActionsController;
    //visuals

    //bottom panel
    private JPanel functionsPanel;

    //create league panel
    private JTextField createLeagueID;
    private JButton createLeagueButton;

    //join league panel
    private JTextField joinLeagueID;
    private JButton joinLeagueButton;

    //go home & functions
    private JButton goHomeButton;

    //viewLeague panel
    private JButton viewLeagueButton;
    private JTextField viewLeagueID;

    ArrayList<League> leagues;

    public LeagueView(LeagueViewModel leagueViewModel) {
        this.leagueViewModel = leagueViewModel;
        this.leagueViewModel.addPropertyChangeListener(this);
        this.leagues = (leagueViewModel.getState().getLeagues());

        //create new league stuff
        createLeagueID = new JTextField("Enter New League ID");
        createLeagueButton = new JButton("Create New League");

        //join league stuff
        joinLeagueID = new JTextField("Enter League ID");
        joinLeagueButton = new JButton("Join League");

        //go home stuff
        goHomeButton = new JButton("←");

        //see league stuff
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

        //button listeners
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
                        toLeagueActionController.execute(viewLeagueID.getText());
                    }
                }
        );



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void setGoHomeController(GoHomeController controller){
        this.goHomeController = controller;
    }

    public void setUpdateLeaguesController(UpdateLeaguesController updateLeaguesController){
        this.updateLeaguesController = updateLeaguesController;
    }

    public void setToLeagueActionsController(ToLeagueActionsController controller){
        this.toLeagueActionsController = controller;
    }
}
