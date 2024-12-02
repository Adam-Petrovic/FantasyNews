package view.leagueviews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// Application-specific imports
import data_access.Constants;
import entity.League;
import entity.User;
import interfaceadapter.leagueuserstory.to_league_actions.LeagueActionsViewModel;
import interfaceadapter.navigation.go_home.GoHomeController;
import interfaceadapter.pointsuserstory.update_league_points.UpdateLeaguePointsController;
import interfaceadapter.selectwordsuserstory.to_draft.ToDraftController;

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
    private JScrollPane displayLeagueScrollPane;
    private League league;

    public LeagueActionsView(LeagueActionsViewModel leagueActionsViewModel) {
        this.leagueActionsViewModel = leagueActionsViewModel;
        this.leagueActionsViewModel.addPropertyChangeListener(this);
        this.league = leagueActionsViewModel.getState().getLeague();

        // displayLeaguePanel (top panel)
        displayLeagueScrollPane = new JScrollPane();

        // functions panel (bottom panel)
        this.goHomeButton = new JButton("<-");
        this.draftButton = new JButton("Draft");
        this.functionsPanel = new JPanel();
        functionsPanel.add(goHomeButton);
        functionsPanel.add(draftButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(displayLeagueScrollPane);
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
        this.remove(displayLeagueScrollPane);
        this.remove(functionsPanel);
        this.league = leagueActionsViewModel.getState().getLeague();
        displayLeagueScrollPane = makeLeagueScrollPane(league);
        this.add(displayLeagueScrollPane);
        this.add(functionsPanel);

    }

    /**
     * Sets the controller responsible for handling navigation to the home view.
     *
     * @param controller the {@link GoHomeController} instance to be used for navigation.
     */
    public void setGoHomeController(GoHomeController controller) {
        this.goHomeController = controller;
    }

    /**
     * Make league scroll pane.
     * @param fakeLeague league.
     * @return scroll pane.
     */
    public JScrollPane makeLeagueScrollPane(League fakeLeague) {
        JScrollPane leagueScrollPane = new JScrollPane();
        String[] columnNames = makeColumns();

        updateLeaguePointsController.execute(fakeLeague.getId(), fakeLeague.getUserObjArr());

        String[][] info = makeInfo(fakeLeague);

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

    private String[][] makeInfo(League fakeLeague) {
        ArrayList<User> usrObj = fakeLeague.getUserObjArr();

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

    /**
     * Sets the controller responsible for updating the points for a league.
     *
     * @param controller the {@link UpdateLeaguePointsController} instance to be used for updating league points.
     */
    public void setUpdatePointsForLeagueController(UpdateLeaguePointsController controller) {
        this.updateLeaguePointsController = controller;
    }

    /**
     * Sets the controller responsible for handling the drafting process in a league.
     *
     * @param conroller the {@link ToDraftController} instance to be used for drafting actions.
     */
    public void setDraftController(ToDraftController conroller) {
        this.toDraftController = conroller;
    }
}
