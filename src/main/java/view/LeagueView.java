package view;

import entity.League;
import entity.User;
import interface_adapter.create_league.CreateLeagueController;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.signup.SignupState;
import interface_adapter.to_league.LeagueState;
import interface_adapter.to_league.LeagueViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class LeagueView  extends JPanel implements ActionListener, PropertyChangeListener {
    //controllers & stuff
    private final LeagueViewModel leagueViewModel;
    private CreateLeagueController createLeagueController;
    private GoHomeController goHomeController;

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
        goHomeButton = new JButton("<-");

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
        JLabel catPhoto = new JLabel(new ImageIcon("Meow.jpg"));
        catPhoto.setPreferredSize(new Dimension(100, 400));
        displayLeagueTabs.add(catPhoto);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(displayLeaguePanel);
        this.add(functionsPanel);

        //button listeners
        createLeagueButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(createLeagueButton)) {
                        String username = leagueViewModel.getState().getUsername();
                        leagueViewModel.getState().setErrorMessage(null);
                        createLeagueController.execute(username, createLeagueID.getText());
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
        }

        displayLeaguePanel.add(displayLeagueTabs);
        displayLeaguePanel.revalidate();
        displayLeaguePanel.repaint();
    }

    public void setCreateLeagueController(CreateLeagueController controller) {
        this.createLeagueController = controller;
    }

    public void setGoHomeController(GoHomeController controller){
        this.goHomeController = controller;
    }

    public JScrollPane makeLeagueScrollPane(League league){
        JScrollPane leagueScrollPane = new JScrollPane();
        ArrayList<User> users = league.getUsers();
        String[] columnNames = {"Member", "Words", "Points"};
        String[][] info = new String[users.size()][3];

        for(int i = 0; i < users.size(); i++) {
            info[i][0] = users.get(i).getName();
            String[] words = league.getData().get(users.get(i));
            info[i][1] = words[0] + ", " + words[1];
            info[i][2] = String.valueOf(users.get(i).getLeaguePoints());
        }

        JTable leagueTable = new JTable(info, columnNames);
        leagueTable.setDefaultEditor(Object.class, null);
        leagueScrollPane.setViewportView(leagueTable);

        return leagueScrollPane;
    }
}
