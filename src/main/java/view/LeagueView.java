package view;

import data_access.Constants;
import entity.User;
import interface_adapter.add_friends.AddFriendsState;
import interface_adapter.create_league.CreateLeagueController;
import interface_adapter.signup.SignupState;
import interface_adapter.to_league.LeagueController;
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

    private JLabel league;
    private JTextField newLeagueID;
    private JPanel realFinalPanel;

    public LeagueView(LeagueViewModel leagueViewModel) {
        this.leagueViewModel = leagueViewModel;
        this.leagueViewModel.addPropertyChangeListener(this);

        //create new league panel;
        JPanel createLeaguePanel = new JPanel();
        JButton createLeague = new JButton("Create League");
        this.newLeagueID = new JTextField("Enter League ID");
        createLeaguePanel.add(createLeague);
        createLeaguePanel.add(newLeagueID);

        JPanel mainPanel = new JPanel();
        this.league = new JLabel("League: " + leagueViewModel.getState().getLeagueID());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        String[] userTitle = {"User"};
        String[][] usernames =  {{"usr1"}, {"usr2"}, {"usr3"}};
        JTable userTable = new JTable(usernames, userTitle);

        String[] topicsTitle = {"Food", "Mood", "Dude!"};
        String[][] words =  {{"Apricot", "Sleepy", "Epic"}, {"Apple", "Brood", "Dude"}, {"Terabyte", "Byte", "bite"}};
        JTable wordsTable = new JTable(words, topicsTitle);
        JScrollPane wordsScrollPane = new JScrollPane(wordsTable);
        JScrollPane userScrollPane = new JScrollPane(userTable);
        mainPanel.add(userScrollPane);
        mainPanel.add(wordsScrollPane);
        userTable.setDefaultEditor(Object.class, null);

        Dimension dw = new Dimension(words[0].length * 100, words[0].length * 150);
        Dimension du = new Dimension(userTitle.length * 50, words[0].length * 150);

        userScrollPane.setPreferredSize(du);
        wordsScrollPane.setPreferredSize(dw);

        JPanel finalPanel = new JPanel();
        finalPanel.add(createLeaguePanel);
        finalPanel.add(mainPanel);

        this.realFinalPanel = new JPanel();
        realFinalPanel.setLayout(new BoxLayout(realFinalPanel, BoxLayout.Y_AXIS));
        realFinalPanel.add(league);
        realFinalPanel.add(finalPanel);

        realFinalPanel.setVisible(true);
        this.add(realFinalPanel);

        //buttons
        createLeague.addActionListener(
                evt -> {
                    if (evt.getSource().equals(createLeague)) {
                        String username = leagueViewModel.getState().getUsername();
                        createLeagueController.execute(username, newLeagueID.getText());
                    }
                }
        );

        //createLeagueListener();
    }

    private void createLeagueListener() {
        newLeagueID.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                LeagueState currentState = leagueViewModel.getState();
                currentState.setLeagueID(newLeagueID.getText());
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
        System.out.println("league state says: " + leagueViewModel.getState().getLeagueID());
        this.league.setText("League: " + leagueViewModel.getState().getLeagueID());
    }

    public void setCreateLeagueController(CreateLeagueController controller) {
        this.createLeagueController = controller;
    }
}
