package view;

import data_access.Constants;
import entity.User;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.solo_play.SoloPlayController;
import interface_adapter.solo_play.SoloPlayState;
import interface_adapter.solo_play.SoloPlayViewModel;
import interface_adapter.add_word.AddWordController;
import interface_adapter.update_points.UpdatePointsController;
import use_case.signup.SignupOutputBoundary;
//import use_case.update_points.UpdatePointsController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SoloPlayView extends JPanel implements PropertyChangeListener {
    private final String viewName = Constants.SOLO_PLAY_VIEW_NAME;
    private final SoloPlayViewModel soloPlayViewModel;
    private AddWordController addWordController;
    private UpdatePointsController updatePointsConroller;
    private JScrollPane jScrollPane;
    private JTable wordsTable;
    private User user;

    public SoloPlayView(SoloPlayViewModel soloPlayViewModel) {
        this.soloPlayViewModel = soloPlayViewModel;
        this.soloPlayViewModel.addPropertyChangeListener(this);
        this.user = soloPlayViewModel.getState().getUser();

        // Initializing the JTable

        jScrollPane = new JScrollPane();
        JPanel userOptions = new JPanel();
        userOptions.setLayout(new BoxLayout(userOptions, BoxLayout.X_AXIS));

        JTextField wordInput = new JTextField(10);
        userOptions.add(wordInput);

        JButton addWord = new JButton("Add");
        userOptions.add(addWord);

        JButton updateScores = new JButton("Update");
        userOptions.add(updateScores);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(jScrollPane);
        this.add(userOptions);

        addWord.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addWord)) {
                        String category = Constants.CATEGORIES[wordsTable.getSelectedColumn()];
                        String newWord = soloPlayViewModel.getState().getInputtedWord();
                        addWordController.execute(user.getName(), category, newWord);

                    }
                });

        updateScores.addActionListener(
                evt -> {
                    if (evt.getSource().equals(updateScores)) {
                        System.out.println("Update Button Pressed");
                        updatePointsConroller.execute(this.user);
                    }
                }
        );

        wordInput.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SoloPlayState currentState = soloPlayViewModel.getState();
                currentState.setInputtedWord(new String(wordInput.getText()));
                System.out.println(currentState.getInputtedWord());
                soloPlayViewModel.setState(currentState);
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
    public void propertyChange(PropertyChangeEvent evt) {

        this.user = soloPlayViewModel.getState().getUser();

        Object[][] wordsEntry = {user.getWords(), user.getAllPoints()};

        Integer[] selectedCell = soloPlayViewModel.getState().getSelectedCell();

        if (! selectedCell[0].equals(-1) && ! selectedCell[1].equals(-1)) {
            wordsEntry[selectedCell[0]][selectedCell[1]] = soloPlayViewModel.getState().getInputtedWord();
        }

        wordsTable = new JTable(wordsEntry, Constants.CATEGORIES);
        jScrollPane.add(wordsTable);
        jScrollPane.setViewportView(wordsTable);
    }

    public String getViewName(){
        return viewName;
    }

    public void setAddWordController(AddWordController controller) {
        this.addWordController = controller;
    }

    public void setUpdatePointsConroller(UpdatePointsController controller) {
        this.updatePointsConroller = controller;
    }

}
