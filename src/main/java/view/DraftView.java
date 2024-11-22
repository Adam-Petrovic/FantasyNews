package view;

import interface_adapter.draft.DraftViewModel;
import interface_adapter.draft.DraftController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DraftView extends JPanel implements ActionListener, PropertyChangeListener {
    private final DraftViewModel draftViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    private DraftController draftController;

    public DraftView(DraftViewModel draftViewModel) {
        this.draftViewModel = draftViewModel;

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel addWordPanel = new JPanel();
        addWordPanel.setLayout(new BoxLayout(addWordPanel, BoxLayout.Y_AXIS));
        JButton addWordButton = new JButton("Add Word");
        addWordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addWordPanel.add(addWordButton);
        //addFriendsPanel.add(new JTextField("enter friend code"));
        final LabelTextPanel friend_usernameInfo = new LabelTextPanel(
                new JLabel("Enter word for draft"), usernameInputField);
        mainPanel.add(addWordPanel);
        this.add(friend_usernameInfo);

        addWordButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addWordButton)) {
                        draftController.execute(usernameInputField.getText());
                    }
                });

        String[] userTitle = {"Words"};
        String[][] usernames =  {{"word1"}, {"word2"}, {"word3"}};
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

    public void setDraftController(DraftController draftController) {
        this.draftController = draftController;
    }

}
