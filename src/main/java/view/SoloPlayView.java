package view;

import data_access.Constants;
import entity.Users.User;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.solo_play.SoloPlayState;
import interface_adapter.solo_play.SoloPlayViewModel;
import interface_adapter.add_word.AddWordController;
import interface_adapter.update_points.UpdatePointsController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SoloPlayView extends JPanel implements PropertyChangeListener {
    private final SoloPlayViewModel soloPlayViewModel;
    private AddWordController addWordController;
    private UpdatePointsController updatePointsConroller;
    private JScrollPane jScrollPane;
    private JTable wordsTable;
    private User user;
    private GoHomeController goHomeController;

    public SoloPlayView(SoloPlayViewModel soloPlayViewModel) {
        this.soloPlayViewModel = soloPlayViewModel;
        this.soloPlayViewModel.addPropertyChangeListener(this);
        this.user = soloPlayViewModel.getState().getUser();

        // Initializing the JTable

        jScrollPane = new JScrollPane();
        JPanel userOptions = new JPanel();
        userOptions.setLayout(new BoxLayout(userOptions, BoxLayout.X_AXIS));

        JButton goHomeButton = new JButton("←");
        userOptions.add(goHomeButton);

        JTextField wordInput = new JTextField(10);
        userOptions.add(wordInput);

        JButton addWord = new JButton("Add");
        userOptions.add(addWord);

        JButton updateScores = new JButton("Update");
        userOptions.add(updateScores);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(jScrollPane);
        this.add(userOptions);

        goHomeButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(goHomeButton)) {
                        goHomeController.execute();
                    }
                });

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
                        updatePointsConroller.execute(this.user);
                    }
                }
        );

        wordInput.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SoloPlayState currentState = soloPlayViewModel.getState();
                currentState.setInputtedWord(wordInput.getText());
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
        wordsTable.setDefaultEditor(Object.class, null);
        jScrollPane.add(wordsTable);
        jScrollPane.setViewportView(wordsTable);
    }

    public void setAddWordController(AddWordController controller) {
        this.addWordController = controller;
    }


    public void setUpdatePointsConroller(UpdatePointsController controller) {
        this.updatePointsConroller = controller;
    }

    public void setGoHomeController(GoHomeController goHomeController){
        this.goHomeController = goHomeController;
    }


}
