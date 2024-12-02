package view.actionviews;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.*;

import data_access.Constants;
import entity.League;
import entity.User;
import interfaceadapter.leagueuserstory.to_league.LeagueState;
import interfaceadapter.navigation.go_home.GoHomeController;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsState;
import interfaceadapter.rankingsuserstory.to_rankings.RankingsViewModel;
import interfaceadapter.rankingsuserstory.update_rankings.UpdateRankingsController;

/**
 * The RankingsView class represents a user interface component for displaying
 * and interacting with rankings data in both live and historical contexts.
 * It listens for property changes in the RankingsViewModel to dynamically
 * update the rankings display.
 */
public class RankingsView extends JPanel implements PropertyChangeListener {
    private final RankingsViewModel rankingsViewModel;
    private UpdateRankingsController updateRankingsController;
    private GoHomeController goHomeController;
    private User user;
    private League league;
    private JPanel displayRankingsPanel;
    private JScrollPane liveSp;
    private JScrollPane historicalSp;
    private JTextField rankingsLeagueID;

    public RankingsView(RankingsViewModel rankingsViewModel) {
        this.league = new League();
        this.rankingsViewModel = rankingsViewModel;
        this.rankingsViewModel.addPropertyChangeListener(this);
        rankingsViewModel.getState().setLeague(this.league);

        displayRankingsPanel = new JPanel();
        displayRankingsPanel.setLayout(new BoxLayout(displayRankingsPanel, BoxLayout.X_AXIS));

        JButton goHomeButton = new JButton("←");
        displayRankingsPanel.add(goHomeButton);

        JButton updateRankings = new JButton("Update Rankings");
        displayRankingsPanel.add(updateRankings);

        rankingsLeagueID = new JTextField("Enter LeagueID");

        rankingsLeagueID.setMaximumSize(new Dimension(Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT));
        displayRankingsPanel.add(rankingsLeagueID);

        String[] liveTitles = {"Live Ranking", "default user", "default points"};
        String[][] liveRows = new String[Constants.DEFAULT_ROWS][Constants.DEFAULT_ROWS];

        String[] historicalTitles = {"Historical Ranking", "default user", "default points"};
        String[][] historicalRows = new String[Constants.DEFAULT_ROWS][Constants.DEFAULT_ROWS];

        JTable liveTable = new JTable(liveRows, liveTitles);
        JTable historicalTable = new JTable(historicalRows, historicalTitles);

        liveSp = new JScrollPane(liveTable);
        historicalSp = new JScrollPane(historicalTable);
        displayRankingsPanel.add(liveSp);
        displayRankingsPanel.add(historicalSp);

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

        rankingsLeagueID.setPreferredSize(new Dimension(Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT));
        displayRankingsPanel.setVisible(true);
        this.add(displayRankingsPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final RankingsState state = (RankingsState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }

        displayRankingsPanel.remove(liveSp);
        displayRankingsPanel.remove(historicalSp);

        this.league = rankingsViewModel.getState().getLeague();
        ArrayList<User> liveRankings = league.getLiveRankings();
        ArrayList<User> historicalRankings = league.getHistoricalRankings();

        String[] liveTitles = {"Live Ranking", "user", "points"};
        String[][] liveRows = new String[liveRankings.size()][Constants.DEFAULT_ROWS];

        String[] historicalTitles = {"Historical Ranking", "user", "points"};
        String[][] historicalRows = new String[historicalRankings.size()][Constants.DEFAULT_ROWS];

        for (int i = 0; i < liveRankings.size(); i++) {
            liveRows[i][0] = Integer.toString(i + 1);
            liveRows[liveRankings.size() - i - 1][1] = liveRankings.get(i).getName();
            liveRows[liveRankings.size() - i - 1][2] = Integer.toString(liveRankings.get(i).getLiveLeaguePoints());
        }

        for (int i = 0; i < historicalRankings.size(); i++) {
            historicalRows[i][0] = Integer.toString(i + 1);
            historicalRows[historicalRankings.size() - i - 1][1] = historicalRankings.get(i).getName();
            historicalRows[historicalRankings.size() - i - 1][2] = Integer.toString(historicalRankings.get(i)
                    .getLeaguePoints());
        }

        JTable liveTable = new JTable(liveRows, liveTitles);
        JTable historicalTable = new JTable(historicalRows, historicalTitles);

        liveSp = new JScrollPane(liveTable);
        historicalSp = new JScrollPane(historicalTable);

        displayRankingsPanel.add(liveSp);
        displayRankingsPanel.add(historicalSp);

        displayRankingsPanel.revalidate();
        displayRankingsPanel.repaint();
    }

    /**
     * Sets the UpdateRankingsController, which handles updating the rankings
     * when the corresponding button is pressed.
     *
     * @param updateRankingsController the controller responsible for updating rankings.
     */
    public void setUpdateRankingsController(UpdateRankingsController updateRankingsController) {
        this.updateRankingsController = updateRankingsController;
    }

    /**
     * Sets the GoHomeController, which navigates the user back to the home view
     * when the corresponding button is pressed.
     *
     * @param goHomeController the controller responsible for navigating to the home view.
     */
    public void setGoHomeController(GoHomeController goHomeController) {
        this.goHomeController = goHomeController;
    }
}
