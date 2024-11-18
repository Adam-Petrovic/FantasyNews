package view;

import data_access.Constants;
import entity.User;
import interface_adapter.add_friends.AddFriendsState;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_new_friend.AddNewFriendController;
import interface_adapter.signup.SignupState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddFriendsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final AddFriendsViewModel addFriendsViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    private AddNewFriendController addNewFriendController;

    public AddFriendsView(AddFriendsViewModel addFriendsViewModel) {
        this.addFriendsViewModel = addFriendsViewModel;

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel addFriendsPanel = new JPanel();
        addFriendsPanel.setLayout(new BoxLayout(addFriendsPanel, BoxLayout.Y_AXIS));
        JButton addFriendButton = new JButton("Add Friend");
        addFriendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addFriendsPanel.add(addFriendButton);
        //addFriendsPanel.add(new JTextField("enter friend code"));
        final LabelTextPanel friend_usernameInfo = new LabelTextPanel(
                new JLabel("Enter friend username"), usernameInputField);
        mainPanel.add(addFriendsPanel);
        this.add(friend_usernameInfo);

        addFriendButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addFriendButton)) {
                        addNewFriendController.execute(usernameInputField.getText());
                    }
                });

        String[] userTitle = {"Friends"};
        String[][] usernames =  {{"usr1"}, {"usr2"}, {"usr3"}};
        JTable userTable = new JTable(usernames, userTitle);
        JScrollPane userScrollPane = new JScrollPane(userTable);
        mainPanel.add(userScrollPane);

        userTable.setDefaultEditor(Object.class, null);

        mainPanel.setVisible(true);
        this.add(mainPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setAddNewFriendController(AddNewFriendController addNewFriendController) {
        this.addNewFriendController = addNewFriendController;
    }
}
