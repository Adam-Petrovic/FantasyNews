package view;

import data_access.Constants;
import entity.League;
import entity.User;
import interface_adapter.to_rankings.RankingsViewModel;
import interface_adapter.update_rankings.UpdateRankingsController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class RankingsView extends JPanel implements PropertyChangeListener {
    private final RankingsViewModel rankingsViewModel;
    private UpdateRankingsController updateRankingsController;
    private User user;
    private League league;
    //private JScrollPane jScrollPane;
    private JPanel displayRankingsPanel;

    public RankingsView(RankingsViewModel rankingsViewModel) {
        //rankings table
        this.league = new League();
        this.rankingsViewModel = rankingsViewModel;
        this.rankingsViewModel.addPropertyChangeListener(this);
        rankingsViewModel.getState().setLeague(this.league);
        //this.user = rankingsViewModel.getState().getUser();
        //this.league = rankingsViewModel.getState().getLeague();
//
//        // category view
        displayRankingsPanel = new JPanel();
        displayRankingsPanel.setLayout(new BoxLayout(displayRankingsPanel, BoxLayout.X_AXIS));
//
//        String[] title = {"Ranking"};
//        String[][] indices =  {{"1"}, {"2"}, {"3"}};
//        JTable indexTable = new JTable(indices, title);
//
//        String[] userTitles = {"user", "points"};
//        String[][] user =  {{"user1", "5"}, {"user3", "4"}, {"user2", "1"}};
//        JTable userTable = new JTable(user, userTitles);
//
//        //String[] topicsTitle = {"Food", "Mood", "Dude!"};
//        //String[][] words =  {{"user1", "user2", "user1"}, {"user3", "user1", "user2"}, {"user2", "user3", "user3"}};
//        //JTable wordsTable = new JTable(words, topicsTitle);
//
//        JScrollPane indexScrollPane = new JScrollPane(indexTable);
//        JScrollPane userScrollPane = new JScrollPane(userTable);
//        //JScrollPane wordsScrollPane = new JScrollPane(wordsTable);
//        displayRankingsPanel.add(indexScrollPane);
//        displayRankingsPanel.add(userScrollPane);
//        //mainPanel.add(wordsScrollPane);
//        indexTable.setDefaultEditor(Object.class, null);
//
//        //Dimension dw = new Dimension(words[0].length * 100, words[0].length * 150);
//        //Dimension du = new Dimension(title.length * 100, words[0].length * 150);
//
//        //wordsScrollPane.setPreferredSize(dw);
//        //indexScrollPane.setPreferredSize(du);
//
//        //update rankings button
        JButton updateRankings = new JButton("Update Rankings");
        displayRankingsPanel.add(updateRankings);

        JTextField rankingsLeagueID = new JTextField("Enter League ID");
        displayRankingsPanel.add(rankingsLeagueID);

        updateRankings.addActionListener(evt -> {
            if (evt.getSource() == updateRankings) {
                updateRankingsController.execute(rankingsLeagueID.getText());
            }
        });


        displayRankingsPanel.setVisible(true);
        this.add(displayRankingsPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.league = rankingsViewModel.getState().getLeague();
        ArrayList<User> rankings = league.getRankings();
        System.out.println(rankings);

        String[] title = {"Ranking"};
        Integer[][] indices = new Integer[rankings.size()][1];

        String[][] user = new String[rankings.size()][2];
        String[] userTitles = {"user", "points"};

        for (int i = 0; i < rankings.size(); i++) {
            indices[i][0] = i + 1;
            user[rankings.size() - i - 1][0] = rankings.get(i).getName();
            user[rankings.size() - i - 1][1] = Integer.toString(rankings.get(i).getLeaguePoints());
        }

        JTable userTable = new JTable(user, userTitles);
        JTable indexTable = new JTable(indices, title);
        indexTable.setDefaultEditor(Object.class, null);
        userTable.setDefaultEditor(Object.class, null);
        displayRankingsPanel.add(indexTable);
        displayRankingsPanel.add(userTable);
        //displayRankingsPanel.setViewportView(indexTable);
        //displayRankingsPanel.setViewportView(userTable);
    }

    public void setUpdateRankingsController(UpdateRankingsController updateRankingsController) {
        this.updateRankingsController = updateRankingsController;
    }

}
