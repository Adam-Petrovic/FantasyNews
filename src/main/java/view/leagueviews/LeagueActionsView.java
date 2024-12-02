package view.leagueviews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.*;

// Application-specific imports
import data_access.Constants;
import entity.League;
import entity.User;
import interfaceadapter.navigation.go_home.GoHomeController;
import interfaceadapter.selectwordsuserstory.to_draft.ToDraftController;
import interfaceadapter.leagueuserstory.to_league_actions.LeagueActionsViewModel;
import interfaceadapter.pointsuserstory.update_league_points.UpdateLeaguePointsController;

/**
 * League Actions View.
 */
public class LeagueActionsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final LeagueActionsViewModel leagueActionsViewModel;
    private GoHomeController goHomeController;
    private UpdateLeaguePointsController updateLeaguePointsController;
    private ToDraftController toDraftController;

    // go home & functions
    private JPanel functionsPanel;
    private JButton goHomeButton;
    private JButton draftButton;

    // league display panel
    private ArrayList<JLabel> leagueLabels;
    private JPanel displayLeaguePanel;
    private League league;

    public LeagueActionsView(LeagueActionsViewModel leagueActionsViewModel) {
        this.leagueActionsViewModel = leagueActionsViewModel;
        this.leagueActionsViewModel.addPropertyChangeListener(this);
        this.league = leagueActionsViewModel.getState().getLeague();

        // displayLeaguePanel (top panel)
        displayLeaguePanel = new JPanel();

        // functions panel (bottom panel)
        this.goHomeButton = new JButton("<-");
        this.draftButton = new JButton("Draft");
        this.functionsPanel = new JPanel();
        functionsPanel.add(goHomeButton);
        functionsPanel.add(draftButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(displayLeaguePanel);
        this.add(functionsPanel);

        goHomeButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(goHomeButton)) {
                        goHomeController.execute();
                    }
                }
        );

        draftButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(draftButton)) {
                        String name = leagueActionsViewModel.getState().getUsername();
                        String leagueID = leagueActionsViewModel.getState().getLeague().getId();
                        toDraftController.execute(name, leagueID);

                    }
                }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.league = leagueActionsViewModel.getState().getLeague();
        displayLeaguePanel.removeAll();
        JScrollPane leagueScrollPane = makeLeagueScrollPane(league);
        displayLeaguePanel.add(leagueScrollPane);
    }

    public void setGoHomeController(GoHomeController controller) {
        this.goHomeController = controller;
    }

    /**
     * Make league scroll pane.
     * @param league league.
     * @return scroll pane.
     */
    public JScrollPane makeLeagueScrollPane(League league) {
        JScrollPane leagueScrollPane = new JScrollPane();
        String[] columnNames = makeColumns();

        updateLeaguePointsController.execute(league.getId(), league.getUserObjArr());

        String[][] info = makeInfo(league);

        JTable leagueTable = new JTable(info, columnNames);
        leagueTable.setDefaultEditor(Object.class, null);
        leagueScrollPane.setViewportView(leagueTable);

        return leagueScrollPane;
    }

    private String[] makeColumns() {
        String[] c = new String[Constants.NUM_CATEGORIES * 2 + 1];
        c[0] = "Username";

        for (int i = 1; i < c.length; i += 2) {
            c[i] = Constants.CATEGORIES[(i - 1) / 2];
            c[i + 1] = "Pts";
        }
        return c;
    }

    private String[][] makeInfo(League league) {
        ArrayList<User> usrObj = league.getUserObjArr();

        String[][] info = new String[usrObj.size()][Constants.NUM_CATEGORIES * 2 + 1];
        for (int i = 0; i < usrObj.size(); i++) {
            User currentUser = usrObj.get(i);
            info[i][0] = currentUser.getName();

            String[] userWords = currentUser.getWords();
            Integer[] points = currentUser.getAllPoints();
            for (int j = 1; j < Constants.NUM_CATEGORIES * 2; j += 2) {
                info[i][j] = userWords[(j - 1) / 2];
                info[i][j + 1] = points[(j - 1) / 2].toString();
            }
        }

        return info;
    }

    public void setUpdatePointsForLeagueController(UpdateLeaguePointsController controller) {
        this.updateLeaguePointsController = controller;
    }

    public void setDraftController(ToDraftController toDraftController) {
        this.toDraftController = toDraftController;
    }
}