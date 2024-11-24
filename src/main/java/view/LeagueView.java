package view;

import data_access.Constants;
import interface_adapter.create_league.CreateLeagueController;
import interface_adapter.to_league.LeagueController;
import interface_adapter.to_league.LeagueViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LeagueView  extends JPanel implements ActionListener, PropertyChangeListener {
    private final LeagueViewModel leagueViewModel;
    private CreateLeagueController leagueController;

    public LeagueView(LeagueViewModel leagueViewModel) {
        this.leagueViewModel = leagueViewModel;
        //create new league panel
        JPanel createLeaguePanel = new JPanel();
        JButton createLeague = new JButton("Create League");
        JTextField newLeagueID = new JTextField("Enter League ID");
        createLeaguePanel.add(createLeague);
        createLeaguePanel.add(newLeagueID);

        JPanel mainPanel = new JPanel();
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

        finalPanel.setVisible(true);
        this.add(finalPanel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setCreateLeagueController(CreateLeagueController controller) {
        this.leagueController = controller;
    }
}
