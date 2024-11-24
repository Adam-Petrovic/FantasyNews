package view;

import entity.League;
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
    private JPanel mainPanel;
    private League league;

    public LeagueView(LeagueViewModel leagueViewModel) {
        this.leagueViewModel = leagueViewModel;
        this.leagueViewModel.addPropertyChangeListener(this);
        this.league = leagueViewModel.getState().getLeague();

        //create new league panel;
        createLeaguePanel = new JPanel();
        createLeaguePanel.setLayout(new BoxLayout(createLeaguePanel, BoxLayout.Y_AXIS));

        createLeagueID = new JTextField("Enter New League ID");
        createLeaguePanel.add(createLeagueID);

        createLeagueButton = new JButton("Create New League");
        createLeaguePanel.add(createLeagueButton);

        //displayLeaguePanel
        displayLeaguePanel = new JPanel();
        displayLeaguePanel.setLayout(new BoxLayout(displayLeaguePanel, BoxLayout.Y_AXIS));

        this.leagueLabel = new JLabel("League ID: " + leagueViewModel.getState().getLeagueID());
        displayLeaguePanel.add(leagueLabel);

        String[] userTitle = {"Member"};
        String [][] usernames = new String[3][1];
        JTable userTable = new JTable(usernames, userTitle);

        String[] topicsTitle = {"Food", "Mood", "Dude!"};
        String[][] words =  {{"Apricot", "Sleepy", "Epic"}, {"Apple", "Brood", "Dude"}, {"Terabyte", "Byte", "bite"}};
        JTable wordsTable = new JTable(words, topicsTitle);
        JScrollPane wordsScrollPane = new JScrollPane(wordsTable);
        JScrollPane userScrollPane = new JScrollPane(userTable);
        displayLeaguePanel.add(userScrollPane);
        displayLeaguePanel.add(wordsScrollPane);
        userTable.setDefaultEditor(Object.class, null);

        Dimension dw = new Dimension(words[0].length * 100, words[0].length * 150);
        Dimension du = new Dimension(userTitle.length * 50, words[0].length * 150);

        userScrollPane.setPreferredSize(du);
        wordsScrollPane.setPreferredSize(dw);

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

        //createLeagueListener();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("here!");
        this.leagueLabel.setText("League: " + leagueViewModel.getState().getLeagueID());
    }

    public void setCreateLeagueController(CreateLeagueController controller) {
        this.createLeagueController = controller;
    }
}
