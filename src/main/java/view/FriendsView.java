package view;

import entity.User;
import interface_adapter.to_friends.FriendsState;
import interface_adapter.to_friends.FriendsViewModel;
import interface_adapter.add_new_friend.AddNewFriendController;
import interface_adapter.go_home.GoHomeController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

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

        final LabelTextPanel friend_usernameInfo = new LabelTextPanel(
                new JLabel("Enter friend username"), usernameInputField);
        userOptions.add(friend_usernameInfo);

        JButton addFriend = new JButton("Add Friend");
        userOptions.add(addFriend);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(jScrollPane);
        this.add(userOptions);

        addFriend.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addFriend)) {
                        String friend = friendsViewModel.getState().getFriend_username();
                        friendsViewModel.getState().setFriend_usernameError(null);
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
                currentState.setFriend_username(usernameInputField.getText());
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
        if(state.getFriend_usernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getFriend_usernameError());
        }
        displayTable();
    }

    private void displayTable() {
        this.user = friendsViewModel.getState().getUser();
        HashMap<User, Integer> userPoints = friendsViewModel.getState().getUserPoints();
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
                    Integer friendPoints = (Integer) friendsTable.getValueAt(row, 1);
                    Integer myPoints = (Integer) userPoints.values().toArray()[0];

                    // Compare points between user and selected friend and display corresponding result
                    String message;
                    if (friendPoints > myPoints) {
                        message = friendName + " has " + (friendPoints - myPoints) + " more points than you!";
                    } else if (friendPoints < myPoints) {
                        message = "You have " + (myPoints - friendPoints) + " more points than " + friendName + "!";
                    } else {
                        message = "You and " + friendName + " have the same points!";
                    }

                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });

        jScrollPane.add(friendsTable);
        jScrollPane.setViewportView(friendsTable);
    }

    public void setAddNewFriendController(AddNewFriendController addNewFriendController) {
        this.addNewFriendController = addNewFriendController;
    }

    public void setGoHomeController(GoHomeController goHomeController) {
        this.goHomeController = goHomeController;
    }
}
