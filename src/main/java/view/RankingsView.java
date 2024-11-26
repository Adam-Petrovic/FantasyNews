package view;

import entity.League;
import entity.User;
import interface_adapter.to_rankings.RankingsViewModel;
import interface_adapter.update_rankings.UpdateRankingsController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RankingsView extends JPanel implements PropertyChangeListener {
    private final RankingsViewModel rankingsViewModel;
    private UpdateRankingsController updateRankingsController;
    private User user;
    private League league;

    public RankingsView(RankingsViewModel rankingsViewModel) {
        //rankings table
        this.league = new League();
        this.rankingsViewModel = rankingsViewModel;
        rankingsViewModel.getState().setLeague(this.league);
        //this.user = rankingsViewModel.getState().getUser();
        //this.league = rankingsViewModel.getState().getLeague();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        String[] title = {"Ranking"};
        String[][] indices =  {{"1"}, {"2"}, {"3"}};
        JTable indexTable = new JTable(indices, title);

        String[] topicsTitle = {"Food", "Mood", "Dude!"};
        String[][] words =  {{"user1", "user2", "user1"}, {"user3", "user1", "user2"}, {"user2", "user3", "user3"}};
        JTable wordsTable = new JTable(words, topicsTitle);

        JScrollPane indexScrollPane = new JScrollPane(indexTable);
        JScrollPane wordsScrollPane = new JScrollPane(wordsTable);
        mainPanel.add(indexScrollPane);
        mainPanel.add(wordsScrollPane);
        indexTable.setDefaultEditor(Object.class, null);

        Dimension dw = new Dimension(words[0].length * 100, words[0].length * 150);
        Dimension du = new Dimension(title.length * 100, words[0].length * 150);

        wordsScrollPane.setPreferredSize(dw);
        indexScrollPane.setPreferredSize(du);

        //update rankings button
        JButton updateRankings = new JButton("Update Rankings");
        mainPanel.add(updateRankings);

        JTextField rankingsLeagueID = new JTextField("Enter League ID");
        mainPanel.add(rankingsLeagueID);

        updateRankings.addActionListener(evt -> {
            if (evt.getSource() == updateRankings) {
                updateRankingsController.execute(rankingsLeagueID.getText());
            }
        });


        mainPanel.setVisible(true);
        this.add(mainPanel);


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setUpdateRankingsController(UpdateRankingsController updateRankingsController) {
        this.updateRankingsController = updateRankingsController;
    }

}
