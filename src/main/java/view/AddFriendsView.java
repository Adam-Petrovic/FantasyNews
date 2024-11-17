package view;

import entity.User;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.add_friends.AddFriendsController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddFriendsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final AddFriendsViewModel addFriendsViewModel;


    public AddFriendsView(AddFriendsViewModel addFriendsViewModel) {
        this.addFriendsViewModel = addFriendsViewModel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
