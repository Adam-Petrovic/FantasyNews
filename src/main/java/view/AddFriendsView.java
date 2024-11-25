package view;

import data_access.Constants;
import entity.User;
import interface_adapter.add_friends.AddFriendsState;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_new_friend.AddNewFriendController;
import interface_adapter.go_home.GoHomeController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddFriendsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final AddFriendsViewModel addFriendsViewModel;
    private JScrollPane jScrollPane;
    private JTable friendsTable;
    private User user;
    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    private AddNewFriendController addNewFriendController;
    private GoHomeController goHomeController;

    public AddFriendsView(AddFriendsViewModel addFriendsViewModel) {
        this.addFriendsViewModel = addFriendsViewModel;
        this.addFriendsViewModel.addPropertyChangeListener(this);
        this.user = addFriendsViewModel.getState().getUser();

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

//        JPanel mainPanel = new JPanel();
//
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//
//        JPanel addFriendsPanel = new JPanel();
//        addFriendsPanel.setLayout(new BoxLayout(addFriendsPanel, BoxLayout.Y_AXIS));
//        //JButton addFriendButton = new JButton("Add Friend");
//        addFriend.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addFriendsPanel.add(addFriend);
//        //addFriendsPanel.add(new JTextField("enter friend code"));
//
//        mainPanel.add(addFriendsPanel);

        addFriend.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addFriend)) {
                        String friend = addFriendsViewModel.getState().getFriend_username();
                        addFriendsViewModel.getState().setFriend_usernameError(null);
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

//        String[] userTitle = {"Friends"};
//        String[][] usernames =  {{"usr1"}, {"usr2"}, {"usr3"}};
//        JTable userTable = new JTable(usernames, userTitle);
//        JScrollPane userScrollPane = new JScrollPane(userTable);
//        mainPanel.add(userScrollPane);
//
//        userTable.setDefaultEditor(Object.class, null);
//
//        mainPanel.setVisible(true);
//        this.add(mainPanel);

    }

    private void addFriendListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddFriendsState currentState = addFriendsViewModel.getState();
                currentState.setFriend_username(usernameInputField.getText());
                addFriendsViewModel.setState(currentState);
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
        this.user = addFriendsViewModel.getState().getUser();
        final AddFriendsState state = (AddFriendsState) evt.getNewValue();
        if(state.getFriend_usernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getFriend_usernameError());
        }

        String[] columnNames = {"Friends", "Points"};
        Object[][] data = new Object[user.getFriends().size()][2];

        for (int i = 0; i < user.getFriends().size(); i++) {
            User friend = user.getFriends().get(i);
            data[i][0] = friend.getName();
            data[i][1] = sumPoints(friend);
        }
        friendsTable = new JTable(data, columnNames);
        friendsTable.setDefaultEditor(Object.class, null);
        jScrollPane.add(friendsTable);
        jScrollPane.setViewportView(friendsTable);

    }

    public void setAddNewFriendController(AddNewFriendController addNewFriendController) {
        this.addNewFriendController = addNewFriendController;
    }

    public void setGoHomeController(GoHomeController goHomeController) {
        this.goHomeController = goHomeController;
    }

    private int sumPoints(User friend){
        int sum = 0;
        for (int i = 0; i < friend.getAllPoints().length; i++) {
            sum += friend.getAllPoints()[i];
        }
        return sum;
    }
}
