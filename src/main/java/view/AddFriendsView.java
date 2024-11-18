package view;

import entity.User;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.add_friends.AddFriendsController;

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


    public AddFriendsView(AddFriendsViewModel addFriendsViewModel) {
        this.addFriendsViewModel = addFriendsViewModel;

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel addFriendsPanel = new JPanel();
        addFriendsPanel.setLayout(new BoxLayout(addFriendsPanel, BoxLayout.Y_AXIS));
        JButton addFriendButton = new JButton("Add Friend");
        addFriendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addFriendsPanel.add(addFriendButton);
        addFriendsPanel.add(new JTextField("enter friend code"));
        mainPanel.add(addFriendsPanel);


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
}
