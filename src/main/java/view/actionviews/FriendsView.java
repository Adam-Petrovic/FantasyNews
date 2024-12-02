package view.actionviews;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entity.User;
import interfaceadapter.friendsuserstory.add_new_friend.AddNewFriendController;
import interfaceadapter.navigation.go_home.GoHomeController;
import interfaceadapter.friendsuserstory.to_friends.FriendsState;
import interfaceadapter.friendsuserstory.to_friends.FriendsViewModel;

/**
 * Friends view showing the user's friends and the friend's points, optionally showing 1v1 comparison.
 */
public class FriendsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final FriendsViewModel friendsViewModel;
    private JScrollPane jScrollPane;
    private JTable friendsTable;
    private User user;
    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    private AddNewFriendController addNewFriendController;
    private GoHomeController goHomeController;

    public FriendsView(FriendsViewModel friendsViewModel) {
        this.friendsViewModel = friendsViewModel;
        this.friendsViewModel.addPropertyChangeListener(this);
        this.user = friendsViewModel.getState().getUser();

        jScrollPane = new JScrollPane();
        JPanel userOptions = new JPanel();
        userOptions.setLayout(new BoxLayout(userOptions, BoxLayout.X_AXIS));

        JButton goHomeButton = new JButton("←");
        userOptions.add(goHomeButton);

        final LabelTextPanel friendUsernameInfo = new LabelTextPanel(
                new JLabel("Enter friend username"), usernameInputField);
        userOptions.add(friendUsernameInfo);

        JButton addFriend = new JButton("Add Friend");
        userOptions.add(addFriend);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(jScrollPane);
        this.add(userOptions);

        addFriend.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addFriend)) {
                        String friend = friendsViewModel.getState().getFriendUsername();
                        friendsViewModel.getState().setFriendUsernameError(null);
                        addNewFriendController.execute(friend, this.user.getName());
                    }
                });

        goHomeButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(goHomeButton)) {
                        goHomeController.execute();
                    }
                });

        addFriendListener();
    }

    private void addFriendListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final FriendsState currentState = friendsViewModel.getState();
                currentState.setFriendUsername(usernameInputField.getText());
                friendsViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final FriendsState state = (FriendsState) evt.getNewValue();
        if (state.getFriendUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getFriendUsernameError());
        }
        displayTable();
    }

    private void displayTable() {
        this.user = friendsViewModel.getState().getUser();
        Map<User, Integer> userPoints = friendsViewModel.getState().getUserPoints();
        String[] columnNames = {"Friends", "Points"};
        Object[][] data = new Object[user.getFriends().size()][2];

        for (int i = 0; i < user.getFriends().size(); i++) {
            User friend = user.getFriends().get(i);
            data[i][0] = friend.getName();
            data[i][1] = userPoints.get(friend);
        }
        friendsTable = new JTable(data, columnNames);
        friendsTable.setDefaultEditor(Object.class, null);

        // Add MouseListener to the JTable
        friendsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = friendsTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    // Retrieve friend's points from the clicked row
                    String friendName = (String) friendsTable.getValueAt(row, 0);
                    Integer friendPoints = (Integer) userPoints.values().toArray()[1];
                    Integer myPoints = (Integer) userPoints.values().toArray()[0];

                    // Compare points between user and selected friend and display corresponding result
                    String message = getMessage(friendPoints, myPoints, friendName);

                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });

        jScrollPane.add(friendsTable);
        jScrollPane.setViewportView(friendsTable);
    }

    private static String getMessage(Integer friendPoints, Integer myPoints, String friendName) {
        String message;
        if (friendPoints > myPoints) {
            message = friendName + " has " + (friendPoints - myPoints) + " more points than you!";
        }
        else if (friendPoints < myPoints) {
            message = "You have " + (myPoints - friendPoints) + " more points than " + friendName + "!";
        }
        else {
            message = "You and " + friendName + " have the same points!";
        }
        return message;
    }

    /**
     * Sets the addNewFriendController.
     * @param addNewFriendController controller for adding a new friend.
     */
    public void setAddNewFriendController(AddNewFriendController addNewFriendController) {
        this.addNewFriendController = addNewFriendController;
    }

    /**
     * Sets the goHomeController.
     * @param goHomeController controller for going back to homepage.
     */
    public void setGoHomeController(GoHomeController goHomeController) {
        this.goHomeController = goHomeController;
    }
}
