package view;

import data_access.Constants;
import entity.League;
import entity.User;
import interface_adapter.go_home.GoHomeController;
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
    private GoHomeController goHomeController;
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
        JButton goHomeButton = new JButton("←");
        displayRankingsPanel.add(goHomeButton);

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

        goHomeButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(goHomeButton)) {
                        goHomeController.execute();
                    }
                });


        displayRankingsPanel.setVisible(true);
        this.add(displayRankingsPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.league = rankingsViewModel.getState().getLeague();
        ArrayList<User> liveRankings = league.getLiveRankings();
        ArrayList<User> historicalRankings = league.getHistoricalRankings();

        String[] liveTitles = {"Live Ranking", "user", "points"};
        String[][] liveRows = new String[liveRankings.size()][3];

        String[] historicalTitles = {"Historical Ranking", "user", "points"};
        String[][] historicalRows = new String[historicalRankings.size()][3];

        for (int i = 0; i < liveRankings.size(); i++) {
            liveRows[i][0] = Integer.toString(i + 1);
            liveRows[liveRankings.size() - i - 1][1] = liveRankings.get(i).getName();
            liveRows[liveRankings.size() - i - 1][2] = Integer.toString(liveRankings.get(i).getLiveLeaguePoints());
        }

        for (int i = 0; i < historicalRankings.size(); i++) {
            historicalRows[i][0] = Integer.toString(i + 1);
            historicalRows[historicalRankings.size() - i - 1][1] = historicalRankings.get(i).getName();
            historicalRows[historicalRankings.size() - i - 1][2] = Integer.toString(historicalRankings.get(i).getLeaguePoints());
        }

        JTable liveTable = new JTable(liveRows, liveTitles);
        JTable historicalTable = new JTable(historicalRows, historicalTitles);
        //liveTable.setDefaultEditor(Object.class, null);
        //historicalTable.setDefaultEditor(Object.class, null);
        JScrollPane liveSP = new JScrollPane(liveTable);
        JScrollPane historicalSP = new JScrollPane(historicalTable);
        displayRankingsPanel.add(liveSP);
        displayRankingsPanel.add(historicalSP);
    }

    public void setUpdateRankingsController(UpdateRankingsController updateRankingsController) {
        this.updateRankingsController = updateRankingsController;
    }

    public void setGoHomeController(GoHomeController goHomeController) {
        this.goHomeController = goHomeController;
    }

}
