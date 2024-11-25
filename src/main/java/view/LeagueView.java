package view;

import entity.League;
import entity.User;
import interface_adapter.create_league.CreateLeagueController;
import interface_adapter.to_league.LeagueState;
import interface_adapter.to_league.LeagueViewModel;

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
    private final LeagueViewModel leagueViewModel;
    private CreateLeagueController createLeagueController;

    //visuals
    private JLabel leagueLabel;
    private JTextField createLeagueID;
    private JButton createLeagueButton;
    private JPanel createLeaguePanel;
    private JPanel displayLeaguePanel;
    private JTable displayLeagueTable;
    private JScrollPane displayLeagueScorePane;
    private JPanel mainPanel;
    private League league;

    public LeagueView(LeagueViewModel leagueViewModel) {
        this.leagueViewModel = leagueViewModel;
        this.leagueViewModel.addPropertyChangeListener(this);
        //might need to pass in user too TvT
        this.league = leagueViewModel.getState().getLeague();

        //create new league panel;
        createLeaguePanel = new JPanel();
        createLeaguePanel.setLayout(new BoxLayout(createLeaguePanel, BoxLayout.Y_AXIS));

        createLeagueID = new JTextField("Enter New League ID", 15);
        createLeaguePanel.add(createLeagueID);

        createLeagueButton = new JButton("Create New League");
        createLeaguePanel.add(createLeagueButton);

        //displayLeaguePanel
        displayLeaguePanel = new JPanel();
        displayLeaguePanel.setLayout(new BoxLayout(displayLeaguePanel, BoxLayout.Y_AXIS));

        this.leagueLabel = new JLabel("League ID: " + leagueViewModel.getState().getLeagueID());
        displayLeaguePanel.add(leagueLabel);
        displayLeaguePanel.add(new JLabel("not currently in a league"));

        this.displayLeagueScorePane = new JScrollPane();
        displayLeaguePanel.add(displayLeagueScorePane);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(createLeaguePanel);
        this.add(displayLeaguePanel);


        //buttons
        createLeagueButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(createLeagueButton)) {
                        String username = leagueViewModel.getState().getUsername();
                        createLeagueController.execute(username, createLeagueID.getText());
                    }
                }
        );

        createLeagueListener();
    }

    private void createLeagueListener() {
        createLeagueID.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                LeagueState currentState = leagueViewModel.getState();
                currentState.setLeagueID(createLeagueID.getText());
                leagueViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    //updating the league display yasssss
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.league = leagueViewModel.getState().getLeague();
        this.leagueLabel.setText("League: " + leagueViewModel.getState().getLeagueID());

        ArrayList<User> users = this.league.getUsers();
        String[] columnNames = {"Member", "Points"};
        String[][] info = new String[users.size()][2];

        for(int i = 0; i < users.size(); i++) {
            info[i][0] = users.get(i).getName();
            info[i][1] = String.valueOf(users.get(i).getLeaguePoints());
        }

        this.displayLeagueTable = new JTable(info, columnNames);
        displayLeagueTable.setDefaultEditor(Object.class, null);
        this.displayLeagueScorePane = new JScrollPane(displayLeagueTable);


    }

    public void setCreateLeagueController(CreateLeagueController controller) {
        this.createLeagueController = controller;
    }
}
