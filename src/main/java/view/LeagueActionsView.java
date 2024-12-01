package view;

import data_access.Constants;
import entity.League;
import entity.User;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.updateLeaguePoints.UpdateLeaguePointsController;
import interface_adapter.to_league_actions.LeagueActionsViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class LeagueActionsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final LeagueActionsViewModel leagueActionsViewModel;
    private GoHomeController goHomeController;
    private UpdateLeaguePointsController updateLeaguePointsController;

    //go home & functions
    private JPanel functionsPanel;
    private JButton goHomeButton;
    private JButton draftButton;
    private JButton startButton;

    //league display panel
    private ArrayList<JLabel> leagueLabels;
    private JPanel displayLeaguePanel;
    private League league;

    public LeagueActionsView(LeagueActionsViewModel leagueActionsViewModel) {
        this.leagueActionsViewModel = leagueActionsViewModel;
        this.leagueActionsViewModel.addPropertyChangeListener(this);
        this.league = (leagueActionsViewModel.getState().getLeague());

        //displayLeaguePanel (top panel)
        displayLeaguePanel = new JPanel();

        //functions panel (bottom panel)
        this.goHomeButton = new JButton("<-");
        this.draftButton = new JButton("Draft");
        this.startButton = new JButton("Start");
        this.functionsPanel = new JPanel();
        functionsPanel.add(goHomeButton);
        functionsPanel.add(draftButton);
        functionsPanel.add(startButton);

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
                    if(evt.getSource().equals(draftButton)) {
                    }
                }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.league = (leagueActionsViewModel.getState().getLeague());
        displayLeaguePanel.removeAll();
        JScrollPane leagueScrollPane = makeLeagueScrollPane(league);
        displayLeaguePanel.add(leagueScrollPane);
    }

    public void setGoHomeController(GoHomeController controller){
        this.goHomeController = controller;
    }

    public void setUpdateLeaguePointsController(UpdateLeaguePointsController controller){
        this.updateLeaguePointsController = controller;
    }


    public JScrollPane makeLeagueScrollPane(League league){
        JScrollPane leagueScrollPane = new JScrollPane();
        String[] columnNames = makeColumns();

        updateLeaguePointsController.execute(league.getUserObjArr());

        String[][] info = makeInfo(league);

        JTable leagueTable = new JTable(info, columnNames);
        leagueTable.setDefaultEditor(Object.class, null);
        leagueScrollPane.setViewportView(leagueTable);

        return leagueScrollPane;
    }

    private String[] makeColumns() {
        String[] c =  new String[Constants.NUM_CATEGORIES * 2 + 1];
        c[0] = "Username";

        for(int i = 1; i < c.length; i+= 2) {
            c[i] = Constants.CATEGORIES[(i - 1) / 2];
            c[i + 1] = "Pts";
        }
        return c;
    }

    private String[][] makeInfo(League league){
        ArrayList<User> usrObj = league.getUserObjArr();

        String[][] info = new String[usrObj.size()][Constants.NUM_CATEGORIES * 2 + 1];
        for(int i = 0; i <  usrObj.size(); i++){
            User currentUser  = usrObj.get(i);
            info[i][0] = currentUser.getName();

            String[] userWords = currentUser.getWords();
            Integer[] points = currentUser.getAllPoints();
            for (int j = 1; j < Constants.NUM_CATEGORIES * 2; j+= 2){
                info[i][j] = userWords[(j - 1) / 2];
                info[i][j+1] = points[(j - 1) / 2].toString();
            }
        }

        return info;
    }
}
