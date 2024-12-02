package view.actionviews;

import entity.League;
import entity.User;
import interfaceadapter.navigation.go_home.GoHomeController;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsViewModel;
import interfaceadapter.rankingsuserstory.update_rankings.UpdateRankingsController;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class RankingsView extends JPanel implements PropertyChangeListener {
    private final RankingsViewModel rankingsViewModel;
    private UpdateRankingsController updateRankingsController;
    private GoHomeController goHomeController;
    private User user;
    private League league;
    private JPanel displayRankingsPanel;
    JScrollPane liveSP;
    JScrollPane historicalSP;

    public RankingsView(RankingsViewModel rankingsViewModel) {
        //rankings table
        this.league = new League();
        this.rankingsViewModel = rankingsViewModel;
        this.rankingsViewModel.addPropertyChangeListener(this);
        rankingsViewModel.getState().setLeague(this.league);

        displayRankingsPanel = new JPanel();
        displayRankingsPanel.setLayout(new BoxLayout(displayRankingsPanel, BoxLayout.X_AXIS));

        JButton goHomeButton = new JButton("←");
        displayRankingsPanel.add(goHomeButton);

        //update rankings button
        JButton updateRankings = new JButton("Update Rankings");
        displayRankingsPanel.add(updateRankings);

        JTextField rankingsLeagueID = new JTextField("Enter New League ID");
        displayRankingsPanel.add(rankingsLeagueID);

        String[] liveTitles = {"Live Ranking", "user", "points"};
        String[][] liveRows = new String[3][3];

        String[] historicalTitles = {"Historical Ranking", "user", "points"};
        String[][] historicalRows = new String[3][3];


        JTable liveTable = new JTable(liveRows, liveTitles);
        JTable historicalTable = new JTable(historicalRows, historicalTitles);

        liveSP = new JScrollPane(liveTable);
        historicalSP = new JScrollPane(historicalTable);
        displayRankingsPanel.add(liveSP);
        displayRankingsPanel.add(historicalSP);

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
        displayRankingsPanel.remove(liveSP);
        displayRankingsPanel.remove(historicalSP);

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

        liveSP = new JScrollPane(liveTable);
        historicalSP = new JScrollPane(historicalTable);

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
