package view;

import data_access.Constants;
import entity.League;
import entity.User;
import interface_adapter.award_league_points.AwardLeaguePointsController;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.to_league.LeagueState;
import interface_adapter.to_league.LeagueViewModel;
import interface_adapter.update_league_points.UpdateLeaguePointsController;
import interface_adapter.update_leagues.UpdateLeaguesController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeagueView  extends JPanel implements ActionListener, PropertyChangeListener {
    //controllers & stuff
    private final LeagueViewModel leagueViewModel;
    private GoHomeController goHomeController;
    private UpdateLeaguesController updateLeaguesController;
    private UpdateLeaguePointsController updatePointsForLeagueController;
    private AwardLeaguePointsController awardLeaguePointsController;

    //visuals

    //bottom panel
    private JPanel functionsPanel;

    //create league panel
    private JTextField createLeagueID;
    private JButton createLeagueButton;

    //join league panel
    private JTextField joinLeagueID;
    private JButton joinLeagueButton;

    //go home
    private JButton goHomeButton;

    //league display panel
    private ArrayList<JLabel> leagueLabels;
    private JPanel displayLeaguePanel;
    private JTabbedPane displayLeagueTabs;
    private ArrayList<League> leagues;


    public LeagueView(LeagueViewModel leagueViewModel) {
        this.leagueViewModel = leagueViewModel;
        this.leagueViewModel.addPropertyChangeListener(this);
        //might need to pass in user too TvT
        this.leagues = (leagueViewModel.getState().getLeagues());

        //create new league stuff
        createLeagueID = new JTextField("Enter New League ID");
        createLeagueButton = new JButton("Create New League");

        //join league stuff
        joinLeagueID = new JTextField("Enter League ID");
        joinLeagueButton = new JButton("Join League");

        //go home stuff
        goHomeButton = new JButton("←");

        //bottom panel
        this.functionsPanel = new JPanel();
        functionsPanel.add(goHomeButton);
        functionsPanel.setLayout(new BoxLayout(functionsPanel, BoxLayout.X_AXIS));
        functionsPanel.add(createLeagueButton);
        functionsPanel.add(createLeagueID);
        functionsPanel.add(joinLeagueButton);
        functionsPanel.add(joinLeagueID);

        //displayLeaguePanel (top panel)
        displayLeaguePanel = new JPanel();
        displayLeaguePanel.setLayout(new BoxLayout(displayLeaguePanel, BoxLayout.Y_AXIS));
        displayLeagueTabs = new JTabbedPane();
        displayLeaguePanel.add(displayLeagueTabs);
        JLabel catPhoto = new JLabel(new ImageIcon(""));
        catPhoto.setPreferredSize(new Dimension(100, 400));
        displayLeagueTabs.add(catPhoto);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(displayLeaguePanel);
        this.add(functionsPanel);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                for (League league : leagues) {
                    awardLeaguePointsController.awardPoints(league.getId(), league.getUserObjArr());
                }
            }
        }, 0, 1, TimeUnit.DAYS);

        //button listeners
        createLeagueButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(createLeagueButton)) {
                        String username = leagueViewModel.getState().getUsername();
                        leagueViewModel.getState().setErrorMessage(null);
                        updateLeaguesController.execute(username, createLeagueID.getText(), false);
                    }
                }
        );

        goHomeButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(goHomeButton)) {
                        goHomeController.execute();
                    }
                }
        );

        joinLeagueButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(joinLeagueButton)) {
                        String username = leagueViewModel.getState().getUsername();
                        leagueViewModel.getState().setErrorMessage(null);
                        updateLeaguesController.execute(username, joinLeagueID.getText(), true);
                    }
                }
        );

       // createLeagueListener();
    }

//    private void createLeagueListener() {
//        createLeagueID.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                LeagueState currentState = leagueViewModel.getState();
//                currentState.addLeagueID(createLeagueID.getText());
//                leagueViewModel.setState(currentState);
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//        });
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //updating the league display yasssss
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LeagueState state = (LeagueState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
            return;
        }

        this.leagues = leagueViewModel.getState().getLeagues();
        this.displayLeaguePanel.remove(displayLeagueTabs);
        this.displayLeagueTabs = new JTabbedPane();

        for (League league : this.leagues){
            JScrollPane leagueScrollPane = makeLeagueScrollPane(league);
            displayLeagueTabs.add(league.getId(), leagueScrollPane);

            awardLeaguePointsController.roundUp(league.getId(), league.getUserObjArr());
        }

        displayLeaguePanel.add(displayLeagueTabs);
        displayLeaguePanel.revalidate();
        displayLeaguePanel.repaint();
    }

    public void setGoHomeController(GoHomeController controller){
        this.goHomeController = controller;
    }

    public void setUpdateLeaguesController(UpdateLeaguesController updateLeaguesController){
        this.updateLeaguesController = updateLeaguesController;
    }

    public void setUpdatePointsForLeagueController(UpdateLeaguePointsController updatePointsForLeagueController){
        this.updatePointsForLeagueController = updatePointsForLeagueController;
    }

    public void setAwardLeaguePointsController (AwardLeaguePointsController awardLeaguePointsController){
        this.awardLeaguePointsController = awardLeaguePointsController;
    }



    public JScrollPane makeLeagueScrollPane(League league){
        JScrollPane leagueScrollPane = new JScrollPane();
        String[] columnNames = makeColumns();

        updatePointsForLeagueController.execute(league.getId(), league.getUserObjArr());

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
